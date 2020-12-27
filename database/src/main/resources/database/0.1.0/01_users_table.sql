--liquibase formatted sql
--changeset zhuch:release/0.1

CREATE TABLE roles (
    role_id SERIAL PRIMARY KEY,
    role_name varchar(255) NOT NULL
);

COMMENT ON TABLE roles IS 'Роли пользователей';

CREATE TABLE users (
    user_id bigserial PRIMARY KEY,
    username varchar NOT NULL,
    email varchar NOT NULL,
    password_hash varchar NOT NULL,
    role_id integer REFERENCES roles,
    created timestamp NOT NULL DEFAULT NOW(),
    updated timestamp NOT NULL
);

COMMENT ON TABLE users IS 'Пользователи блога';

ALTER TABLE users
    ADD CONSTRAINT email_val
        CHECK (email ~* '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$');

COMMENT ON CONSTRAINT email_val ON users IS 'Только email-ы вида <имя>@<домен>.<зона>';

--rollback DROP TABLE users;
--rollback DROP TABLE roles;