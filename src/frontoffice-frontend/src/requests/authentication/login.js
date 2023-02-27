import axios from "axios";

const login = async (data) => {
    return await axios.post("/api/v1/login", data);
}

export default login;