import RouteManager from "./RouteManager";
import Header from "./components/Header";
import React from "react";
import {Layout} from "antd";
import {BrowserRouter} from "react-router-dom";
import PageLayout from "./components/PageLayout";

const { Content, Footer } = Layout;

const App = () => {
    return (
        <BrowserRouter>
            <PageLayout>
                <RouteManager />
            </PageLayout>
        </BrowserRouter>
    );
}

export default App;
