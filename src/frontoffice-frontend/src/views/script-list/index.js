import {Card, List} from "antd";

const ScriptList = () => {
    const scripts = [
        {id: '698a1492-3744-4523-a50d-1865ee68740b', name: 'Script 1'},
        {id: '698a1492-3744-4523-a50d-1865ee68740c', name: 'Script 2'},
        {id: '698a1492-3744-4523-a50d-1865ee68740d', name: 'Script 3'},
        {id: '698a1492-3744-4523-a50d-1865ee68740e', name: 'Script 4'},
    ]
    return (
        <>
            <h2>My Scripts</h2>
            <List grid={{ gutter: 16, column: 8 }} dataSource={scripts} renderItem={item => (
                <List.Item>
                    <Card hoverable onDoubleClick={() => window.open(`/script/${item.id}`, '_blank').focus()}>
                        {item.name}
                    </Card>
                </List.Item>
            )}
            />
        </>
    )
}

export default ScriptList