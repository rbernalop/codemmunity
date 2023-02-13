import axios from 'axios';

const register = async (data) => {
    const response = await axios.post('/api/v1/user', data);
    return response.data;
}

export default register;