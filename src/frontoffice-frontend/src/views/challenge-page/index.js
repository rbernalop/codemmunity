import {useParams} from "react-router-dom";
import {Content} from "antd/es/layout/layout";
import CodeEditor from "../../components/CodeEditor";
import {useEffect, useRef, useState} from "react";
import {LANGUAJES} from "../../constants/languages";
import Sider from "antd/es/layout/Sider";
import CodeEditorOptions from "../../components/CodeEditorOptions";
import {Layout} from "antd";
import {findChallengeById} from "../../requests/challenges/findChallengeById";
import {errorNotification} from "../../utils/notification";
import ChallengeDataCard from "./ChallengeDataCard";
import TestRunBox from "../../components/TestRunBox";
import {connect} from "../../requests/websocket/connection";
import {
    sendChallengeScriptContentChange
} from "../../requests/websocket/challengeScript/producer/sendChallengeScriptChange";
import {listenChallengeScriptJoin} from "../../requests/websocket/challengeScript/consumer/userJoinedChallengeScript";
import {joinChallengeScript} from "../../requests/websocket/challengeScript/producer/joinChallengeScript";
import {createChallengeScript} from "../../requests/websocket/challengeScript/producer/createChallengeScript";
import {findBaseCodeById} from "../../requests/baseCode/findBaseCodeById";
import {
    sendChallengeScriptLanguageChange
} from "../../requests/websocket/challengeScript/producer/sendChallengeScriptLanguageChange";

const ChallengePage = () => {
    const { id } = useParams()
    const challengeScriptSocketPath = '/service/v1/challengeScript'
    const [language, setLanguage] = useState(LANGUAJES[0]);
    const editorRef = useRef(null);
    const [challengeData, setChallengeData] = useState({});
    const [code, setCode] = useState("");
    const [isRunning, setIsRunning] = useState(false);
    const [stompClient, setStompClient] = useState(null)


    useEffect(() => {
        function handleConnection(generatedStompClient, scriptCode) {
            setStompClient(generatedStompClient)
            listenChallengeScriptJoin(generatedStompClient, id, (content) => {
                    editorRef.current.setValue(content)
                    setCode(content)
                }, (languageName) => {
                    setLanguage(LANGUAJES.find((l) => l.name === languageName))
                },
                () =>
                    createChallengeScript(generatedStompClient, id, localStorage.getItem("username"), language.name, scriptCode)
            )
            joinChallengeScript(generatedStompClient, id, localStorage.getItem("username"))
        }

        function handleError(scriptCode) {
            setTimeout(() => {
                connect((stomp) => handleConnection(stomp, scriptCode), () =>
                    errorNotification("Error connecting to server", "Try again later", "topRight")
                , challengeScriptSocketPath)
            }, 5000);
        }


        findChallengeById(id, language.name).then((response) => {
            editorRef.current.setValue(response.data.baseScript)
            connect((stomp) => handleConnection(stomp, response.data.baseScript), () => handleError(response.data.baseScript), challengeScriptSocketPath)
            setChallengeData(response.data)
        }).catch((e) => {
            errorNotification("Error while retrieving challenge", e.response.data.message || "Try again later", "topRight")
        })
    }, [])

    useEffect(() => {
        editorRef.current.on('change', (instance, changes) => {
            setCode(editorRef.current.getValue());
            if(changes.origin !== "setValue" && changes.origin !== undefined) {
                sendChallengeScriptContentChange(stompClient, id, localStorage.getItem("username"), changes, instance.getValue());
            }
        });
    }, [setCode, stompClient]);

    return (
        <>
            <Layout>
                <Sider width={"fit-content"} style={{padding: '24px 24px', backgroundColor: '#F5F5F5', height: '100vh'}}>
                    <ChallengeDataCard challenge={challengeData} />
                    <hr />
                    <TestRunBox challengeId={id} languageName={language.name} isRunning={isRunning} setIsRunning={setIsRunning} code={code} />
                </Sider>
                <Content style={{ padding: '24px 24px', height: '100vh' }}>
                    <CodeEditor language={language.key} editorRef={editorRef} code={code} />
                </Content>
                <Sider width={"fit-content"}>
                    <CodeEditorOptions language={language.key} changeLanguage={(languageKey) => {
                            const language = LANGUAJES.find(language => language.key === languageKey);
                                findBaseCodeById(language.name, id).then((response) => {
                                sendChallengeScriptLanguageChange(stompClient, id, localStorage.getItem("username"), language.name)
                                sendChallengeScriptContentChange(stompClient, id, localStorage.getItem("username"), {origin: "setValue"}, response.code)
                                setLanguage(language);
                                setCode(response.code)
                                editorRef.current.setValue(response.code)
                            }).catch((e) => {
                                console.log(e)
                                errorNotification("Error while retrieving base code", e.response.data.message || "Try again later", "topRight")
                            })
                    }} />
                </Sider>
            </Layout>
        </>
    )
}

export default ChallengePage