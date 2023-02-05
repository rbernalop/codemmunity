import React from "react";
import './styles/page-title.css';

const PageTitle = ({children}) => {
    return (
        <h1 className="title">
            {children}
        </h1>
    );
};

export default PageTitle;