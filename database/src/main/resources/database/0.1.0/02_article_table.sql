--liquibase formatted sql
--changeset zhuch:release/0.1 tag:release/0.1

CREATE TABLE article (
    article_id bigserial PRIMARY KEY,
    user_id bigint REFERENCES users,
    content text NOT NULL,
    created timestamp NOT NULL,
    updated timestamp NOT NULL
);

COMMENT ON TABLE article IS 'Статьи в блоге';

ALTER TABLE article
    ADD CONSTRAINT updated_val
        CHECK (updated >= created);

COMMENT ON CONSTRAINT updated_val ON article IS 'Время обновления не раньше создания';

--rollback DROP TABLE article;