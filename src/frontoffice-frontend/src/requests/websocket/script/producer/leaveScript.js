import {sendEvent} from "../../producer";

export const leaveScript = (stompClient, scriptId, username) => {
    if (stompClient) {
        const event = `/service/v1/script/${scriptId}/leave`
        const data = {username: username}
        sendEvent(stompClient, event, data)
    }
}
