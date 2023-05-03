import {Progress, Table} from 'antd';
import {useEffect, useState} from "react";
import {findAllChallengesPaginated} from "../../requests/challenges/findAllChallengesPaginated";
import {CheckCircleOutlined, CloseCircleOutlined} from "@ant-design/icons";

const getDifficultyColor = (difficulty) => {
    const hue = (1 - difficulty / 5) * 120; // 120 is the hue range for green to red
    return `hsl(${hue}, 100%, 50%)`;
};

const columns = [
    {
        title: 'Title',
        dataIndex: 'title',
        key: 'title',
        render: (title, record) =>
            <a href={record.id} style={{ color: 'inherit' }}>
                <span style={{ fontSize: '16px', fontWeight: 'bold' }}>{title}</span>
            </a>,
    },
    {
        title: 'Category',
        dataIndex: 'category',
        key: 'category',
        render: (category) => (
            <span style={{ fontSize: '16px', fontFamily: 'Arial', backgroundColor: '#f5f5f5', padding: '4px 8px' }}>
                {category}
            </span>
        ),
    },
    {
        title: 'Difficulty',
        dataIndex: 'difficulty',
        key: 'difficulty',
        render: (difficulty) => (
            <Progress
                percent={difficulty * 20}
                status="active"
                showInfo={false}
                strokeColor={getDifficultyColor(difficulty)}
            />
        ),
    },
    {
        title: 'Completed',
        dataIndex: 'completed',
        key: 'completed',
        render: (completed) => (
            (completed) ? <CheckCircleOutlined /> : <CloseCircleOutlined />
        ),
    }
];

const ChallengeList = () => {
    const [challenges, setChallenges] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalChallenges, setTotalChallenges] = useState(0);
    const [size, setSize] = useState(50);

    useEffect(() => {
        const fetchData = async () => {
            const result = await findAllChallengesPaginated(currentPage, size);
            setChallenges(result.data.challenges);
            setTotalChallenges(result.data.totalChallenges);
        };

        fetchData();
    }, [currentPage, size]);

    return <>
        <h2>Challenges</h2>
        <Table columns={columns} dataSource={challenges} style={{ fontFamily: 'Arial', border: 'none' }}
            bordered={false} size="small" rowKey="id"
            pagination={{
                current: currentPage + 1, onChange: (page) => setCurrentPage(page - 1),
                pageSize: size, showSizeChanger: true, onShowSizeChange: (current, pageSize) => setSize(pageSize),
                total: totalChallenges,
            }}
        />
    </>
};

export default ChallengeList