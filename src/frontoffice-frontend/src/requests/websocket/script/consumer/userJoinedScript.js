import {listenEvent} from "../../consumer";

export const listenUsersJoined = (stompClient, scriptId, usersJoined, setUsersJoined, setScriptContent) => {
    const event = `/script/joined`

    const handler = (event) => {
        let data = JSON.parse(event.body);
        const username = localStorage.getItem('username');
        if (data.username === username) {
            setScriptContent(data.scriptContent);
        } else {
            setUsersJoined([...usersJoined, {name: data.username}]);
        }
    }

    listenEvent(stompClient, event, handler)
}
