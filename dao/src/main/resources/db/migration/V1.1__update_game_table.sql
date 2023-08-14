CREATE TYPE esrb_type AS ENUM (
    'EC',
    'E',
    'E10',
    'T',
    'M',
    'AO',
    'RP'
    );

ALTER TABLE game
    DROP COLUMN developer,
    ADD COLUMN esrb esrb_type NOT NULL,
    ADD COLUMN created_at TIMESTAMP NOT NULL,
    ADD COLUMN created_by varchar (32) NOT NULL,
    ADD COLUMN updated_at TIMESTAMP,
    ADD COLUMN updated_by varchar (32),
    ADD CONSTRAINT game_UN UNIQUE (name, release_date);


CREATE TABLE developer
(
    id BIGSERIAL NOT NULL,
    name varchar(64) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE publisher
(
    id BIGSERIAL NOT NULL,
    name varchar(64) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE genre
(
    id BIGSERIAL NOT NULL,
    name varchar(64) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE game_developer
(
    game_id      BIGINT NOT NULL,
    developer_id BIGINT NOT NULL,
    PRIMARY KEY (game_id, developer_id),
    FOREIGN KEY (game_id) REFERENCES game (id),
    FOREIGN KEY (developer_id) REFERENCES developer (id)
);

CREATE TABLE game_publisher
(
    game_id      BIGINT NOT NULL,
    publisher_id BIGINT NOT NULL,
    PRIMARY KEY (game_id, publisher_id),
    FOREIGN KEY (game_id) REFERENCES game (id),
    FOREIGN KEY (publisher_id) REFERENCES publisher (id)
);

CREATE TABLE game_genre
(
    game_id  BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    PRIMARY KEY (game_id, genre_id),
    FOREIGN KEY (game_id) REFERENCES game (id),
    FOREIGN KEY (genre_id) REFERENCES genre (id)
);

-- REVERT
-- DROP TABLE IF EXISTS game_genre;
-- DROP TABLE IF EXISTS game_publisher;
-- DROP TABLE IF EXISTS game_developer;
-- DROP TABLE IF EXISTS developer;
-- DROP TABLE IF EXISTS publisher;
-- DROP TABLE IF EXISTS genre;
-- ALTER TABLE game
--     ADD COLUMN developer varchar(64),
--     DROP COLUMN esrb,
--     DROP COLUMN created_at,
--     DROP COLUMN created_by,
--     DROP COLUMN updated_at,
--     DROP COLUMN updated_by;
-- DROP TYPE IF EXISTS esrb_type;
-- DELETE FROM flyway_schema_history WHERE version = '1.1';
