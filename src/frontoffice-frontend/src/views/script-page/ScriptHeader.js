import {Button, Avatar, Tooltip} from "antd";
import {ShareAltOutlined} from "@ant-design/icons";
import ShareScriptModal from "../../components/ShareScriptModal";
import EditScriptTitle from "./EditScriptTitle";
import {useState} from "react";

String.prototype.hashCode = function() {
    var hash = 0, i, chr;
    if (this.length === 0) return hash;
    for (i = 0; i < this.length; i++) {
        chr   = this.charCodeAt(i);
        hash  = ((hash << 5) - hash) + chr;
        hash |= 0; // Convertir a un entero de 32 bits
    }
    return hash;
};

const ScriptHeader = ({ id, shareKey, setShareKey, title, setTitle, scriptUsers }) => {
    const [isModalOpen, setIsModalOpen] = useState(false);
    return (
        <div style={{fontSize: "2em", fontWeight: "bolder", marginBottom: "30px"}}>
            <EditScriptTitle id={id} title={title} setTitle={setTitle} />
            <Button type="primary" shape="round" size="large" style={{float: 'right', marginTop: 10, marginRight: 10}}
                    onClick={() => setIsModalOpen(true)}>
                <ShareAltOutlined />
                Share
            </Button>

            <Avatar.Group style={{float: 'right', marginTop: 10, marginRight: 10}}>
                {
                    scriptUsers.map(user => {
                        const color = "#" + Math.abs(user.hashCode()).toString(16).slice(0,6);
                        return (
                            <Tooltip key={user} title={user} placement="top">
                                <Avatar key={user} style={{backgroundColor: color, verticalAlign: 'middle'}}>
                                    {user[0].toUpperCase()}
                                </Avatar>
                            </Tooltip>
                        )
                    })
                }
            </Avatar.Group>

            <ShareScriptModal scriptId={id} shareKey={shareKey} setShareKey={setShareKey} isModalOpen={isModalOpen}
                              setIsModalOpen={setIsModalOpen} />
        </div>
    )
}

export default ScriptHeader;