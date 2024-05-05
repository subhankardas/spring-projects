CREATE TABLE IF NOT EXISTS movies (
    id INT NOT NULL AUTO_INCREMENT, name VARCHAR(100), year INT NOT NULL, genre VARCHAR(100), PRIMARY KEY (id)
);

TRUNCATE movies;

INSERT INTO movies (name, year, genre) VALUES
    ('Godfather', 1972, 'Drama'),
    ('Avengers', 2018, 'Action'),
    ('Joker', 2019, 'Drama'),
    ('Interstellar', 2014, 'Sci-Fi'),
    ('The Matrix', 1999, 'Action'),
    ('Inception', 2010, 'Sci-Fi'),
    ('Prestige', 2006, 'Drama'),
    ('The Dark Knight', 2008, 'Action'),
    ('The Silence of the Lambs', 1991, 'Thriller'),
    ('The Green Mile', 1999, 'Drama');