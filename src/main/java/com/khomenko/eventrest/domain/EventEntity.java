/*
 * Do not reproduce without permission in writing.
 * Copyright (c) 2023.
 */
package com.khomenko.eventrest.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity()
@Table(name = "event")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventEntity {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @NotNull(message = "Title is required")
    @Column(name = "title")
    private String title;

    @NotNull(message = "Place is required")
    @Column(name = "place")
    private String place;

    @NotNull(message = "Speaker is required")
    @Column(name = "speaker")
    private String speaker;

    @NotNull(message = "Event type is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private EventTypeEntity eventType;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @NotNull(message = "Date time is required")
    @Column(name = "date_time")
    private LocalDateTime dateTime;
}
