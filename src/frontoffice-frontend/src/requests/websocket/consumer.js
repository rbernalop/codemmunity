
export const listenEvent = (stompClient, event, handler) => {
    if (stompClient) {
        stompClient.subscribe(event, handler);
    }
}
