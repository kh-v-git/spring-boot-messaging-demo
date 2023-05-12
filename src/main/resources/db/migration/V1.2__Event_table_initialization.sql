CREATE TABLE event
(
    event_id  BIGINT IDENTITY NOT NULL,
    title     VARCHAR(255)    NOT NULL,
    place     VARCHAR(255)    NOT NULL,
    speaker   VARCHAR(255)    NOT NULL,
    type_id   BIGINT          NOT NULL,
    date_time DATETIME2       NOT NULL,
    PRIMARY KEY (event_id),
    foreign key (type_id) references event_type (event_type_id)
);