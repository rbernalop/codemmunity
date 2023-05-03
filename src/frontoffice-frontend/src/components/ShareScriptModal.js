import {Button, Input, Modal} from "antd";
import {generateUUID} from "../utils/uuid";
import regenerateShareKey from "../requests/scripts/regenerateShareKey";
import {errorNotification} from "../utils/notification";

const ShareScriptModal = ({ scriptId, shareKey, setShareKey, isModalOpen, setIsModalOpen }) => {
    const copyBaseURL = process.env.REACT_APP_COPY_BASE_URL ||
        `http://localhost:3000/script/${scriptId}/`;
    return (
        <Modal
            title="Share Script"
            open={isModalOpen}
            onCancel={() => setIsModalOpen(false)}
            footer={null}
        >
            <div style={{textAlign: 'center'}}>
                <p>Share this script with your friends!</p>
                <Input
                    value={shareKey.length > 0 ? `${copyBaseURL}?shareKey=${shareKey}` : ""}
                    style={{width: '100%'}}
                    readOnly={true}
                    onClick={(e) => {
                        e.target.select()
                        navigator.clipboard.writeText(e.target.value)
                    }}
                />
                <Button type="primary" style={{marginTop: 10}} onClick={() => {
                    const oldShareKey = shareKey;
                    setShareKey("");
                    const nextShareKey = generateUUID();
                    regenerateShareKey(scriptId, nextShareKey).then(() => {
                        setShareKey(nextShareKey)
                    }).catch(e => {
                        errorNotification("Error changing language", e.response.data.message || "Try again later", "topRight")
                        setShareKey(oldShareKey);
                    })
                }}>
                    Regenerate Share Key
                </Button>
            </div>
        </Modal>
    );
}

export default ShareScriptModal;