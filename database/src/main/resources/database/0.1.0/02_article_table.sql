--liquibase formatted sql
--changeset zhuch:release/0.1 tag:release/0.1

CREATE TABLE article (
    article_id bigserial PRIMARY KEY,
    user_id bigint REFERENCES users,
    caption text NOT NULL,
    content text NOT NULL,
    created timestamp NOT NULL DEFAULT NOW(),
    updated timestamp NOT NULL,
    likes int,
    dislikes int,
    views int
);

COMMENT ON TABLE article IS 'Статьи в блоге';

--rollback DROP TABLE article;