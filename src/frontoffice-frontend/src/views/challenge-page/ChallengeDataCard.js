
import React from 'react';
import { Card, Typography, Tag } from 'antd';

const { Title, Text } = Typography;

const ChallengeDataCard = ({ challenge }) => {
    if (!challenge) {
        return <div>Error: Challenge data is not available.</div>;
    }

    const { category, creatorUsername, description, difficulty, title } = challenge;

    const difficultyTagColor = difficulty => {
        switch (difficulty) {
            case 'easy':
                return 'green';
            case 'medium':
                return 'orange';
            case 'hard':
                return 'red';
            default:
                return 'blue';
        }
    };

    return (
        <Card
            style={{
                maxWidth: 600,
                borderRadius: 12,
                boxShadow: '0 0 10px rgba(0, 0, 0, 0.15)',
                marginBottom: 20,
            }}
        >
            <Title level={3}>{title}</Title>
            <Tag color={difficultyTagColor(difficulty)}>{difficulty}</Tag>
            <br />
            <br />
            <Text>Category: {category}</Text>
            <br />
            <Text>Created by: {creatorUsername}</Text>
            <hr />
            <Text>{description}</Text>
        </Card>
    );
};

export default ChallengeDataCard