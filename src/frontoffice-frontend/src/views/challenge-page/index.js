import {useParams} from "react-router-dom";
import {Content} from "antd/es/layout/layout";
import CodeEditor from "../../components/CodeEditor";
import {useRef, useState} from "react";
import {LANGUAJES} from "../../constants/languages";
import Sider from "antd/es/layout/Sider";
import CodeEditorOptions from "../../components/CodeEditorOptions";
import {Layout} from "antd";

const ChallengePage = () => {
    const { id } = useParams()
    const [language, setLanguage] = useState(LANGUAJES[0]);
    const editorRef = useRef(null);


    return (
        <>
            <h1>Challenge Page</h1>
            <Layout>
                <Sider width={"fit-content"}>
                    <CodeEditorOptions id={id} language={language.key} setLanguage={setLanguage} languages={LANGUAJES} />
                </Sider>
                <Content style={{ padding: '24px 24px', height: '100vh' }}>
                    <CodeEditor language={language.key} editorRef={editorRef} />
                </Content>
            </Layout>
        </>
    )
}

export default ChallengePage