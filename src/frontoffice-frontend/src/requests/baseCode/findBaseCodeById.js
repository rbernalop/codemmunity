import axios from 'axios';

export const findBaseCodeById = (languageName, challengeId) => {
    return axios.get(`/api/v1/baseCode?languageName=${languageName}&challengeId=${challengeId}`,
        {headers: {Authorization: localStorage.getItem("token")}}
        )
        .then(response => response.data)
        .catch(error => {
            throw error;
        });
}