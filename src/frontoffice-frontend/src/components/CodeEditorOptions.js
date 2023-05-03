import {Menu} from "antd";
import {LANGUAJES} from "../constants/languages";

const CodeEditorOptions = ({language, changeLanguage}) => {

    const handleLanguageChange = (e) => {
        changeLanguage(e.key);
    }

    return (
        <Menu
            mode="inline"
            defaultSelectedKeys={[language]}
            style={{ height: '100%', borderRight: 0 }}
        >
            {LANGUAJES.map((language) => (
                <Menu.Item key={language.key} value={language.key} onClick={handleLanguageChange}>
                    {language.icon}
                </Menu.Item>
            ))}
        </Menu>
    )
}

export default CodeEditorOptions