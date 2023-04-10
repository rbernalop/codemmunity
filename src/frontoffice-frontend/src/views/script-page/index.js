import CodeEditor from "../../components/CodeEditor";
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import CodeEditorOptions from "../../components/CodeEditorOptions";
import {Layout} from "antd";
import {Content} from "antd/es/layout/layout";
import Sider from "antd/es/layout/Sider";
import CodeRunBox from "../../components/CodeRunBox";
import findScriptById from "../../requests/scripts/findScriptById";
import {errorNotification} from "../../utils/notification";
import {changeScriptLanguage} from "../../requests/scripts/changeScriptLanguage";
import {runScript} from "../../requests/scripts/runScript";
import Authenticate from "../authenticate";
import ScriptHeader from "./ScriptHeader";

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
    const [formTab, setFormTab] = useState("login")
    const [language, setLanguage] = useState(languages[0]);
    const [title, setTitle] = useState('');
    const [shareKey, setShareKey] = useState('');
    const [compilationOutput, setCompilationOutput] = useState(''); // Estado para almacenar la salida
    const [executionOutput, setExecutionOutput] = useState(''); // Estado para almacenar la salida
    const [code, setCode] = useState(language.sample);
    const [isRunning, setIsRunning] = useState(false);

    const changeLanguage = (languageKey) => {
        const language = languages.find(language => language.key === languageKey);
        changeScriptLanguage(id, language.id).then(() => {
            setLanguage(language);
        }).catch(e =>
            errorNotification("Error changing language", e.response.data.message || "Try again later", "topRight")
        );
    }

    const runCode = () => {
        if (!isAuthenticated) {
            return;
        }
        setIsRunning(true);
        runScript(code, language.id).then((response) => {
            setIsRunning(false);
            setCompilationOutput('');
            setExecutionOutput(response.data.executionOutput);
        }).catch(e => {
            setIsRunning(false);
            if (e.response.data.compilationError) {
                setCompilationOutput(e.response.data.compilationError)
                setExecutionOutput('');
            } else if (e.response.data.executionError) {
                setExecutionOutput(e.response.data.executionError)
                setCompilationOutput('');
            } else {
                errorNotification("Error running code", e.response.data.message || "Try again later", "topRight")
                setCompilationOutput('');
                setExecutionOutput('');
            }
        });
    };

    useEffect(() => {
        if (!isAuthenticated) {
            return;
        }
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
            <ScriptHeader id={id} shareKey={shareKey} setShareKey={setShareKey} title={title} setTitle={setTitle} />
            <Layout>
                <Sider width={"fit-content"}>
                    <CodeEditorOptions language={language.key} setLanguage={changeLanguage} languages={languages} />
                </Sider>
                <Content style={{ padding: '24px 24px', height: '100vh' }}>
                    <CodeEditor roomId={id} language={language.key} code={language.sample} setCode={setCode} />
                </Content>
                <Sider width={"fit-content"} style={{padding: '24px 24px', backgroundColor: '#F5F5F5', height: '100vh'}}>
                    <CodeRunBox
                        executionResult={executionOutput}
                        compilationResult={compilationOutput}
                        runCode={() => {runCode()}}
                        isRunning={isRunning}
                    />
                </Sider>
            </Layout>
        </>
    );
}

export default ScriptPage;