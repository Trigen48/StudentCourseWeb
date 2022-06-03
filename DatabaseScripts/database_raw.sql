CREATE TABLE system_user
(
	UserID				INT				PRIMARY KEY,
	UserName			VARCHAR(128)	NOT NULL,
	EmailAddress		VARCHAR(256)	NOT NULL,
	Password			CHAR(64)		NOT NULL
	DateAdded			DATETIME		NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UserTypeID			SMALLINT		NOT NULL DEFAULT 1
);

CREATE TABLE student_csv
(
	StudentNumber		INT					PRIMARY KEY,
	Firstname			VARCHAR(100)		NOT NULL,
	Surname				VARCHAR(100)		NOT NULL,
    CourseCode			CHAR(8)				PRIMARY KEY,
	CourseDescription	VARCHAR(200)		NOT NULL,
    Grade				CHAR(1)				NOT NULL DEFAULT 'F',
);
