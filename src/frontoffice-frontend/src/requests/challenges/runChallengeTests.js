import axios from 'axios';

export const runChallengeTests = (id, language, code) => {
    return axios.post(`/api/v1/test/check`, {
        challengeId: id,
        languageName: language,
        scriptContent: code
    }, {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': localStorage.getItem('token')
        }
    })
}
