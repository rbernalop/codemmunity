
export const sendEvent=(stompClient, event, data)=>{
    if (stompClient) {
        stompClient.send(event, {}, JSON.stringify(data));
    }
}