/*
 * Do not reproduce without permission in writing.
 * Copyright (c) 2023.
 */
package com.khomenko.eventrest.service;

import java.util.List;
import java.util.Optional;

import com.khomenko.eventrest.domain.EventEntity;
import com.khomenko.eventrest.repository.EventRepository;
import com.khomenko.eventrest.utils.exception.ApplicationBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Slf4j
@Transactional(readOnly = true)
public class EventServiceIml implements EventService {
    private final EventRepository eventRepository;

    public EventServiceIml(final EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    @Override
    public EventEntity createEvent(final EventEntity eventEntity) throws ApplicationBusinessException {
        Assert.notNull(eventEntity, "Event must not be null");

        if (eventEntity.getEventId() == null) {
            return eventRepository.save(eventEntity);
        } else {
            throw new ApplicationBusinessException(String.format("Event with id: %s already exists", eventEntity.getEventId()));
        }
    }

    @Transactional
    @Override
    public EventEntity updateEventById(final Long id, final EventEntity eventEntity) throws ApplicationBusinessException {
        return eventRepository.findById(id).map(event -> {
            event.setTitle(eventEntity.getTitle());
            event.setPlace(eventEntity.getPlace());
            event.setSpeaker(eventEntity.getSpeaker());
            event.setEventType(eventEntity.getEventType());
            event.setDateTime(eventEntity.getDateTime());
            return eventRepository.save(event);
        }).orElseThrow(() -> new ApplicationBusinessException(String.format("Update process failed. No event with id: %s", id)));
    }

    @Override
    public EventEntity getEventById(final Long id) throws ApplicationBusinessException {
        return eventRepository.findById(id).orElseThrow(() -> new ApplicationBusinessException(String.format("No event with id: %s", id)));
    }

    @Transactional
    @Override
    public EventEntity deleteEventById(final Long id) throws ApplicationBusinessException {
        Optional<EventEntity> maybeEntity = eventRepository.findById(id);

        if (maybeEntity.isPresent()) {
            eventRepository.deleteById(id);

            return maybeEntity.get();
        } else {
            throw new ApplicationBusinessException(String.format("Delete process failed. No event with id: %s", id));
        }
    }

    @Override
    public List<EventEntity> getAllEvents() throws ApplicationBusinessException {
        List<EventEntity> events = eventRepository.findAll();
        if (!events.isEmpty()) {
            return eventRepository.findAll();
        }
        throw new ApplicationBusinessException("No events found");
    }
}



