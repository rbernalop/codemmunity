import {Layout, Row, Col, Button} from 'antd';
import { Link } from 'react-router-dom';
import {useState} from "react";
import Authenticate from "../views/authenticate";

const Header = () => {
    const [showAuthModal, setShowAuthModal] = useState(false);
    const [formTab, setFormTab] = useState('login');
    const logedIn = localStorage.getItem('token') !== null;
    return (
        <Layout.Header>
            <Row justify="space-between">
                <Col>
                    <Link to="/">
                        <img src="/logo.png" alt="Logo" style={{ height: '20px', margin: '20px 0px' }} />
                    </Link>
                </Col>
                {
                    logedIn ?
                    <Col>
                        <span style={{ color: 'white', marginRight: '10px' }} id="welcome-msg">
                            Welcome, {localStorage.getItem('username')}
                        </span>
                        <Button type="primary" onClick={() => {
                            localStorage.clear();
                            window.location.reload();
                        }} id="logout-btn">Logout</Button>
                    </Col>
                        :
                    <Col>
                        <Button id="login-btn" type="primary" style={{ marginRight: '10px' }} onClick={() => {
                            setFormTab('login');
                            setShowAuthModal(true)
                        }}>Login</Button>

                        <Button id="register-btn" type="primary" onClick={() => {
                            setFormTab('register');
                            setShowAuthModal(true)
                        }}>Register</Button>
                    </Col>
                }
            </Row>
            <Authenticate showModal={showAuthModal} setShowModal={setShowAuthModal} formTab={formTab} setFormTab={setFormTab} />
        </Layout.Header>
    );
};

export default Header;