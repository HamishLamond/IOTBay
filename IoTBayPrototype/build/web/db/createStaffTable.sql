CREATE TABLE Staff
(staffId VARCHAR(16) NOT NULL,
staffName VARCHAR(32) NOT NULL,
staffEmail VARCHAR(32) NOT NULL,
staffPhone INT,
rank INT,
staffPassword VARCHAR(32) NOT NULL,
staffManager VARCHAR(16),
PRIMARY KEY (staffId));