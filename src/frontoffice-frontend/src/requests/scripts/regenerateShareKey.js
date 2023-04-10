import axios from "axios";

const regenerateShareKey = async (scriptId, nextShareKey) => {
    return await axios.patch(`/api/v1/script/${scriptId}/shareKey`,
        {
            shareKey: nextShareKey
        }, {
        headers: {
            'Authorization': localStorage.getItem('token')
        }
    });
}

export default regenerateShareKey;