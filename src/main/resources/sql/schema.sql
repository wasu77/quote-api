CREATE TABLE IF NOT EXISTS quotes
(
    id         INT auto_increment   NOT NULL,
    text       VARCHAR              NOT NULL,
    first_name VARCHAR(64)          NOT NULL,
    last_name  VARCHAR(64)          NOT NULL
);