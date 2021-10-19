CREATE ROLE fairlo SUPERUSER CREATEDB CREATEROLE INHERIT LOGIN PASSWORD 'fairlo';

CREATE DATABASE fairlo OWNER TO fairlo;

CREATE SCHEMA fairlo AUTHORIZATION fairlo;

CREATE TABLE fairlo.artist (
	id integer NOT NULL,
	"name" varchar NOT NULL,
	short_name varchar NOT NULL,
	area varchar NOT NULL,
	gender varchar NOT NULL,
	CONSTRAINT artist_pk PRIMARY KEY (id)
);

CREATE TABLE fairlo.track (
	id integer NOT NULL,
	title varchar NOT NULL,
	"position" varchar NOT NULL,
	duration varchar NOT NULL,
	CONSTRAINT track_pk PRIMARY KEY (id)
);


CREATE TABLE fairlo.recording (
	id integer NOT NULL,
	title varchar NOT NULL,
	artist_id integer NOT NULL,
	duration varchar NOT NULL,
	track_id integer NOT NULL,
	CONSTRAINT recording_pk PRIMARY KEY (id),
	CONSTRAINT recording_fk FOREIGN KEY (id) REFERENCES fairlo.artist(id),
	CONSTRAINT recording_fk_1 FOREIGN KEY (id) REFERENCES fairlo.track(id)
);
