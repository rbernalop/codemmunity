import {Button, Form, Input} from "antd";
import {checkRequiredField} from "../../utils/formValidators";
import login from "../../requests/authentication/login";
import {errorNotification} from "../../utils/notification";

const LoginForm = ({form}) => {

    const handleOk = () => {
        form.validateFields()
            .then(values => {
                login(values).then(r => {
                    localStorage.setItem('token', r.headers.authorization);
                    localStorage.setItem('id', r.data.id);
                    localStorage.setItem('username', r.data.username);
                    localStorage.setItem('email', r.data.email);
                    window.location.reload();
                }).catch(e =>
                    errorNotification("Error while log in", e.response.data.message, "topRight")
                );
            }).catch(() => {});
    };

    return (
        <Form form={form} onFinish={handleOk}>
            <Form.Item label="Username" name="username"
                rules={[checkRequiredField('Please input your username!')]}>
                <Input />
            </Form.Item>

            <Form.Item label="Password" name="password"
                rules={[checkRequiredField('Please input your password!')]}>
                <Input.Password />
            </Form.Item>

            <Form.Item style={{textAlign: 'center'}}>
                <Button type="primary" htmlType="submit" size={'large'}>
                    Login
                </Button>
            </Form.Item>
        </Form>
    )
}

export default LoginForm