import {sendEvent} from "../../producer";

export const sendChange = (stompClient, scriptId, username, change, changedScript) => {
    if (stompClient) {
        const event = `/service/v1/script/${scriptId}/change`
        const data = {username: username, change: change, changedScript: changedScript}
        sendEvent(stompClient, event, data)
    }
}