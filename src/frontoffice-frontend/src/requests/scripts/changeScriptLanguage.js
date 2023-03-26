import axios from "axios";

export const changeScriptLanguage = async (id, language) => {
    return await axios.patch("/api/v1/script/" + id + "/language", {languageId: language}, {
        headers: {
            'Authorization': localStorage.getItem('token')
        }
    });
}