--liquibase formatted sql
--changeset zhuch:initialize

INSERT INTO roles (role_name) VALUES ('ADMIN'), ('USER');

INSERT INTO users (username, email, password_hash, role_id, created, updated)
VALUES ('admin', 'dev@zhuch.org', 'hash', 1, 'now'::timestamp, 'now'::timestamp),
       ('user', 'mail@zhuch.org', 'hash', 2, 'now'::timestamp, 'now'::timestamp);

INSERT INTO articles (user_id, caption, content, created, updated, likes, dislikes, views)
VALUES (1, 'caption #1', 'content #1', 'now'::timestamp, 'now'::timestamp, 0, 0, 0),
       (1, 'caption #2', 'content1 #2', 'now'::timestamp, 'now'::timestamp, 0, 0, 0);

INSERT INTO comments (article_id, user_id, content, created, updated, likes, dislikes)
VALUES (1, 1, 'comment to the first article', 'now'::timestamp, 'now'::timestamp, 0, 0),
       (2, 2, 'comment to the second article', 'now'::timestamp, 'now'::timestamp, 0, 0)

--rollback DELETE FROM roles;
--rollback DELETE FROM users;
--rollback DELETE FROM articles;
--rollback DELETE FROM comments;