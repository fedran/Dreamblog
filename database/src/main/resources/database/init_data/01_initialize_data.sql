--liquibase formatted sql
--changeset zhuch:initialize

INSERT INTO roles (role_name) VALUES ('ADMIN'), ('USER');

INSERT INTO users (username, email, password_hash, role_id, created, updated)
VALUES ('admin', 'dev@zhuch.org', 'hash', 1, 'now'::timestamp, 'now'::timestamp),
       ('user', 'mail@zhuch.org', 'hash', 2, 'now'::timestamp, 'now'::timestamp);

INSERT INTO article (user_id, content, created, updated)
VALUES (1, 'content', 'now'::timestamp, 'now'::timestamp),
       (1, 'content1', 'now'::timestamp, 'now'::timestamp);

--rollback DELETE FROM roles;
--rollback DELETE FROM users;
--rollback DELETE FROM article;