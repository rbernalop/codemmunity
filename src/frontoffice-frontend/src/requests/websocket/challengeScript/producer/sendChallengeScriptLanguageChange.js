import {sendEvent} from "../../producer";

export const sendChallengeScriptLanguageChange = (stompClient, challengeId, username, language) => {
    if (stompClient) {
        const event = `/service/v1/challengeScript/${challengeId}/changeLanguage`
        const data = {username: username, language: language}
        sendEvent(stompClient, event, data)
    }
}