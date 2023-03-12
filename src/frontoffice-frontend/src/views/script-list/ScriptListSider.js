import {PlusOutlined} from "@ant-design/icons";
import {Button, Dropdown, Menu} from "antd";
import {MY_SCRIPTS_SECTION} from "../../constants/scriptsSections";
import {SCRIPTS_PAGE} from "../../constants/routes";
import {generateUUID} from "../../utils/uuid";
import {createScript} from "../../requests/scripts/createScript";
import {errorNotification} from "../../utils/notification";

const ScriptCreator = ({languages, addScript}) =>   {

    const dropdownLanguages = languages.map(language => {
        return {
            key: language.id,
            label: (
                <div rel="noopener noreferrer" onClick={() => createAndOpenScript({languageId: language.id})}>
                    {language.name}
                </div>
            ),
        }
    })


    const createAndOpenScript = ({languageId}) => {
        const id = generateUUID();
        const key = generateUUID();
        const script = {id, key, languageId};
        createScript(script).then(() => {
            window.open(SCRIPTS_PAGE.route + "/" + id, '_blank').focus();
            addScript({...script, name: "Untitled script"});
        }).catch(e =>
            errorNotification("Error retrieving scripts", e.response.data.message || "Try again later",
                "topRight")
        )
    }

    return (
        <Dropdown menu={{items: dropdownLanguages}} placement={"bottomRight"} arrow={{pointAtCenter: true}}>
            <Button id="create-script-btn" type="default" shape="round" icon={<PlusOutlined />} size="large" style={{marginLeft: "10px"}}>
                New
            </Button>
        </Dropdown>
    )
}

const ScriptListSider =  ({selectedKey, addScript}) => {
    const sections = [MY_SCRIPTS_SECTION];

    const languages = [
        {id: 'ccd651ad-012d-4c01-8287-43e117f75de6', name: "Node.js"},
        {id: 'f7f620d1-cf38-431e-9640-9a6a05dea978', name: "Python"},
        {id: '35ecd470-d2eb-4e57-b9ae-970a8686b7bd', name: "Java"},
    ];

    const selectKey = ({key}) => {
        window.location.href = SCRIPTS_PAGE.route + "?section=" + key;
    }

    return (
        <>
            <ScriptCreator languages={languages} addScript={addScript} />
            <Menu mode="inline" items={sections} selectedKeys={[selectedKey]} onClick={selectKey} />
        </>
    )
}

export default ScriptListSider