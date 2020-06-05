CREATE TABLE AccessLog
(accessLogId INT GENERATED ALWAYS AS IDENTITY NOT NULL,
customerId INT,
logTime Timestamp DEFAULT CURRENT_TIMESTAMP,
eventType varchar(16),
staffId INT,
PRIMARY KEY (accessLogId),
FOREIGN KEY (customerId) REFERENCES CUSTOMER(customerId),
FOREIGN KEY (staffId) REFERENCES STAFF(staffId));