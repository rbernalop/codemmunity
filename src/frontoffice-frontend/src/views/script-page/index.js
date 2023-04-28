import CodeEditor from "../../components/CodeEditor";
import {useParams} from "react-router-dom";
import {useEffect, useRef, useState} from "react";
import CodeEditorOptions from "../../components/CodeEditorOptions";
import {Layout} from "antd";
import {Content} from "antd/es/layout/layout";
import Sider from "antd/es/layout/Sider";
import CodeRunBox from "../../components/CodeRunBox";
import findScriptById from "../../requests/scripts/findScriptById";
import {errorNotification} from "../../utils/notification";
import Authenticate from "../authenticate";
import ScriptHeader from "./ScriptHeader";
import {connect} from "../../requests/websocket/connection";
import {listenUsersLeft} from "../../requests/websocket/script/consumer/userLeftScript";
import {listenUsersJoined} from "../../requests/websocket/script/consumer/userJoinedScript";
import {joinScript} from "../../requests/websocket/script/producer/joinScript";
import {leaveScript} from "../../requests/websocket/script/producer/leaveScript";
import {sendChange} from "../../requests/websocket/script/producer/sendScriptChange";
import {listenScriptChanges} from "../../requests/websocket/script/consumer/scriptChange";
import {LANGUAJES} from "../../constants/languages";

const ScriptPage = () => {
    const { id } = useParams()
    const [isAuthenticated, setIsAuthenticated] = useState(localStorage.getItem("token") !== null)

    const [stompClient, setStompClient] = useState(null)
    const [formTab, setFormTab] = useState("login")
    const [scriptUsers, setScriptUsers] = useState([])

    const [language, setLanguage] = useState(LANGUAJES[0]);
    const [title, setTitle] = useState('');
    const [shareKey, setShareKey] = useState('');

    const [compilationOutput, setCompilationOutput] = useState(''); // Estado para almacenar la salida
    const [executionOutput, setExecutionOutput] = useState(''); // Estado para almacenar la salida
    const [code, setCode] = useState('');
    const [isRunning, setIsRunning] = useState(false);


    const editorRef = useRef(null);


    useEffect(() => {
        if (!isAuthenticated) {
            return;
        }

        function handleConnection(generatedStompClient) {
            setStompClient(generatedStompClient)
            listenUsersJoined(generatedStompClient, id, scriptUsers, setScriptUsers, (code) => editorRef.current.setValue(code))
            listenUsersLeft(generatedStompClient, id, scriptUsers, setScriptUsers)
            joinScript(generatedStompClient, id, localStorage.getItem("username"))
            window.addEventListener("beforeunload", () => {
                leaveScript(generatedStompClient, id, localStorage.getItem("username"))
            });
        }

        function handleError() {
            setTimeout(() => {
                connect(handleConnection, () => {
                    errorNotification("Error connecting to server", "Try again later", "topRight")
                })
            }, 5000);
        }

        connect(handleConnection, handleError)

        findScriptById(id).then((response) => {
            setTitle(response.data.name);
            setLanguage(LANGUAJES.find(language => language.id === response.data.languageId));
            setShareKey(response.data.shareKey);
        }).catch(e =>
            errorNotification("Error retrieving script", e.response.data.message || "Try again later",
                "topRight")
        );
    }, []);


    // WHEN CODE CHANGES
    useEffect(() => {
        editorRef.current.on('change', (instance, changes) => {
            setCode(editorRef.current.getValue());
            if(changes.origin !== "setValue" && changes.origin !== undefined) {
                sendChange(stompClient, id, localStorage.getItem("username"), changes, instance.getValue());
            }
        });
    }, [setCode, stompClient]);

    useEffect(() => {
        listenScriptChanges(stompClient, id, (change, codeChanged) => {
            editorRef.current.replaceRange(change.text, change.from, change.to, 'setValue');
            setCode(codeChanged);
        });
    }, [stompClient]);

    if(!isAuthenticated) {
        return(
            <Authenticate showModal={!isAuthenticated} setShowModal={setIsAuthenticated} formTab={formTab} setFormTab={setFormTab} />
        )
    }

    return (
        <>
            <ScriptHeader id={id} shareKey={shareKey} setShareKey={setShareKey} title={title} setTitle={setTitle}
                  scriptUsers={scriptUsers} setLanguage={setLanguage} />
            <Layout>
                <Sider width={"fit-content"}>
                    <CodeEditorOptions id={id} language={language.key} setLanguage={setLanguage} languages={LANGUAJES} />
                </Sider>
                <Content style={{ padding: '24px 24px', height: '100vh' }}>
                    <CodeEditor language={language.key} editorRef={editorRef} />
                </Content>
                <Sider width={"fit-content"} style={{padding: '24px 24px', backgroundColor: '#F5F5F5', height: '100vh'}}>
                    <CodeRunBox
                        isAuthenticated={isAuthenticated}
                        executionResult={executionOutput}
                        setExecutionOutput={setExecutionOutput}
                        compilationResult={compilationOutput}
                        setCompilationOutput={setCompilationOutput}
                        code={code}
                        language={language}
                        isRunning={isRunning}
                        setIsRunning={setIsRunning}
                    />
                </Sider>
            </Layout>
        </>
    );
}

export default ScriptPage;