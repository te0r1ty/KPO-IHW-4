--liquibase formatted sql
--changeset Maxim Polyakov:3
CREATE TABLE "station" (
    id SERIAL PRIMARY KEY,
    station VARCHAR(50) NOT NULL
);

INSERT INTO station (id, station)
VALUES
    (0, 'KursKaya'),
    (1, 'Tovarnaya'),
    (2, 'Mahach Kala'),
    (3, 'Astera'),
    (4, 'Volgograd'),
    (5, 'Zhelezka');