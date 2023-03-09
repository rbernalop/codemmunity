import {Card, List} from "antd";
import {SCRIPT_PAGE} from "../../constants/routes";

const ScriptCardList = ({ scripts }) => {
    return (
        <>
            <h2>My Scripts</h2>

            <List grid={{ gutter: 16, column: 8 }} dataSource={scripts} renderItem={item => (
                <List.Item>
                    <Card
                        hoverable
                        onDoubleClick={() =>
                            window.open(SCRIPT_PAGE.route.replace(":id", item.id), '_blank').focus()}
                    >
                        {item.name}
                    </Card>
                </List.Item>
            )}
            />
    </>
    )
}

export default ScriptCardList