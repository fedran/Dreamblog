--liquibase formatted sql
--changeset zhuch:release/0.1 tag:release/0.1

CREATE TABLE articles (
    article_id bigserial PRIMARY KEY,
    user_id bigint REFERENCES users,
    caption text NOT NULL,
    content text NOT NULL,
    created timestamp NOT NULL DEFAULT NOW(),
    updated timestamp NOT NULL,
    likes bigint NOT NULL DEFAULT 0,
    dislikes bigint NOT NULL DEFAULT 0,
    views bigint NOT NULL DEFAULT 0
);

COMMENT ON TABLE articles IS 'Статьи в блоге';

--rollback DROP TABLE article;