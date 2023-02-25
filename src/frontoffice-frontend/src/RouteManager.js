import HomePage from "./views/home";
import {Route, Routes} from "react-router-dom";
import NotFound from "./views/not-found";
import React from "react";
import HealthCheck from "./views/health-check/HealthCheck";

const RouteManager = () => {
    return (
        <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/health-check" element={<HealthCheck />} />
            <Route path="*" element={<NotFound />} />
        </Routes>
    )
}

export default RouteManager;