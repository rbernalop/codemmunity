import {PlusOutlined} from "@ant-design/icons";
import {Button, Menu} from "antd";
import {MY_SCRIPTS_SECTION} from "../../constants/scriptsSections";
import {SCRIPTS_PAGE} from "../../constants/routes";

const ScriptListSider =  ({selectedKey}) => {
    const sections = [MY_SCRIPTS_SECTION];
    const selectKey = ({key}) => {
        window.location.href = SCRIPTS_PAGE.route + "?section=" + key;
    }

    const createAndOpenScript = () => {
        console.log("createAndOpenScript")
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