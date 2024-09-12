CREATE TABLE game(
    id BIGSERIAL NOT NULL,
    name varchar(64) NOT NULL,
    developer varchar(64) NOT NULL,
    release_date DATE NOT NULL,
    PRIMARY KEY (id)
);

-- REVERT
-- DROP TABLE game
-- DELETE FROM flyway_schema_history WHERE version = '1.0';

