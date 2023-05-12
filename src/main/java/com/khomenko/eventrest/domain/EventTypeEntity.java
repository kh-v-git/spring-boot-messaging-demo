/*
 * Do not reproduce without permission in writing.
 * Copyright (c) 2023.
 */
package com.khomenko.eventrest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity()
@Table(name = "event_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_type_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private EventTypeEnum eventType;

    @Column(name = "description")
    private String description;
}
