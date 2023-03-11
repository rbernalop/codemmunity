import {PlusOutlined} from "@ant-design/icons";
import {Button, Menu} from "antd";
import {MY_SCRIPTS_SECTION} from "../../constants/scriptsSections";
import {SCRIPTS_PAGE} from "../../constants/routes";
import {generateUUID} from "../../utils/uuid";
import {createScript} from "../../requests/scripts/createScript";
import {errorNotification} from "../../utils/notification";

const ScriptListSider =  ({selectedKey}) => {
    const sections = [MY_SCRIPTS_SECTION];
    const selectKey = ({key}) => {
        window.location.href = SCRIPTS_PAGE.route + "?section=" + key;
    }

    const createAndOpenScript = () => {
        const id = generateUUID();
        const key = generateUUID();
        const languageId = generateUUID(); // TODO: change this to a real language id
        const script = {id, key, languageId};
        createScript(script).then(() => {
            window.open(SCRIPTS_PAGE.route + "/" + id, '_blank').focus();
        }).catch(e =>
            errorNotification("Error retrieving scripts", e.response.data.message || "Try again later",
                "topRight")
        )
    }

    return (
        <>
            <Button type="default" shape="round" icon={<PlusOutlined />} size="large" style={{marginLeft: "10px"}}
                    onClick={createAndOpenScript}>
                New
            </Button>
            <Menu mode="inline" items={sections} selectedKeys={[selectedKey]} onClick={selectKey} />
        </>
    )
}

export default ScriptListSider