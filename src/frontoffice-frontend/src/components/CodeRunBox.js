import { Button } from 'antd';
import { CaretRightOutlined } from '@ant-design/icons';

const CodeRunBox = ({ runCode, outputResult }) => {
    return (
        <>
            <Button type="primary" onClick={runCode} style={{display: 'flex', alignItems: 'center', justifyContent: 'center', width: '100%', height: 'auto', padding: 10, borderRadius: 5, marginTop: 10}}>
                RUN
                <CaretRightOutlined />
            </Button>

            <div style={{ backgroundColor: '#282A36', color: '#fff', width: '450px', padding: 10, borderRadius: 5, marginTop: 10, height: 'calc(100vh - 110px)', overflow: 'auto', fontSize: "0.875rem" }}>
                <div>Output:</div>
                <br/>
                {outputResult}
            </div>
        </>
    )
}

export default CodeRunBox