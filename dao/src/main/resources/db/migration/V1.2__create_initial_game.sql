INSERT INTO developer (name)
    VALUES ('Naughty Dog');

INSERT INTO publisher (name)
    VALUES ('Sony Computer Entertainment');

INSERT INTO game (name, release_date, esrb, created_at, created_by)
VALUES ('The Last of Us', '2013-06-14', 'M - Mature', NOW(), 'SYSTEM');

INSERT INTO genre (name)
VALUES ('Action-Adventure'),
       ('Third-Person Shooter'),
       ('Science Fiction'),
       ('Fantasy'),
       ('Historical'),
       ('Mystery'),
       ('Adventure'),
       ('Role-Playing'),
       ('Simulation'),
       ('Sports'),
       ('Strategy'),
       ('Survival'),
       ('Stealth'),
       ('Horror'),
       ('Racing'),
       ('Puzzle'),
       ('Fighting'),
       ('Music'),
       ('Visual Novel');

INSERT INTO game_developer (game_id, developer_id)
VALUES ((SELECT id FROM game WHERE name = 'The Last of Us' AND release_date = '2013-06-14'),
        (SELECT id FROM developer WHERE name = 'Naughty Dog'));

INSERT INTO game_publisher (game_id, publisher_id)
VALUES ((SELECT id FROM game WHERE name = 'The Last of Us' AND release_date = '2013-06-14'),
        (SELECT id FROM publisher WHERE name = 'Sony Computer Entertainment'));

INSERT INTO game_genre (game_id, genre_id)
VALUES ((SELECT id FROM game WHERE name = 'The Last of Us' AND release_date = '2013-06-14'),
        (SELECT id FROM genre WHERE name = 'Action-Adventure'));

INSERT INTO game_genre (game_id, genre_id)
VALUES ((SELECT id FROM game WHERE name = 'The Last of Us' AND release_date = '2013-06-14'),
        (SELECT id FROM genre WHERE name = 'Third-Person Shooter'));

INSERT INTO game_genre (game_id, genre_id)
VALUES ((SELECT id FROM game WHERE name = 'The Last of Us' AND release_date = '2013-06-14'),
        (SELECT id FROM genre WHERE name = 'Horror'));


-- REVERT
-- DELETE FROM game_genre WHERE game_id = (SELECT id FROM game WHERE name = 'The Last of Us' AND release_date = '2013-06-14');
-- DELETE FROM game_publisher WHERE game_id = (SELECT id FROM game WHERE name = 'The Last of Us' AND release_date = '2013-06-14');
-- DELETE FROM game_developer WHERE game_id = (SELECT id FROM game WHERE name = 'The Last of Us' AND release_date = '2013-06-14');
-- DELETE FROM game WHERE name = 'The Last of Us' AND release_date = '2013-06-14';
-- DELETE FROM genre WHERE name IN (
--                                 'Action-Adventure', 'Third-Person Shooter', 'Science Fiction', 'Fantasy', 'Historical',
--                                 'Mystery', 'Adventure', 'Role-Playing', 'Simulation', 'Sports', 'Strategy', 'Survival',
--                                 'Stealth', 'Horror', 'Racing', 'Puzzle', 'Fighting', 'Music', 'Visual Novel'
--       );
-- DELETE FROM developer WHERE name = 'Naughty Dog';
-- DELETE FROM publisher WHERE name = 'Sony Computer Entertainment';
-- DELETE FROM flyway_schema_history WHERE version = '1.2';
