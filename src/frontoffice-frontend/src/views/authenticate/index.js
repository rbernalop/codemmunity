import {Modal, Tabs} from "antd";
import RegisterForm from "./RegisterForm";
import LoginForm from "./LoginForm";

const Authenticate = ({formTab, setFormTab, showModal, setShowModal}) => {

    const tabItems = [
        {key: 'login', label: 'Login', children: <LoginForm />},
        {key: 'register', label: 'Register', children: <RegisterForm setFormTab={setFormTab} />}
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