/*
 * Do not reproduce without permission in writing.
 * Copyright (c) 2023.
 */
package com.khomenko.eventrest.controller.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.khomenko.eventrest.controller.event.assembler.EventEntityIdModelAssembler;
import com.khomenko.eventrest.domain.EventEntity;
import com.khomenko.eventrest.message.service.produce.MessageProducerService;
import com.khomenko.eventrest.service.EventService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/api/v1/" })
public class EventRestController {
    private final EventService eventService;
    private final EventEntityIdModelAssembler assembler;
    private final MessageProducerService messageService;

    public EventRestController(EventService eventService, final EventEntityIdModelAssembler assembler, final MessageProducerService messageService) {
        this.eventService = eventService;
        this.assembler = assembler;
        this.messageService = messageService;
    }

    @GetMapping({ "/event" })
    public CollectionModel<EntityModel<EventEntity>> getAllEvents() {
        return assembler.toCollectionModel(eventService.getAllEvents());
    }

    @GetMapping({ "/event/{id}" })
    public EntityModel<EventEntity> getEventById(@PathVariable final Long id) {
        return assembler.toModel(eventService.getEventById(id));
    }

    @PostMapping({ "/event" })
    public ResponseEntity<?> createEvent(@RequestBody final EventEntity eventEntity) throws JsonProcessingException {
        EventEntity savedEntity = eventService.createEvent(eventEntity);
        EntityModel<EventEntity> eventModel = assembler.toModel(savedEntity);

        messageService.createEventNotification(savedEntity);

        return ResponseEntity.created(eventModel.getRequiredLink("self").toUri()).body(eventModel);
    }

    @PutMapping({ "/event/{id}" })
    public ResponseEntity<?> updateEventById(@PathVariable final Long id, @RequestBody final EventEntity eventEntity) {
        EventEntity updatedEntity = eventService.updateEventById(id, eventEntity);
        EntityModel<EventEntity> eventModel = assembler.toModel(updatedEntity);

        messageService.updateEventNotification(updatedEntity);

        return ResponseEntity.created(eventModel.getRequiredLink("self").toUri()).body(eventModel);
    }

    @DeleteMapping({ "/event/{id}" })
    public ResponseEntity<?> deleteEventById(@PathVariable final Long id) {
        EventEntity deletedEntity = eventService.deleteEventById(id);

        messageService.deleteEventNotification(deletedEntity);

        return ResponseEntity.noContent().build();
    }
}
