/*
 * Do not reproduce without permission in writing.
 * Copyright (c) 2023.
 */
package com.khomenko.eventrest.message.service.produce.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khomenko.eventrest.domain.EventEntity;
import com.khomenko.eventrest.message.service.produce.MessageProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("rabbitmq")
@Component
public class RabbitMqMessageProducerServiceImpl implements MessageProducerService {
    private final RabbitTemplate rabbitTemplate;

    @Value("${application.message.queue.create-event-queue}")
    String createQueue;

    @Value("${application.message.queue.update-event-queue}")
    String updateQueue;

    @Value("${application.message.queue.delete-event-queue}")
    String deleteQueue;

    public RabbitMqMessageProducerServiceImpl(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void createEventNotification(final EventEntity entity) {
        try {

            String jsonObj = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(entity);
            rabbitTemplate.convertAndSend(createQueue, jsonObj);

            log.info("Create message was send");
        } catch (AmqpException exc) {
            log.debug("Create message send error");
        } catch (JsonProcessingException exc) {
            log.debug("Entity deserialization message send error");
        }
    }

    @Override
    public void updateEventNotification(final EventEntity entity) {
        try {

            String jsonObj = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(entity);
            rabbitTemplate.convertAndSend(updateQueue, jsonObj);

            log.info("Update message was send");
        } catch (AmqpException exc) {
            log.debug("Update message send error");
        } catch (JsonProcessingException exc) {
            log.debug("Entity deserialization in update message send error");
        }
    }

    @Override
    public void deleteEventNotification(final EventEntity entity) {
        try {

            String jsonObj = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(entity);
            rabbitTemplate.convertAndSend(deleteQueue, jsonObj);

            log.info("Delete message was send");
        } catch (AmqpException exc) {
            log.debug("Delete message send error");
        } catch (JsonProcessingException exc) {
            log.debug("Entity deserialization in delete message send error");
        }
    }
}
