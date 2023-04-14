import HomePage from "./views/home";
import {Route, Routes} from "react-router-dom";
import NotFound from "./views/not-found";
import ScriptList from "./views/script-list";
import React from "react";
import {CHALLENGES_PAGE, HOME_PAGE, SCRIPT_PAGE, SCRIPTS_PAGE} from "./constants/routes";
import ScriptPage from "./views/script-page";
import ChallengeList from "./views/challenge-list";

const RouteManager = () => {
    return (
        <Routes>
            <Route path={HOME_PAGE.route} element={<HomePage />} />
            <Route path={SCRIPTS_PAGE.route} element={<ScriptList />} />
            <Route path={SCRIPT_PAGE.route} element={<ScriptPage />} />
            <Route path={CHALLENGES_PAGE.route} element={<ChallengeList />} />
            <Route path="*" element={<NotFound />} />
        </Routes>
    )
}

export default RouteManager;