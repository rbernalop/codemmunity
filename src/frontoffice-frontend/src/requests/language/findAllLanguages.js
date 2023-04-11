import axios from 'axios';

const findAllLanguages = async () => {
    return await axios.get('/api/v1/language',
        {
            headers: {
                'Authorization': localStorage.getItem('token')
            }
        }
    );
}

export default findAllLanguages;