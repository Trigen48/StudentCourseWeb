CREATE DATABASE student_course_db CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';


CREATE TABLE system_user
(
	UserID				INT				PRIMARY KEY,
	UserName			VARCHAR(128)	NOT NULL,
	EmailAddress		VARCHAR(256)	NOT NULL,
	Password			CHAR(64)		NOT NULL,
	DateAdded			DATETIME		NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UserTypeID			SMALLINT		NOT NULL DEFAULT 1
) ENGINE=InnoDB ;


CREATE TABLE student
(
	StudentNumber		INT					PRIMARY KEY,
	Firstname			VARCHAR(100)		NOT NULL,
	Surname				VARCHAR(100)		NOT NULL
) ENGINE=InnoDB ;

CREATE TABLE course
(
	CourseCode			CHAR(8)				PRIMARY KEY,
	CourseDescription	VARCHAR(200)		NOT NULL
) ENGINE=InnoDB ;

CREATE TABLE student_course
(
	StudentNumber		INT					NOT NULL,
	CourseCode			CHAR(8)				NOT NULL,
	PRIMARY KEY (StudentNumber,CourseCode),
	Grade				CHAR(1)				NOT NULL DEFAULT 'F',

	CONSTRAINT student_course_StudentNumber_FK FOREIGN KEY(StudentNumber) REFERENCES student(StudentNumber)
	ON DELETE CASCADE
	ON UPDATE CASCADE,

	CONSTRAINT student_course_CourseCode_FK FOREIGN KEY(CourseCode) REFERENCES course(CourseCode)
	ON DELETE CASCADE
	ON UPDATE CASCADE
) ENGINE=InnoDB ;