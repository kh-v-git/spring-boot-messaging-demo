package com.khomenko.eventrest.message.service.produce;

import com.khomenko.eventrest.domain.EventEntity;

public interface MessageProducerService {
    void createEventNotification(final EventEntity entity);

    void updateEventNotification(final EventEntity entity);

    void deleteEventNotification(final EventEntity entity);
}
