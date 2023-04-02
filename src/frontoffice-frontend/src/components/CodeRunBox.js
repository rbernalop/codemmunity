import { Button } from 'antd';
import { CaretRightOutlined } from '@ant-design/icons';

const CodeRunBox = ({ runCode, compilationResult, executionResult, isRunning }) => {

    return (
        <>
            <Button type="primary"
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
                <pre>{executionResult}</pre>
            </div>
        </>
    )
}

export default CodeRunBox