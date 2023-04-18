package org.rbernalop.shared.domain;


import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.lang.annotation.*;

@RabbitListener(queues = "${spring.rabbitmq.queue}")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Inherited
public @interface EventListener {
}
