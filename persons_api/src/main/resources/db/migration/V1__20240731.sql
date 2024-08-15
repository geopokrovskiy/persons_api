-- This is a migration file for creation of schemas and tables

CREATE SCHEMA IF NOT EXISTS person;

CREATE TABLE IF NOT EXISTS person.countries
(
    id      SERIAL PRIMARY KEY,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    name    VARCHAR(64),
    alpha2  VARCHAR(2),
    alpha3  VARCHAR(3),

    CONSTRAINT unique_country_name UNIQUE (name),
    CONSTRAINT unique_alpha2 UNIQUE (alpha2),
    CONSTRAINT unique_alpha3 UNIQUE (alpha3)
);

CREATE TABLE IF NOT EXISTS person.addresses
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    country_id INTEGER REFERENCES person.countries (id),
    address    VARCHAR(128),
    zip_code   VARCHAR(32),
    archived   TIMESTAMP ,
    city       VARCHAR(32),
    state      VARCHAR(32)
);

CREATE TABLE IF NOT EXISTS person.users
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    secret_key  VARCHAR(32),
    created     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    first_name  VARCHAR(32),
    last_name   VARCHAR(32),
    verified_at TIMESTAMP,
    archived_at TIMESTAMP,
    status      VARCHAR(64),
    filled      BOOLEAN,
    address_id  UUID REFERENCES person.addresses (id)
);

CREATE TABLE IF NOT EXISTS person.merchants
(
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    company_name VARCHAR(32),
    company_id   VARCHAR(32),
    email        VARCHAR(32),
    phone_number VARCHAR(32),
    verified_at  TIMESTAMP,
    archived_at  TIMESTAMP,
    status       VARCHAR(32),
    filled       BOOLEAN
);

CREATE TABLE IF NOT EXISTS person.merchant_members
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id     UUID UNIQUE REFERENCES person.users (id),
    merchant_id UUID REFERENCES person.merchants (id),
    member_role VARCHAR(32),
    status      VARCHAR(32)
);

CREATE TABLE IF NOT EXISTS person.individuals
(
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id         UUID UNIQUE REFERENCES person.users (id),
    passport_number VARCHAR(32),
    phone_number    VARCHAR(32),
    email           VARCHAR(32)
);

CREATE TABLE IF NOT EXISTS person.verification_statuses
(
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created             TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated             TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    profile_id          UUID REFERENCES person.users (id),
    profile_type        VARCHAR(32),
    details             VARCHAR(255),
    verification_status VARCHAR(32)
);

CREATE TABLE IF NOT EXISTS person.profile_history
(
    id             UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    profile_id     UUID REFERENCES person.users (id),
    profile_type   VARCHAR(32),
    reason         VARCHAR(255),
    comment        VARCHAR(255),
    changed_values VARCHAR(1024)
);

CREATE TABLE IF NOT EXISTS person.merchant_members_invitations
(
    id      UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    merchant_id UUID REFERENCES person.merchants(id),
    first_name VARCHAR(32),
    last_name VARCHAR(32),
    email VARCHAR(32),
    status VARCHAR(32)
);



