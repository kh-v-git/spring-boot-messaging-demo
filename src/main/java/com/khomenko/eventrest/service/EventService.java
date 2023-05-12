package com.khomenko.eventrest.service;

import java.util.List;

import com.khomenko.eventrest.domain.EventEntity;
import com.khomenko.eventrest.utils.exception.ApplicationBusinessException;

public interface EventService {

    EventEntity createEvent(EventEntity eventEntity) throws ApplicationBusinessException;

    EventEntity updateEventById(Long id, EventEntity eventEntity) throws ApplicationBusinessException;

    EventEntity getEventById(Long id) throws ApplicationBusinessException;

    EventEntity deleteEventById(Long id) throws ApplicationBusinessException;

    List<EventEntity> getAllEvents() throws ApplicationBusinessException;
}
