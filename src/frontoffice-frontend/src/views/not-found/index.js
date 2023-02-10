import { Result, Button } from 'antd';
import { Link } from 'react-router-dom';

const NotFound = () => {
    return (
        <Result
            status="404"
            title="404"
            subTitle="Sorry, the page you're looking for is not found."
            extra={
                <Link to="/">
                    <Button type="primary">Back to Homepage</Button>
                </Link>
            }
        />
    )
}

export default NotFound