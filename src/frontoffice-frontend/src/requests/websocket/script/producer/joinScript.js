import {sendEvent} from "../../producer";

export const joinScript = (stompClient, scriptId, username) => {
    const event = `/service/v1/script/${scriptId}/join`
    const data = {username: username}
    sendEvent(stompClient, event, data)
}
