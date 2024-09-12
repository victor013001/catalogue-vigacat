ALTER TABLE game
    DROP CONSTRAINT game_UN;

ALTER TABLE game
    RENAME COLUMN name TO title;

ALTER TABLE game
    ADD CONSTRAINT game_UN UNIQUE (title, release_date);

ALTER TABLE genre RENAME COLUMN name TO genre;

ALTER TABLE publisher RENAME COLUMN name TO publisher;

ALTER TABLE developer RENAME COLUMN name TO developer;

-- REVERT
-- ALTER TABLE game DROP CONSTRAINT game_UN;
-- ALTER TABLE game RENAME COLUMN title TO name;
-- ALTER TABLE game ADD CONSTRAINT game_UN UNIQUE (name, release_date);
-- ALTER TABLE genre RENAME COLUMN genre TO name;
-- ALTER TABLE publisher RENAME COLUMN publisher TO name;
-- ALTER TABLE developer RENAME COLUMN developer TO name;
-- DELETE FROM flyway_schema_history WHERE version = '1.3';