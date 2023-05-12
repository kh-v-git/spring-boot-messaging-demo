/*
 * Do not reproduce without permission in writing.
 * Copyright (c) 2023.
 */
package com.khomenko.eventrest.message.service.consume.impl;

import com.khomenko.eventrest.message.service.consume.MessageConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("rabbitmq")
public class RabbitMqMessageConsumerServiceImpl implements MessageConsumerService {

    @RabbitListener(queues = "${application.message.queue.create-event-queue}")
    @Override
    public void createEventRequest(final String jsonMessage) {

        log.info("Creation message received: {}", jsonMessage);

    }

    @RabbitListener(queues = "${application.message.queue.update-event-queue}")
    @Override
    public void updateEventRequest(final String jsonMessage) {

        log.info("Update message received: {}", jsonMessage);

    }

    @RabbitListener(queues = "${application.message.queue.delete-event-queue}")
    @Override
    public void deleteEventRequest(final String jsonMessage) {

        log.info("Delete message received: {}", jsonMessage);

    }
}
