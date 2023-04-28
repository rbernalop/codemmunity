import axios from 'axios';

export const findChallengeById = async (challengeId) => {
    return await axios.get(`/api/v1/challenge/${challengeId}`, {
            headers: {Authorization: localStorage.getItem('token')}
    });
}