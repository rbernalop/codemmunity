import axios from "axios";

export const createScript = async (script) => {
    return await axios.post("/api/v1/script", script, {
        headers: {
            'Authorization': localStorage.getItem('token')
        }
    });
}