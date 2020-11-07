CREATE DATABASE IF NOT EXISTS db_movies_store;

USE db_movies_store;


--
-- Movie table
--

CREATE TABLE IF NOT EXISTS movies (
  id CHAR(36) NOT NULL,
  title VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);