CREATE TABLE Transactions
(transactionId INT GENERATED ALWAYS AS IDENTITY NOT NULL,
transactionValue DOUBLE NOT NULL,
customerId INT NOT NULL,
createdOn TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
lastModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
status INT,
PRIMARY KEY (transactionId),
FOREIGN KEY (customerId) REFERENCES CUSTOMER(customerId));