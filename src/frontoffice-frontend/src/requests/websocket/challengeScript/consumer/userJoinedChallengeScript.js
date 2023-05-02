import {listenEvent} from "../../consumer";

export const listenChallengeScriptJoin = (stompClient, challengeId, setScriptContent, setLanguage, createChallengeScript) => {
    const event = `/challengeScript/joined`

    const handler = (event) => {
        let data = JSON.parse(event.body);
        const username = localStorage.getItem('username');
        console.log(data)
        if (data.username !== username || data.challengeId !== challengeId) {
            return null;
        }

        if(data.content === null || data.languageName === null) {
            createChallengeScript();
            return null;
        }
        setScriptContent(data.content);
        setLanguage(data.languageName);
        return data;
    }

    listenEvent(stompClient, event, handler)
}
