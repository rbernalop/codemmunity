import HomePage from "./views/home";
import {Route, Routes} from "react-router-dom";
import NotFound from "./views/not-found";
import React from "react";

const RouteManager = () => {
    return (
                    <Routes>
                        <Route path="/" element={<HomePage />} />
                        <Route path="*" element={<NotFound />} />
                </Routes>
    )
}

export default RouteManager;