# user-note
User Note Spring Boot Project

# Exposed URL : 
URL - http://<HOSTNAME>>/notes
e.g. http://localhost:8182/notes

# Supported Operations : 

GET, POST, PUT, DELETE

# GET Operation 
URL : http://localhost:8182/notes
This will get All element for Authenticated User


# POST Operation 
URL : http://localhost:8182/notes

Json Body
{
	"title" : "note-title"
	"note" : "note description"
}

# PUT Operation 

URL : http://localhost:8182/notes

Json Body
{
	"title" : "note-title"
	"note" : "note description"
}

- title - This is Unique ID on which User note will be searched
- note - This field will be updated

# DELETE Operation 
URL : http://localhost:8182/notes

Json Body
{
	"title" : "note-title"
}

title - This is Unique ID which user note will be searched and deleted

# Required DDL and DML

User Table 
----------------
CREATE TABLE USER (
	USER_ID	INT AUTO_INCREMENT ,
	EMAIL varchar(100) NOT NULL UNIQUE,
    PASSWORD varchar(50) NOT NULL,
    CREATE_TIME TIMESTAMP DEFAULT current_timestamp,
    UPDATE_TIME TIMESTAMP DEFAULT current_timestamp,
    CONSTRAINT USER_PK PRIMARY KEY(USER_ID)
);

INSERT INTO USER (EMAIL, PASSWORD) VALUES ('user01@gp.com', '{noop}testpassword');
INSERT INTO USER (EMAIL, PASSWORD) VALUES ('user02@gp.com', '{noop}testpassword');

Note Table
-----------------
CREATE TABLE NOTE (
	NOTE_ID INT AUTO_INCREMENT,
    TITLE varchar(50) NOT NULL,
    NOTE varchar(1000) NOT NULL,
    CREATE_TIME TIMESTAMP DEFAULT current_timestamp,
    UPDATE_TIME TIMESTAMP DEFAULT current_timestamp,
    USER_ID INT,
    CONSTRAINT NOTE_PK PRIMARY KEY(NOTE_ID) ,
    FOREIGN KEY NOTE_FK_01 (USER_ID) REFERENCES USER(USER_ID)
);

# Spring Security
Spring Basic Authentication is used for

User name and password to be used as inserted in User table

# BCrypt Utility - BCryptPasswordEncoderTest
BCyrpt Utility is provided to generate BCrypt Password

