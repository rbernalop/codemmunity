import {Typography} from "antd";
import React from "react";

const { Title } = Typography;

const iconStyle = { fontSize: '36px' };

const PageSection = ({ title, description, Icon }) => {
    return (
        <>
            <Icon style={iconStyle} />
            <Title level={1}>{title}</Title>
            <p>{description}</p>
        </>
    )
}

export default PageSection;