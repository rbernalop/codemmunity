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

const ChallengePage = () => {
    const { id } = useParams()
    const [language, setLanguage] = useState(LANGUAJES[0]);
    const editorRef = useRef(null);
    const [challengeData, setChallengeData] = useState({});
    const [code, setCode] = useState(
        "function bubblesort(arr) {\n" +
        "    let n = arr.length;\n" +
        "    for (let i = 0; i < n - 1; i++) {\n" +
        "        for (let j = 0; j < n - i - 1; j++) {\n" +
        "            if (arr[j] > arr[j + 1]) {\n" +
        "                let temp = arr[j];\n" +
        "                arr[j] = arr[j + 1];\n" +
        "                arr[j + 1] = temp;\n" +
        "            }\n" +
        "        }\n" +
        "    }\n" +
        "    return arr;\n" +
        "}\n" +
        "module.exports = bubblesort;\n");
    const [isRunning, setIsRunning] = useState(false);


    useEffect(() => {
        findChallengeById(id).then((response) => {
            setChallengeData(response.data)
        }).catch((e) => {
            errorNotification("Error while retrieving challenge", e.response.data.message || "Try again later", "topRight")
        })
    }, [])

    return (
        <>
            <Layout>
                <Sider width={"fit-content"} style={{padding: '24px 24px', backgroundColor: '#F5F5F5', height: '100vh'}}>
                    <ChallengeDataCard challenge={challengeData} />
                    <hr />
                    <TestRunBox challengeId={id} languageName={language.name} isRunning={isRunning} setIsRunning={setIsRunning} />
                </Sider>
                <Content style={{ padding: '24px 24px', height: '100vh' }}>
                    <CodeEditor language={language.key} editorRef={editorRef} code={code} />
                </Content>
                <Sider width={"fit-content"}>
                    <CodeEditorOptions id={id} language={language.key} setLanguage={setLanguage} languages={LANGUAJES} />
                </Sider>
            </Layout>
        </>
    )
}

export default ChallengePage