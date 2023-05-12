SET IDENTITY_INSERT event_type ON;

INSERT INTO event_type (event_type_id, event_type, description)
VALUES (0,  'WORKSHOP', 'An Event that gives attendees hands-on experience'),
       (1,  'TECH_TALK', 'An Event that gives attendees technical experience');

SET IDENTITY_INSERT event_type OFF;