package org.rbernalop.servicesynchronization.script.infrasctructure.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.rbernalop.servicesynchronization.script.domain.aggregate.Script;
import org.rbernalop.servicesynchronization.script.domain.aggregate.ScriptMother;
import org.rbernalop.servicesynchronization.script.domain.port.ScriptRepository;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;

@Disabled
class UserJoinSocketTest extends IntegrationTestCase {
    @Autowired
    private ScriptRepository scriptRepository;
    private StompSession session;

    @BeforeEach
    void setUp()  throws ExecutionException, InterruptedException, TimeoutException {
        WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient(
            List.of(new WebSocketTransport(new StandardWebSocketClient()))
        ));

        String url = String.format("ws://localhost:%d/service/v1", 8083);
        this.session = stompClient
            .connectAsync(url, new StompSessionHandlerAdapter() {})
            .get(1, SECONDS);
    }

    @Test
    void should_join_user_to_script() throws JsonProcessingException {
        // GIVEN
        UserJoinSocketRequest userJoinSocketRequest = UserJoinSocketRequestMother.random();
        Script script = ScriptMother.random();
        assertNotNull(script.getId());
        scriptRepository.save(script);

        // WHEN
        String request = objectMapper.writeValueAsString(userJoinSocketRequest);
        this.session.send("/service/v1/script/" + script.getId().getValue() + "/join", request.getBytes());
    }
}