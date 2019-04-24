# user-note
User Note Spring Boot Project with MySql and Basic Authentication

# HowTos
- Download or Clone project in any director of your choice
- do mvn clean install inside cloned/downloadded "user-note" projecct
- Open in any editor of your choice
- To update MySql project - Go to src/main/resource/application.properties file
	and update URL, Schema , UserName and Password
- Run DML and DDL in Database given under "Required DML DDL" section
- Spring Basic Authentication is used. Email as Username and password for Basic Authentication
- User Postman or cUrl provided to run the project.

# Exposed URL : 
URL - http://localhost:8182/notes

# Supported Operations : 

GET, POST, PUT, DELETE

# GET Operation 
URL : http://localhost:8182/notes
This will get All element for Authenticated User

Using cUrl
-----------
curl -u user02@gp.com:testpassword http://localhost:8182/notes

# POST Operation 
URL : http://localhost:8182/notes

Using cUrl  
---------------
curl -u user02@gp.com:testpassword -d "@C:\temp\post-json.json" -H "Content-Type: application/json" -X POST http://localhost:8182/notes

Json Body - post-json.json
{
	"title" : "note-title"
	"note" : "note description"
}

# PUT Operation 

URL : http://localhost:8182/notes

cUrl
-----------------
curl -u user02@gp.com:testpassword -d "@C:\temp\put-json.json" -H "Content-Type: application/json" -X PUT http://localhost:8182/notes

Json Body - put-json.json
{
	"title" : "note-title"
	"note" : "note description"
}

- title - This is Unique ID on which User note will be searched
- note - This field will be updated

# DELETE Operation 
URL : http://localhost:8182/notes

cUrl
--------------
curl -u user02@gp.com:testpassword -d "@C:\temp\delete-json.json" -H "Content-Type: application/json" -X DELETE http://localhost:8182/notes

Json Body - delete-json.json
{
	"title" : "note-title"
}

title - This is Unique ID which user note will be searched and deleted

# Required DDL and DML

User Table 
----------------
CREATE TABLE USER (
	ID	INT AUTO_INCREMENT ,
	EMAIL varchar(100) NOT NULL UNIQUE,
    PASSWORD varchar(50) NOT NULL,
    CREATE_TIME TIMESTAMP DEFAULT current_timestamp,
    UPDATE_TIME TIMESTAMP DEFAULT current_timestamp,
    CONSTRAINT USER_PK PRIMARY KEY(ID)
);

INSERT INTO USER (EMAIL, PASSWORD) VALUES ('user01@gp.com', '{noop}testpassword');
INSERT INTO USER (EMAIL, PASSWORD) VALUES ('user02@gp.com', '{noop}testpassword');

NOTE : Using BCrypt Utility (BCryptPasswordEncoderTest.java) encrypted password can be generated and inserted instead

Note Table
-----------------
CREATE TABLE NOTE (
	ID INT AUTO_INCREMENT,
    TITLE varchar(50) NOT NULL,
    NOTE varchar(1000) NOT NULL,
    CREATE_TIME TIMESTAMP DEFAULT current_timestamp,
    UPDATE_TIME TIMESTAMP DEFAULT current_timestamp,
    USER_ID INT,
    CONSTRAINT NOTE_PK PRIMARY KEY(ID) ,
    FOREIGN KEY NOTE_FK_01 (USER_ID) REFERENCES USER(ID)
);

# Spring Security
Spring Basic Authentication is used for

User name and password to be used as inserted in User table

# BCrypt Utility - BCryptPasswordEncoderTest
BCyrpt Utility is provided to generate BCrypt Password