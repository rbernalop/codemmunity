import {Dropdown} from "antd";
import {EditOutlined} from "@ant-design/icons";
import {renameScript} from "../../requests/scripts/renameScript";
import {errorNotification} from "../../utils/notification";
import {useState} from "react";
import RenameScriptModal from "./RenameScriptModal";

const items = [{label: 'Rename script', key: 'rename', icon: <EditOutlined />},];

const ScriptCardMenu = ({ script, setScriptName, children }) => {
    const [isRenameModalOpen, setIsRenameModalOpen] = useState(false);

    const rename = async (scriptId, newName) => {
        renameScript(scriptId, newName).then(() =>
            setScriptName(scriptId, newName)
        ).catch(e =>
            errorNotification("Error while renaming script", e.response.data.message || "Try again later", "topRight")
        );
    }

    const handleMenuClick = (e) => {
        switch (e.key) {
            case 'rename':
                setIsRenameModalOpen(true);
                break;
            default:
                break;
        }
    };

    const menuProps = {
        items,
        onClick: handleMenuClick
    };

    return (
        <>
            <Dropdown menu={menuProps} trigger={['contextMenu']}>
                {children}
            </Dropdown>

            <RenameScriptModal
                script={script}
                isOpen={isRenameModalOpen}
                onClose={() => setIsRenameModalOpen(false)}
                onRename={rename} />
        </>
    )
}

export default ScriptCardMenu