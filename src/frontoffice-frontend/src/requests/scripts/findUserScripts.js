import axios from "axios";

const findUserScripts = async (page, size) => {
    return await axios.get("/api/v1/script", {
        headers: {
            'Authorization': localStorage.getItem('token')
        },
        params: {
            page: page,
            size: size
        }
    });
}

export default findUserScripts;