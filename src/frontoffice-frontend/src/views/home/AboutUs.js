import React, {useState} from 'react';
import {Row, Col, Typography, Divider, Avatar} from 'antd';
import './styles/about-us.css';
import {UserOutlined} from "@ant-design/icons";

const { Title, Paragraph } = Typography;

const AboutUs = () => {
    const [isImageZoomed, setIsImageZoomed] = useState(false);

    return (
        <section id="about-us">
            <section id="about-us" className="about-us-section">
                <Row>
                    <Col span={24}>
                        <Title level={2} className="about-us-title">About Us</Title>
                        <Divider />
                    </Col>
                    <Col span={24}>
                        <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                            <Avatar
                                size={100}
                                src="profile.jpg"
                                icon={<UserOutlined />}
                                onMouseEnter={() => setIsImageZoomed(true)}
                                onMouseLeave={() => setIsImageZoomed(false)}
                                style={{
                                    cursor: 'pointer',
                                    transform: isImageZoomed ? 'scale(1.1)' : 'scale(1)',
                                    transition: 'transform 0.5s',
                                }}
                                onClick={() => window.open('https://www.linkedin.com/in/rafael-bernabeu-lopez/', '_blank')}
                            />
                            <div style={{ textAlign: 'left', marginLeft: '20px' }}>
                                <Title level={2}>Rafael Bernabeu López</Title>
                                <Paragraph style={{ maxWidth: '450px', fontSize: '16px' }}>
                                    I am a Computer Engineering student at the University of Alicante, currently
                                    I am in the last year of the degree. I am specializing in the area of
                                    backend development, although I am also interested in other areas such as frontend,
                                    artificial intelligence or cloud computing.
                                </Paragraph>
                                <Paragraph style={{ maxWidth: '450px', fontSize: '16px' }}>
                                    This project is my Final Degree Project, in which I have tried to demonstrate all
                                    the knowledge that I have acquired during these years of career.
                                </Paragraph>
                            </div>
                        </div>
                    </Col>
                </Row>
            </section>
        </section>
    );
};

export default AboutUs;