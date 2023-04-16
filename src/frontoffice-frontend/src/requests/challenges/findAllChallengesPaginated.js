import axios from "axios";

export const findAllChallengesPaginated = async (page, size) => {
    return await axios.get(`/api/v1/challenge?page=${page}&size=${size}`, {
        headers: {
            'Authorization': localStorage.getItem('token')
        }
    });
}
