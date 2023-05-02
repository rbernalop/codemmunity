import {over} from 'stompjs';
import SockJS from 'sockjs-client';

export const connect =(onConnnect, onConnectionError, path)=>{
    const apiGatewayHost = process.env.REACT_APP_API_GATEWAY_HOST || 'http://localhost';
    const apiGatewayPort = process.env.REACT_APP_API_GATEWAY_PORT || '8084';
    const connectionUrl = `${apiGatewayHost}:${apiGatewayPort}`;
    let Sock = new SockJS(connectionUrl + path);
    let stompClient = over(Sock);
    stompClient.debug = null
    stompClient.connect({}, () => onConnnect(stompClient), onConnectionError);
}