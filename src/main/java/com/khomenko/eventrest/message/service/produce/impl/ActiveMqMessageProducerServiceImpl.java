/*
 * Do not reproduce without permission in writing.
 * Copyright (c) 2023.
 */
package com.khomenko.eventrest.message.service.produce.impl;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khomenko.eventrest.domain.EventEntity;
import com.khomenko.eventrest.message.service.produce.MessageProducerService;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("activemq")
@Component
public class ActiveMqMessageProducerServiceImpl implements MessageProducerService {

    private final JmsTemplate jmsTemplate;

    @Value("${application.message.queue.create-event-queue}")
    String createQueue;

    @Value("${application.message.queue.update-event-queue}")
    String updateQueue;

    @Value("${application.message.queue.delete-event-queue}")
    String deleteQueue;

    public ActiveMqMessageProducerServiceImpl(final JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void createEventNotification(final EventEntity entity) {
        Optional<String> jsonObj = mapEntity(entity);

        jsonObj.ifPresent(json -> {
            sendMessage(createQueue, json);

            log.info("Create info message was send");
        });
    }

    @Override
    public void updateEventNotification(final EventEntity entity) {
        Optional<String> jsonObj = mapEntity(entity);

        jsonObj.ifPresent(json -> {
            sendMessage(updateQueue, json);

            log.info("Update info message was send");
        });
    }

    @Override
    public void deleteEventNotification(final EventEntity entity) {
        Optional<String> jsonObj = mapEntity(entity);

        jsonObj.ifPresent(json -> {

            sendMessage(deleteQueue, json);

            log.info("Delete info message was send");
        });
    }

    private Optional<String> mapEntity(final EventEntity entity) {
        try {
            String jsonObj = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(entity);

            log.debug("Mapping entity to JSON message was send successful");

            return Optional.of(jsonObj);
        } catch (JsonProcessingException exc) {
            log.debug("Error mapping entity to JSON  when sending Notification wit exception: {}", exc.getMessage());

            return Optional.empty();
        }
    }

    private void sendMessage(final String queue, String message) {
        try {
            jmsTemplate.send(queue, messageCreator -> {
                TextMessage textMessage = messageCreator.createTextMessage();
                textMessage.setText(message);

                return textMessage;
            });
        } catch (JmsException e) {
            log.debug("Message send error");
        }
    }
}

