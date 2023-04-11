import { Button } from 'antd';
import { CaretRightOutlined } from '@ant-design/icons';
import {runScript} from "../requests/scripts/runScript";
import {errorNotification} from "../utils/notification";

const CodeRunBox = ({ isAuthenticated, compilationResult, setCompilationOutput, executionResult, setExecutionOutput,
                    code, language, isRunning, setIsRunning }) => {

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

    return (
        <>
            <Button type="primary"
                    id="run-script-btn"
                    onClick={runCode}
                    style={{display: 'flex', alignItems: 'center', justifyContent: 'center', width: '100%', height: 'auto', padding: 10, borderRadius: 5, marginTop: 10}}
                    loading={isRunning}
            >
                RUN
                <CaretRightOutlined />
            </Button>

            <div style={{ backgroundColor: '#282A36', color: '#fff', width: '450px', padding: 10, borderRadius: 5, marginTop: 10, height: 'calc(100vh - 110px)', overflow: 'auto', fontSize: "0.875rem" }}>
                <div>Compilation Output:</div>
                <br/>
                <pre>{compilationResult || "No compilation errors :)"}</pre>

                <hr style={{border: '1px solid #fff'}}/>

                <div>Execution Output:</div>
                <br/>
                <pre id="execution-result">{executionResult}</pre>
            </div>
        </>
    )
}

export default CodeRunBox