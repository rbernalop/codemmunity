import {CodeOutlined, TrophyOutlined, UserOutlined} from "@ant-design/icons";
import {Card, Col, Row} from "antd";
import React from "react";
import PageSection from "./PageSection";
import {useNavigate} from "react-router-dom";
import './styles/page-sections.css';

const sectionsRowStyle = { marginLeft: '200px', marginRight: '200px', borderRadius: '100%' };

const PageSections = () => {
    const navigator = useNavigate();
    const sections = [
        {title: "Compiler", description: "Programming sandbox for learning and testing with your friends.",
            link: "/compiler", Icon: CodeOutlined},
        {title: "Challenges", description: "Try programming challenges to improve your programming skills.",
            link: "/challenges", Icon: UserOutlined},
        {title: "Tournaments", description: "Join our tournaments and compete against other programmers to see who's the best.",
            link: "/tournaments", Icon: TrophyOutlined}
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