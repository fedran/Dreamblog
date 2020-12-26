--liquibase formatted sql
--changeset zhuch:release/0.1 tag:release/0.1

CREATE TABLE comments (
    comment_id bigserial PRIMARY KEY,
    article_id bigint REFERENCES article,
    content text NOT NULL,
    created timestamp NOT NULL DEFAULT NOW(),
    updated timestamp NOT NULL
);

COMMENT ON TABLE comments IS 'Комментарии';

--rollback DROP TABLE comments;