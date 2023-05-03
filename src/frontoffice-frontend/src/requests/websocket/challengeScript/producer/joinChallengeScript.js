import {sendEvent} from "../../producer";

export const joinChallengeScript = (stompClient, challengeId, username) => {
    const event = `/service/v1/challengeScript/${challengeId}/join`
    const data = {username: username}
    sendEvent(stompClient, event, data)
}
