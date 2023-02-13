import RouteManager from "./RouteManager";
import React from "react";
import {BrowserRouter} from "react-router-dom";
import PageLayout from "./components/PageLayout";


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
