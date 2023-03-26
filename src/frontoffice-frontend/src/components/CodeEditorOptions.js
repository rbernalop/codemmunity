import {Menu} from "antd";

const CodeEditorOptions = ({language, setLanguage, languages}) => {

    const handleLanguageChange = (e) => {
        setLanguage(e.key);
    }

    return (
        <Menu
            mode="inline"
            defaultSelectedKeys={[language]}
            style={{ height: '100%', borderRight: 0 }}
        >
            {languages.map((language) => (
                <Menu.Item key={language.key} value={language.key} onClick={handleLanguageChange}>
                    {language.icon}
                </Menu.Item>
            ))}
        </Menu>
    )
}

export default CodeEditorOptions