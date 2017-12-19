--liquibase formatted sql
--changeset author:piotr

BEGIN TRANSACTION;

CREATE SEQUENCE hibernate_sequence;

CREATE TABLE person (
    id BIGINT PRIMARY KEY,
    name VARCHAR (255) NOT NULL,
    email VARCHAR (254) UNIQUE,
    gender VARCHAR (255) NOT NULL,
    insured BOOLEAN NOT NULL,
    children_number BIGINT NOT NULL CHECK(children_number >= 0)
);

CREATE INDEX person_name_index ON person USING btree (name);

CREATE TABLE address (
    id BIGINT PRIMARY KEY,
    city VARCHAR(255) NOT NULL,
    street VARCHAR(255),
    house_number VARCHAR(16) NOT NULL,
    postal_code VARCHAR(16) NOT NULL,
    CONSTRAINT address_unique UNIQUE (city, street, house_number, postal_code)
);

CREATE INDEX address_city_index ON address USING btree (city);
CREATE INDEX address_postal_code_index ON address USING btree (postal_code);

CREATE TABLE car (
    id BIGINT PRIMARY KEY,
    registration_number VARCHAR(16) UNIQUE,
    person_id BIGINT REFERENCES person(id) ON DELETE SET NULL
);

CREATE TABLE address_person (
    address_id BIGINT REFERENCES address(id) ON DELETE CASCADE,
    person_id BIGINT REFERENCES person(id) ON DELETE CASCADE,
    PRIMARY KEY (address_id, person_id)
);

COMMIT TRANSACTION;

--rollback DROP SEQUENCE hibernate_sequence; DROP TABLE address_person; DROP TABLE person CASCADE; DROP TABLE address CASCADE; DROP TABLE car CASCADE;
