CREATE DATABASE covid19
 WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    --LC_COLLATE = 'English_United States.1252'
    --LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- USE `covid19`;
\c covid19;

--
-- Table structure for table `vaccine`
--

DROP TABLE IF EXISTS vaccine;

CREATE TABLE "vaccine" (
  id SERIAL NOT NULL PRIMARY KEY,
  manufacturer varchar(45) DEFAULT NULL,
  min_age integer DEFAULT NULL,
  storage_temperature integer DEFAULT NULL,
  in_stock integer DEFAULT NULL
);

INSERT INTO vaccine VALUES 
    (default, 'Pfizer', 15, -80, 500),
    (default, 'Moderna', 10, -20, 200),
    (default, 'CytoDyn', 20, 10, 300);

--
-- Table structure for table `user`
--


CREATE TABLE public."user" (
  id serial NOT NULL primary key,
  name varchar(45) DEFAULT NULL,
  cnp varchar(20) DEFAULT NULL,
  address varchar(100) DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  phone varchar(20) DEFAULT NULL,
  profession varchar(100) DEFAULT NULL,
  priority integer DEFAULT NULL,
  registration timestamp DEFAULT NULL,
  vaccine_id int DEFAULT NULL REFERENCES vaccine (id),
  appointment DATE DEFAULT NULL
);

--
-- Data for table `user`
--

INSERT INTO "user" VALUES 
	(default, 'Albert Einstein','1790314011844', 'Princeton', 'albert.einstein@yahoo.com', '0766562789','Doctor', '1', '2021-01-19 10:38:15', 1, default),
  (default, 'Max Planck','1580423011834', 'Göttingen', 'max.planck@yahoo.com', '0744321987','Other', '3', '2021-01-15 03:22:45', 2, default),
  (default, 'Werner Heisenberg','1760201011869', 'Würzburg', 'werner.heisenberg@yahoo.com', '0769562871','Teacher', '2', '2021-01-18 15:14:59', 1, default),
  (default, 'Niels Bohr','1851007011831', 'Würzburg', 'niels.bohr@yahoo.com', '0766354268','Doctor', '4', '2021-01-17 12:47:25', 3, default),
  (default, 'Erwin Rudolf Josef Alexander Schrödinger','1870812011842', 'Vienna', 'erwin.schrödinger@yahoo.com', '0763989756','Other', '1', '2021-01-11 07:35:33', 1, default);


CREATE DATABASE covid19security
WITH
    OWNER = postgres
    ENCODING = 'UTF8'
   -- LC_COLLATE = 'English_United States.1252'
   -- LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- USE `covid19security`;
\c covid19security;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS users;
CREATE TABLE "users" (
  username varchar(50) primary key,
  password char(68) NOT NULL,
  enabled smallint NOT NULL
);

--
-- Default passwords here are: fun123
--

INSERT INTO users 
VALUES 
('admin','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1);


--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS authorities;
CREATE TABLE "authorities" (
  username varchar(50) NOT NULL,
  authority varchar(50) NOT NULL,
  CONSTRAINT authorities_idx_1 UNIQUE (username, authority),
  CONSTRAINT authorities_ibfk_1 FOREIGN KEY (username) REFERENCES users (username)
);

--
-- Dumping data for table `authorities`
--

INSERT INTO authorities VALUES ('admin','ROLE_ADMIN');

\c postgres