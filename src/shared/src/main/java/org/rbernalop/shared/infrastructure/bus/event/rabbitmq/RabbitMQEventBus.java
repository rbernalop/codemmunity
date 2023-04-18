package org.rbernalop.shared.infrastructure.bus.event.rabbitmq;

import lombok.AllArgsConstructor;
import org.rbernalop.shared.domain.bus.event.DomainEvent;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Primary
public class RabbitMQEventBus implements EventBus {
    private final AmqpTemplate amqpTemplate;
    private final RabbitMQProperties rabbitMQProperties;

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    public void publish(DomainEvent event) {
        amqpTemplate.convertAndSend(rabbitMQProperties.getExchange(), rabbitMQProperties.getRoutingKey(), event);
    }
}
