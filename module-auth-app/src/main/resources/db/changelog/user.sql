--liquibase formatted sql
--changeset Maxim Polyakov:1
CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    nickname VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO "user" (id, nickname, email, password)
VALUES (0, 'Tzitzi-Ya-Ku', 'admin@mail', '$2a$10$LGsGFVpJ16Z.SWcY00A52uJbHQf5JEuMEwCMfbA3KwEWhzqu6o.XS');