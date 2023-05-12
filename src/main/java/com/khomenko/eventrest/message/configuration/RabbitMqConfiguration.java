/*
 * Do not reproduce without permission in writing.
 * Copyright (c) 2023.
 */
package com.khomenko.eventrest.message.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("rabbitmq")
public class RabbitMqConfiguration {
    private final CachingConnectionFactory connectionFactory;

    @Value("${application.message.queue.create-event-queue}")
    String createQueue;

    @Value("${application.message.queue.update-event-queue}")
    String updateQueue;

    @Value("${application.message.queue.delete-event-queue}")
    String deleteQueue;

    public RabbitMqConfiguration(final CachingConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Bean
    public Queue createQueue() {
        return new Queue(createQueue, true);
    }

    @Bean
    public Queue updateQueue() {
        return new Queue(updateQueue, true);
    }

    @Bean
    public Queue deleteQueue() {
        return new Queue(deleteQueue, true);
    }

    @Bean
    public MessageConverter converter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();

        return converter;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate();
        template.setConnectionFactory(connectionFactory);
        template.setMessageConverter(converter());

        return template;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter() {
        return new MessageListenerAdapter("receiveMessage");
    }
}
