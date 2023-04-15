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

const languages = [
    {
        id: 'ccd651ad-012d-4c01-8287-43e117f75de6',
        key: "javascript",
        icon: <i className="devicon-nodejs-plain" style={{fontSize: 22}}></i>,
        sample: `/* Simple Hello World in Node.js */
console.log("Hello World");
`
    },
    {
        id: 'f7f620d1-cf38-431e-9640-9a6a05dea978',
        key: "python",
        icon: <i className="devicon-python-plain" style={{fontSize: 22}}></i>,
        sample: `# Simple Hello World in Python
print("Hello World")
`
    },
    {
        id: '35ecd470-d2eb-4e57-b9ae-970a8686b7bd',
        key: "text/x-java",
        icon: <i className="devicon-java-plain" style={{fontSize: 22}}></i>,
        sample: `// Simple Hello World in Java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
`
    },
];


const ScriptPage = () => {
    const { id } = useParams()
    const [isAuthenticated, setIsAuthenticated] = useState(localStorage.getItem("token") !== null)

    const [stompClient, setStompClient] = useState(null)
    const [formTab, setFormTab] = useState("login")
    const [scriptUsers, setScriptUsers] = useState([])

    const [language, setLanguage] = useState(languages[0]);
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

        connect((generatedStompClient) => {
            setStompClient(generatedStompClient)
            listenUsersJoined(generatedStompClient, id, scriptUsers, setScriptUsers, (code) => editorRef.current.setValue(code))
            listenUsersLeft(generatedStompClient, id, scriptUsers, setScriptUsers)
            joinScript(generatedStompClient, id, localStorage.getItem("username"))
            window.addEventListener("beforeunload", () =>
            {
                leaveScript(generatedStompClient, id, localStorage.getItem("username"))
            });
        }, () => {
            errorNotification("Error connecting to server", "Try again later", "topRight")
        });

        findScriptById(id).then((response) => {
            setTitle(response.data.name);
            setLanguage(languages.find(language => language.id === response.data.languageId));
            setShareKey(response.data.shareKey);
        }).catch(e =>
            errorNotification("Error retrieving script", e.response.data.message || "Try again later",
                "topRight")
        );
    }, []);

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
                    <CodeEditorOptions id={id} language={language.key} setLanguage={setLanguage} languages={languages} />
                </Sider>
                <Content style={{ padding: '24px 24px', height: '100vh' }}>
                    <CodeEditor scriptId={id} language={language.key} setCode={setCode} editorRef={editorRef} stompClient={stompClient} />
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