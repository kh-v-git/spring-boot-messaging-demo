/*
 * Do not reproduce without permission in writing.
 * Copyright (c) 2023.
 */
package com.khomenko.eventrest.message.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("kafka")
public class KafkaConfiguration {
    private final KafkaProperties kafkaProperties;

    @Value("${application.message.queue.create-event-queue}")
    String createQueue;

    @Value("${application.message.queue.update-event-queue}")
    String updateQueue;

    @Value("${application.message.queue.delete-event-queue}")
    String deleteQueue;

    public KafkaConfiguration(final KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public NewTopic createTopic() {
        return new NewTopic(createQueue, 2, (short) 1);
    }

    @Bean
    public NewTopic updateTopic() {
        return new NewTopic(updateQueue, 2, (short) 1);
    }

    @Bean
    public NewTopic deleteTopic() {
        return new NewTopic(deleteQueue, 2, (short) 1);
    }
}
