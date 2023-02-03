import RouteManager from "./RouteManager";
import Header from "./components/Header";
import React from "react";
import {Layout} from "antd";
import {BrowserRouter} from "react-router-dom";

const { Content, Footer } = Layout;

const App = () => {
    return (
        <BrowserRouter>
            <Layout className="layout">
                <Header />
                <Content style={{ padding: '50px', minHeight: 'calc(100vh - 64px - 70px)' }}>
                    <RouteManager />
                </Content>
                <Footer style={{ textAlign: 'center' }}>Codemmunity ©2023</Footer>
            </Layout>
        </BrowserRouter>
    );
}

export default App;
