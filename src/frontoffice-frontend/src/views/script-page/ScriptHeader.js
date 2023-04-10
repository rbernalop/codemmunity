import {Button} from "antd";
import {ShareAltOutlined} from "@ant-design/icons";
import ShareScriptModal from "../../components/ShareScriptModal";
import EditScriptTitle from "./EditScriptTitle";
import {useState} from "react";

const ScriptHeader = ({ id, shareKey, setShareKey, title, setTitle }) => {
    const [isModalOpen, setIsModalOpen] = useState(false);
    return (
        <div style={{fontSize: "2em", fontWeight: "bolder", marginBottom: "30px"}}>
            <EditScriptTitle id={id} title={title} setTitle={setTitle} />
            <Button type="primary" shape="round" size="large" style={{float: 'right', marginTop: 10, marginRight: 10}}
                    onClick={() => setIsModalOpen(true)}>
                <ShareAltOutlined />
                Share
            </Button>
            <ShareScriptModal scriptId={id} shareKey={shareKey} setShareKey={setShareKey} isModalOpen={isModalOpen}
                              setIsModalOpen={setIsModalOpen} />
        </div>
    )
}

export default ScriptHeader;