import axios from "axios";

export const renameScript = async (scriptId, newName) => {
    return await axios.patch(
        `/api/v1/script/${scriptId}`,
        {
            name: newName,
        },
        {
            headers: {
                'Authorization': localStorage.getItem('token')
            }
        }
    );
}