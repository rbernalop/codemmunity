import axios from 'axios';

export const findChallengeById = async (challengeId, languageName) => {
    return await axios.get(`/api/v1/challenge/${challengeId}?languageName=${languageName}`, {
            headers: {Authorization: localStorage.getItem('token')}
    });
}