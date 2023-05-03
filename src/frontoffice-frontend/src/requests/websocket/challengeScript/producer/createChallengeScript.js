import {sendEvent} from "../../producer";

export const createChallengeScript = (stompClient, challengeId, username, language, content) => {
    const event = `/service/v1/challengeScript/${challengeId}/create`
    const data = {username: username, languageName: language, content: content}
    sendEvent(stompClient, event, data)
}
