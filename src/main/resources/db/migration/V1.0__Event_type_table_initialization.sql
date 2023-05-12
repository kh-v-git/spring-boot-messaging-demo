CREATE TABLE event_type
(
    event_type_id BIGINT IDENTITY NOT NULL,
    event_type    VARCHAR(255),
    description   VARCHAR(255),
    PRIMARY KEY (event_type_id)
);
