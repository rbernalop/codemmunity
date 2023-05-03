import {sendEvent} from "../../producer";

export const sendChallengeScriptContentChange = (stompClient, challengeId, username, change, changedScript) => {
    if (stompClient) {
        const event = `/service/v1/challengeScript/${challengeId}/changeContent`
        const data = {username: username, change: change, changedScript: changedScript}
        sendEvent(stompClient, event, data)
    }
}