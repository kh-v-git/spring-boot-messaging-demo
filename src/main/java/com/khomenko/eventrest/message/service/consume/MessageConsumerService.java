package com.khomenko.eventrest.message.service.consume;

public interface MessageConsumerService {
    void createEventRequest(final String jsonMessage);

    void updateEventRequest(final String jsonMessage);

    void deleteEventRequest(final String jsonMessage);
}
