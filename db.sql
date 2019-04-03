CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));

 CREATE TABLE user_roles (
  user_role_id integer IDENTITY(1,1) PRIMARY KEY,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));

CREATE TABLE candidate(
	id integer IDENTITY(1,1) PRIMARY KEY,
	fullName nvarchar(45),
	dateOfBirth date,
	address nvarchar(45),
	phoneNumber varchar(45),
	email nvarchar(45),
	totalScore float,
	grade varchar(45),
	dateCreated date,
	typeCandidate int,
	countryName nvarchar(45),
	facultyName nvarchar(45),
	majorName nvarchar(45)
)

CREATE TABLE faculty(
	id integer IDENTITY(1,1) PRIMARY KEY,
	name nvarchar(45) Unique
)

CREATE TABLE country(
	id integer IDENTITY(1,1) PRIMARY KEY,
	name nvarchar(45) Unique
)

CREATE TABLE majorofcountry(
	id integer IDENTITY(1,1) PRIMARY KEY,
	nameMajor nvarchar(45) Unique,
	country_id int,
	CONSTRAINT fk_country FOREIGN KEY (country_id) REFERENCES country(id)
)

 INSERT INTO users(username,password,enabled)
VALUES ('huynq','123456', 1);
INSERT INTO users(username,password,enabled)
VALUES ('admin','admin', 1);

INSERT INTO user_roles (username, role)
VALUES ('huynq', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_ADMIN');
