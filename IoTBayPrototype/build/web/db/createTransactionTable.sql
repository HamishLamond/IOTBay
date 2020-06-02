CREATE TABLE Transactions
(transactionId INT GENERATED ALWAYS AS IDENTITY NOT NULL,
transactionValue DOUBLE NOT NULL,
customerId VARCHAR(16) NOT NULL,
createdOn TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
lastModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
status INT,
PRIMARY KEY (transactionId));