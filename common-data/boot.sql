CREATE DATABASE IF NOT EXISTS db_movies_store;

USE db_movies_store;

CREATE TABLE IF NOT EXISTS casts (
  id CHAR(36) NOT NULL,
  name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

--
-- Movie table
--

CREATE TABLE IF NOT EXISTS movies (
  id CHAR(36) NOT NULL,
  title VARCHAR(50) NOT NULL,
  cast_id VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_movies_casts FOREIGN KEY (cast_id) REFERENCES casts(id)
);