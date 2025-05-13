DROP TABLE IF EXISTS tokens;

CREATE TABLE tokens
(
    id             UUID NOT NULL,
    hash           BYTEA NOT NULL,
    owner_email    VARCHAR(255) NOT NULL,
    expired_at     TIMESTAMP NOT NULL,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
