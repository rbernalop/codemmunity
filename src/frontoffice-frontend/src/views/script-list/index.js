import ScriptCardList from "./ScriptCardList";
import {Col, Row} from "antd";
import ScriptListSider from "./ScriptListSider";
import {useEffect, useState} from "react";
import {MY_SCRIPTS_SECTION} from "../../constants/scriptsSections";
import NotFound from "../not-found";
import findUserScripts from "../../requests/scripts/findUserScripts";
import {errorNotification} from "../../utils/notification";

const ScriptList = () => {
    const [scripts, setSripts] = useState([])

    const [section, setSection] = useState(window.location.href.split("/").pop())

    const retrieveScripts = (page, size) => {
        findUserScripts(page, size).then(r =>
            console.log(r)
        ).catch(e =>
            errorNotification("Error retrieving scripts", e.response.data.message || "Try again later",
            "topRight")
        )
    }

    useEffect(() => {
        retrieveScripts(0, 102)
    }, [])

    return (
        <Row>
            <Col span={2}>
                <ScriptListSider selectedKey={section} setSelectedKey={setSection} retrieveScripts={retrieveScripts} />
            </Col>

            {section === MY_SCRIPTS_SECTION.key &&
                <Col span={22}>
                    <div style={{margin: "0px 10px"}}>
                        <ScriptCardList scripts={scripts}/>
                    </div>
                </Col>
            }

            {section !== MY_SCRIPTS_SECTION.key &&
                <div style={{width: '100%'}}>
                    <NotFound />
                </div>
            }
        </Row>
    )
}

export default ScriptList