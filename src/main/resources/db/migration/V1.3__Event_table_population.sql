SET IDENTITY_INSERT event ON;

INSERT INTO event (event_id, title, place, speaker, type_id, date_time)
VALUES (0, 'Event 1', 'ChatGPT', 'Speaker 1', 0, '2024-11-01 00:00:00'),
       (1, 'Event 2', 'GIT Copilot', 'Speaker 2', 1, '2025-09-12 00:00:00'),
       (2, 'Event 3', 'AWS Whisper', 'Speaker 3', 0, '2024-05-21 00:00:00'),
       (3, 'Event 4', 'Hand Job', 'Speaker 4', 1, '2023-11-11 00:00:00');

SET IDENTITY_INSERT event OFF;
