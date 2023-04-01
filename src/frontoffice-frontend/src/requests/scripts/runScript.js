import axios from "axios";

export const runScript = async (script, languageId) => {
    return await axios.post("/api/v1/script/run",
        {
            script: script,
            languageId: languageId
        }
        , {
        headers: {
            'Authorization': localStorage.getItem('token')
        }
    });
}
