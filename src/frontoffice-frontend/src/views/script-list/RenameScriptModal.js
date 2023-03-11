import {Button, Form, Input, Modal} from "antd";

const RenameScriptModal = ({ script, isOpen, onClose, onRename }) => {
    return (
        <Modal
            title="Rename script"
            open={isOpen}
            onCancel={onClose}
            okButtonProps={{style: {display: 'none'}}}
            cancelButtonProps={{style: {display: 'none'}}}
            closable={false} >
            <Form
                initialValues={{name: script.name}}
                onFinish={values => {
                    onRename(script.id, values.name);
                    onClose();
                }}
                >
                <Form.Item
                    name="name"
                    >
                    <Input />
                </Form.Item>
                <Form.Item>
                    <Button type="primary" htmlType="submit">ACCEPT</Button>
                </Form.Item>
            </Form>
        </Modal>
    );
}

export default RenameScriptModal