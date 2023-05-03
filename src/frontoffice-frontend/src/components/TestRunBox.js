import { Button } from 'antd';
import { CaretRightOutlined } from '@ant-design/icons';
import {errorNotification} from "../utils/notification";
import {runChallengeTests} from "../requests/challenges/runChallengeTests";
import {useState} from "react";
import TestResult from "./TestResult";

const TestRunBox = ({ challengeId, languageName, code, isRunning, setIsRunning  }) => {
    const [compilationOutput, setCompilationOutput] = useState(''); // Estado para almacenar la salida
    const [executionOutput, setExecutionOutput] = useState([]); // Estado para almacenar la salida

    const runTests = (challengeId, languageName, code) => {
        setIsRunning(true)
        runChallengeTests(challengeId, languageName, code).then((response) => {
            setExecutionOutput(response.data.results)
        }).catch((e) => {
            errorNotification("Error while running script", e.response.data.message || "Try again later", "topRight")
        }).finally(() => {
            setIsRunning(false)
        })
    }

    return (
        <>
            <Button type="primary"
                    id="run-script-btn"
                    onClick={() => { runTests(challengeId, languageName, code) }}
                    style={{display: 'flex', alignItems: 'center', justifyContent: 'center', width: '100%', height: 'auto', padding: 10, borderRadius: 5, marginTop: 10}}
                    loading={isRunning}
            >
                RUN TESTS & SUBMIT
                <CaretRightOutlined />
            </Button>

            <div style={{ backgroundColor: '#282A36', color: '#fff', width: '600px', padding: 10, borderRadius: 5, marginTop: 10, height: 'calc(100vh - 400px)', overflow: 'auto', fontSize: "0.875rem" }}>
                <div>Compilation Output:</div>
                <br/>
                <pre>{compilationOutput || "No compilation errors :)"}</pre>

                <hr style={{border: '1px solid #fff'}}/>

                <div>Execution Output:</div>
                <br/>
                {
                    executionOutput.map((output, index) => {
                        return (
                            <TestResult key={index} testName={output.testName} executionResult={output.executionResult} passed={output.passed} />
                        )
                    })
                }
            </div>
        </>
    )
}

export default TestRunBox