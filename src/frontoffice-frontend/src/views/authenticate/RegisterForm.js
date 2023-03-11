import {Button, DatePicker, Form, Input} from "antd";
import {generateUUID} from "../../utils/uuid";
import register from "../../requests/authentication/register";
import {errorNotification, infoNotification} from "../../utils/notification";
import {checkAgeIsOver18, checkEmail, checkPasswordsMatch, checkRequiredField} from "../../utils/formValidators";
import Captcha from "../../components/Captcha";
import {useRef} from "react";

const RegisterForm = ({setFormTab}) => {

    const [form] = Form.useForm();
    const captchaRef = useRef();

    const handleOk = () => {
        form.validateFields()
            .then(values => {
                values.id = generateUUID();
                values.birthDate = values.birthDate.format('YYYY-MM-DD');
                values.confirmPassword = undefined;
                values.captchaToken = captchaRef.current.getValue();
                register(values).then(() => {
                    infoNotification('Register', 'You have successfully registered. Now you can log in.', 'topRight')
                    form.resetFields();
                    setFormTab('login');
                }).catch(e =>
                    errorNotification("Error while registering", e.response.data.message, "topRight")
                );

            }).catch(e => {
                errorNotification("Error while registering", e, "topRight")
            }
            );
    };

    return (
        <Form form={form} onFinish={handleOk}>
            <Form.Item label="Name" name="name"
                rules={[checkRequiredField('Please input your name!')]}>
                <Input />
            </Form.Item>

            <Form.Item label="Surname" name="surname"
                rules={[checkRequiredField('Please input your surname!')]}
            >
                <Input />
            </Form.Item>

            <Form.Item label="Username" name="username"
                rules={[checkRequiredField('Please input your username!')]}>
                <Input />
            </Form.Item>

            <Form.Item label="Email" name="email"
                rules={[checkRequiredField('Please input your email!'), checkEmail()]}>
                <Input />
            </Form.Item>

            <Form.Item label="Password" name="password"
                rules={[checkRequiredField('Please input your password!')]}>
                <Input.Password />
            </Form.Item>

            <Form.Item label="Confirm Password" name="confirmPassword" dependencies={['password']} hasFeedback
                rules={[checkRequiredField('Please confirm your password!'), checkPasswordsMatch]}>
                <Input.Password />
            </Form.Item>

            <Form.Item label="Birthdate" name="birthDate"
                rules={[checkRequiredField('Please input your birthdate!'), checkAgeIsOver18]}>
                <DatePicker />
            </Form.Item>

            <Captcha captchaRef={captchaRef} />

            <Form.Item style={{textAlign: 'center', marginTop: '20px'}}>
                <Button type="primary" htmlType="submit" size={'large'}>
                    Register
                </Button>
            </Form.Item>
        </Form>
    )
}

export default RegisterForm