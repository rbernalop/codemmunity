import CodeEditor from "../../components/CodeEditor";
import {useParams} from "react-router";
import {useState} from "react";
import CodeEditorOptions from "../../components/CodeEditorOptions";
import {Layout} from "antd";
import {Content} from "antd/es/layout/layout";
import Sider from "antd/es/layout/Sider";

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
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
`
    },
];


const ScriptPage = () => {
    const {id} = useParams()
    const [language, setLanguage] = useState(languages[1]);

    const changeLanguage = (languageKey) => {
        const language = languages.find(language => language.key === languageKey);
        setLanguage(language);
    }

    return (
        <Layout>
            <Sider width={"fit-content"}>
                <CodeEditorOptions language={language.key} setLanguage={changeLanguage} languages={languages} />
            </Sider>
            <Content style={{ padding: '24px 24px', height: '100vh' }}>
                <CodeEditor roomId={id} language={language.key} code={language.sample} />
            </Content>
        </Layout>
    );
}

export default ScriptPage;