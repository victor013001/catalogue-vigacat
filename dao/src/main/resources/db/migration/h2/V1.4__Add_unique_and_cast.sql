-- CREATE CAST (varchar AS esrb_type) WITH INOUT AS IMPLICIT;

ALTER TABLE developer
    ADD CONSTRAINT developer_UN UNIQUE (developer);

ALTER TABLE publisher
    ADD CONSTRAINT publisher_UN UNIQUE (publisher);

ALTER TABLE genre
    ADD CONSTRAINT genre_UN UNIQUE (genre);

-- REVERT
-- DROP CAST IF EXISTS (varchar AS esrb_type);
-- ALTER TABLE developer DROP CONSTRAINT developer_UN;
-- ALTER TABLE publisher DROP CONSTRAINT publisher_UN;
-- ALTER TABLE genre DROP CONSTRAINT genre_UN;
-- DELETE FROM flyway_schema_history WHERE version = '1.4';