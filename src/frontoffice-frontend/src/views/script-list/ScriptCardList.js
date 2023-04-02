import {Card, List} from "antd";
import {SCRIPT_PAGE} from "../../constants/routes";
import ScriptCardMenu from "./ScriptCardMenu";

const ScriptCardList = ({ scripts, setScriptName }) => {
    return (
        <>
            <h2>My Scripts</h2>

            <List grid={{ gutter: 16, column: 8 }} dataSource={scripts} renderItem={item => (
                <List.Item>
                    <ScriptCardMenu script={item} setScriptName={setScriptName}>
                        <Card
                            id={item.id}
                            hoverable
                            onDoubleClick={() =>
                                window.open(SCRIPT_PAGE.route.replace(":id", item.id), '_blank').focus()}>
                            {item.name}
                        </Card>
                    </ScriptCardMenu>
                </List.Item>
            )}
            />
    </>
    )
}

export default ScriptCardList