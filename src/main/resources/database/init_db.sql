CREATE TABLE IF NOT EXISTS post
(
    id    BIGSERIAL PRIMARY KEY ,
    id    bigint NOT NULL,
    title  text NOT NULL ,
    body text NOT NULL
);