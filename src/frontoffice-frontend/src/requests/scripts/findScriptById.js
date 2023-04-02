import axios from "axios";

const findScriptById = async (id) => {
    return await axios.get("/api/v1/script/" + id, {
        headers: {
            'Authorization': localStorage.getItem('token')
        }
    });
}

export default findScriptById;