import {Button, Form, Input} from "antd";
import {checkRequiredField} from "../../utils/formValidators";
import login from "../../requests/authentication/login";
import {errorNotification} from "../../utils/notification";

const LoginForm = () => {

    const [form] = Form.useForm();

    const handleOk = () => {
        form.validateFields()
            .then(values => {
                console.log(values)
                login(values).then(r => {
                    localStorage.setItem('token', r.headers.authorization);
                    localStorage.setItem('id', r.data.id);
                    localStorage.setItem('username', r.data.username);
                    localStorage.setItem('email', r.data.email);
                    window.location.reload();
                }).catch(e =>
                    errorNotification("Error while log in", e.response.data.message, "topRight")
                );
            }).catch(() =>
                errorNotification("Error while log in", "Please fill all the fields", "topRight")
        );
    };

    return (
        <Form form={form} onFinish={handleOk}>
            <Form.Item label="Username" name="username"
                rules={[checkRequiredField('Please input your username!')]}>
                <Input id="username-login"/>
            </Form.Item>

            <Form.Item label="Password" name="password"
                rules={[checkRequiredField('Please input your password!')]}>
                <Input.Password id="password-login"/>
            </Form.Item>

            <Form.Item style={{textAlign: 'center'}}>
                <Button type="primary" htmlType="submit" size={'large'} id="login-submit">
                    Login
                </Button>
            </Form.Item>
        </Form>
    )
}

export default LoginForm