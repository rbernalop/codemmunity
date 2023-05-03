import React from 'react';
import { Tag } from 'antd';

const TestResult = ({ testName, executionResult, passed }) => {
    return (
        (
            <div style={{ display: 'flex', flexDirection: 'column', marginTop: 10 }}>
                <div>
                    <b style={{marginRight: 10}}>{testName}</b>
                    {passed ? <Tag color="success">PASSED</Tag> : <Tag color="error">ERROR</Tag>}
                </div>
                <div>
                    <pre>{executionResult}</pre>
                </div>
            </div>
        )
    )
}

export default TestResult