import {listenEvent} from "../../consumer";

export const listenScriptChanges = (stompClient, scriptId, updateCode) => {
    const event = `/script/changed`

    const handler = (event) => {
        let data = JSON.parse(event.body);
        const username = localStorage.getItem('username');
        if (data.username === username) return
        updateCode(data.change);
    }

    listenEvent(stompClient, event, handler)
}