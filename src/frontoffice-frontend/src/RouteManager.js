import HomePage from "./views/home";
import {Route, Routes} from "react-router-dom";
import NotFound from "./views/not-found";
import ScriptList from "./views/script-list";
import React from "react";
import {HOME_PAGE, SCRIPTS_PAGE} from "./constants/routes";

const RouteManager = () => {
    return (
        <Routes>
            <Route path={HOME_PAGE.route} element={<HomePage />} />
            <Route path={SCRIPTS_PAGE.route} element={<ScriptList />} />
            <Route path="*" element={<NotFound />} />
        </Routes>
    )
}

export default RouteManager;