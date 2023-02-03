import {Typography, Button, Row, Col} from 'antd';
import { UserOutlined, CodeOutlined, TrophyOutlined } from '@ant-design/icons';
import React from 'react';
import {useNavigate} from "react-router-dom";

const { Title } = Typography;

const Section = ({ title, description, buttonText, link, Icon }) => {
    const navigator = useNavigate();
    return (
        <div style={{ background: '#fff', padding: 24, textAlign: 'center' }}>
            <Icon style={{ fontSize: '36px' }} />
            <Title level={1}>{title}</Title>
            <p>{description}</p>
            <Button type="primary" onClick={() => navigator(link)}>{buttonText}</Button>
        </div>
    )
}

const HomePage = () => {
    const sections = [
        {
            title: "Compiler",
            description: "Welcome to our programming with friends page's compiler. Start programming now!",
            buttonText: "Go to compiler",
            link: "/compiler",
            Icon: CodeOutlined
        },
        {
            title: "Challenges",
            description: "Join our programming challenges to improve your programming skills.",
            buttonText: "Go to challenges",
            link: "/challenges",
            Icon: UserOutlined
        },
        {
            title: "Tournaments",
            description: "Join our tournaments and compete against other programmers to see who's the best.",
            buttonText: "Go to tournaments",
            link: "/tournaments",
            Icon: TrophyOutlined
        }];

    return (
            <Row justify="center">
                {
                    sections.map((section, index) => (
                        <>
                            <Col key={index} span={24 / sections.length}>
                                <Section {...section} />
                            </Col>
                        </>
                    ))
                }

            </Row>
    );
};

export default HomePage;
