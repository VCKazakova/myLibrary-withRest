DROP TABLE IF EXISTS authors CASCADE;
DROP TABLE IF EXISTS genres CASCADE;
DROP TABLE IF EXISTS books CASCADE;
DROP TABLE IF EXISTS comments CASCADE;

CREATE TABLE authors
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE genres
(
    id         SERIAL PRIMARY KEY,
    genre_name VARCHAR(255)
);

CREATE TABLE books
(
    id         SERIAL PRIMARY KEY,
    book_title VARCHAR(255),
    author_id  BIGINT REFERENCES authors (id) ON DELETE CASCADE,
    genre_id   BIGINT REFERENCES genres (id) ON DELETE CASCADE
);

CREATE TABLE comments
(
    id      SERIAL PRIMARY KEY,
    comment VARCHAR(855),
    book_id BIGINT REFERENCES books (id) ON DELETE CASCADE
);