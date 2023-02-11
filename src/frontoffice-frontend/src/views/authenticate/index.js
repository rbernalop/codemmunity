import {Form, Modal, Tabs} from "antd";
import RegisterForm from "./RegisterForm";

const Authenticate = ({formTab, setFormTab, showModal, setShowModal}) => {
    const [form] = Form.useForm();

    const tabItems = [
        {key: 'login', label: 'Login', children: <></>},
        {key: 'register', label: 'Register', children: <RegisterForm form={form} setFormTab={setFormTab} />}
    ];


    return (
        <Modal
            title="Authentication Form"
            open={showModal}
            onCancel={() => setShowModal(false)}
            okButtonProps={{style: {display: 'none'}}}
            cancelButtonProps={{style: {display: 'none'}}}
            closable={false} >
            <Tabs
                activeKey={formTab}
                items={tabItems}
                onChange={key => setFormTab(key)} />
        </Modal>
    );
}

export default Authenticate