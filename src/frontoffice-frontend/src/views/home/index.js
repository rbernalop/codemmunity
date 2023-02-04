import React from 'react';
import PageTitle from "./PageTitle";
import PageUsages from "./PageUsages";
import PageSections from "./PageSections";
import AboutUs from "./AboutUs";

const HomePage = () => {

    return (
        <>
            <PageTitle>
                <div>Welcome to the</div>
                <span style={{ color: '#1890ff' }}>new programming experience</span>
            </PageTitle>
            <PageSections />
            <PageUsages />
            <AboutUs />
        </>
    );
};

export default HomePage;
