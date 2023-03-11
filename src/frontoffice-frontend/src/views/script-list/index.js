import ScriptCardList from "./ScriptCardList";
import {Col, Row} from "antd";
import ScriptListSider from "./ScriptListSider";
import {useEffect, useState} from "react";
import {MY_SCRIPTS_SECTION} from "../../constants/scriptsSections";
import NotFound from "../not-found";
import findUserScripts from "../../requests/scripts/findUserScripts";
import {errorNotification} from "../../utils/notification";
import {useSearchParams} from "react-router-dom";
import Authenticate from "../authenticate";

const ScriptList = () => {
    const [scripts, setSripts] = useState([])
    const [isAuthenticated, setIsAuthenticated] = useState(localStorage.getItem("token") !== null)
    const [formTab, setFormTab] = useState("login")
    const [searchParams] = useSearchParams();
    const [section, setSection] = useState(
        searchParams.get("section")
    )

    const retrieveScripts = (page, size) => {
        findUserScripts(page, size).then(r =>
            setSripts(r.data.scriptsResponses)
        ).catch(e =>
            errorNotification("Error retrieving scripts", e.response.data.message || "Try again later",
                "topRight")
        )
    }

    useEffect(() => {
        if(isAuthenticated)
            retrieveScripts(0, 102)
    }, [isAuthenticated])


    if(!isAuthenticated) {
        return(
            <Authenticate showModal={!isAuthenticated} setShowModal={setIsAuthenticated} formTab={formTab} setFormTab={setFormTab} />
        )
    }


    return (
        <Row>
            <Col span={2}>
                <ScriptListSider selectedKey={section} setSelectedKey={setSection} addScript={(script) => {
                    setSripts([...scripts, script])
                }} />
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