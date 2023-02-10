import PageTitle from "./PageTitle";
import {Col, Row} from "antd";
import {ExperimentOutlined, SettingOutlined} from "@ant-design/icons";
import React from "react";
import PageSection from "./PageSection";
import './styles/page-usages.css';

const PageUsages = () => {
    return (
        <>
            <PageTitle>
                Why should you use our platform?
            </PageTitle>

            <Row justify="center" className="usages-row">
                <Col span={10} className="usages-column">
                    <div className="usages-container">
                        <PageSection title="Educate"
                            description="From showing simple code examples to being able to organize collaborative activities between students."
                            Icon={ExperimentOutlined} />
                    </div>
                </Col>
                <Col span={2} />
                <Col span={10} className="usages-column">
                    <div className="usages-container">
                        <PageSection title="Train"
                            description="Practice makes perfect. Nothing will help you improve your coding skills more than non-stop resolving challenges."
                            Icon={SettingOutlined} />
                    </div>
                </Col>
            </Row>
        </>
    );
}

export default PageUsages;