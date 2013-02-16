-- Database: dsweb

-- DROP DATABASE dsweb;

CREATE DATABASE dsweb
WITH OWNER = postgres
ENCODING = 'UTF8'
TABLESPACE = pg_default
LC_COLLATE = 'Portuguese_Brazil.1252'
LC_CTYPE = 'Portuguese_Brazil.1252'
CONNECTION LIMIT = -1;

-- Table: "User"

-- DROP TABLE "User";

CREATE TABLE "User"
(
  id integer NOT NULL,
  first_name character varying,
  last_name character varying,
  email character varying,
  password character varying,
  billing_address character varying,
  delivery_address character varying,
  cpf character varying,
  CONSTRAINT id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "User"
  OWNER TO postgres;
