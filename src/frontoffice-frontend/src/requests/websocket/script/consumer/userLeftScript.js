import {listenEvent} from "../../consumer";

export const listenUsersLeft = (stompClient, scriptId, usersJoined, setUsersJoined) => {
    const event = `/script/left`

    const handler = (event) => {
        let data = JSON.parse(event.body);
        const username = localStorage.getItem('username');
        if (data.username === username) return
        setUsersJoined(usersJoined.filter(user => user.name !== data.username));
    }

    listenEvent(stompClient, event, handler)
}