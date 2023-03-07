import {CodeOutlined, TrophyOutlined, UserOutlined} from "@ant-design/icons";
import {Card, Col, Row} from "antd";
import React from "react";
import PageSection from "./PageSection";
import {useNavigate} from "react-router-dom";
import './styles/page-sections.css';
import {CHALLENGES_PAGE, SCRIPTS_PAGE, TOURNAMENTS_PAGE} from "../../constants/routes";
import {MY_SCRIPTS_SECTION} from "../../constants/scriptsSections";

const sectionsRowStyle = { marginLeft: '200px', marginRight: '200px', borderRadius: '100%' };

const PageSections = () => {
    const navigator = useNavigate();
    const sections = [
        {title: "Compiler", description: "Programming sandbox for learning and testing with your friends.",
            link: SCRIPTS_PAGE.route + "?section=" + MY_SCRIPTS_SECTION.key, Icon: CodeOutlined},
        {title: "Challenges", description: "Try programming challenges to improve your programming skills.",
            link: CHALLENGES_PAGE.route, Icon: UserOutlined},
        {title: "Tournaments", description: "Join our tournaments and compete against other programmers to see who's the best.",
            link: TOURNAMENTS_PAGE.route, Icon: TrophyOutlined}
    ];

    return (
        <Row justify="center" style={sectionsRowStyle}>
            {
                sections.map((section, index) => (
                    <Col key={index} span={24 / sections.length} style={{display: 'flex'}}>
                        <Card className={'page-section'} onClick={() => navigator(section.link)}>
                            <PageSection {...section} />
                        </Card>
                    </Col>
                ))
            }
        </Row>
    );
}

export default PageSections;