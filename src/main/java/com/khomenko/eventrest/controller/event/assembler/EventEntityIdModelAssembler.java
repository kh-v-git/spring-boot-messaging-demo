/*
 * Do not reproduce without permission in writing.
 * Copyright (c) 2023.
 */
package com.khomenko.eventrest.controller.event.assembler;

import com.khomenko.eventrest.controller.event.EventRestController;
import com.khomenko.eventrest.domain.EventEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EventEntityIdModelAssembler implements RepresentationModelAssembler<EventEntity, EntityModel<EventEntity>> {

    @Override
    public @NonNull EntityModel<EventEntity> toModel(@Nullable final EventEntity event) {

        Assert.notNull(event, "EventEntity must not be null");

        return EntityModel.of(event, linkTo(methodOn(EventRestController.class).getEventById(event.getEventId())).withSelfRel(),
                linkTo(methodOn(EventRestController.class).getAllEvents()).withRel("/api/v1/event"));
    }
}
