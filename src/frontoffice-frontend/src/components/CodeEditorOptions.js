import {Menu} from "antd";
import {changeScriptLanguage} from "../requests/scripts/changeScriptLanguage";
import {errorNotification} from "../utils/notification";

const CodeEditorOptions = ({id, language, setLanguage, languages}) => {

    const changeLanguage = (languageKey) => {
        const language = languages.find(language => language.key === languageKey);
        changeScriptLanguage(id, language.id).then(() => {
            setLanguage(language);
        }).catch(e =>
            errorNotification("Error changing language", e.response.data.message || "Try again later", "topRight")
        );
    }

    const handleLanguageChange = (e) => {
        changeLanguage(e.key);
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