import {Layout, Row, Col, Button} from 'antd';
import { Link } from 'react-router-dom';

const Header = () => {
    return (
        <Layout.Header>
            <Row justify="space-between">
                <Col>
                    <Link to="/">
                        <img src="logo.png" alt="Logo" style={{ height: '20px', margin: '20px 0px' }} />
                    </Link>
                </Col>
                <Col>
                    <Link to="/login">
                        <Button type="primary" style={{ marginRight: '10px' }}>Login</Button>
                    </Link>
                    <Link to="/register">
                        <Button type="primary">Register</Button>
                    </Link>
                </Col>
            </Row>
        </Layout.Header>
    );
};

export default Header;