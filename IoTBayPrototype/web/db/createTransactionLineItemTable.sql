CREATE TABLE TransactionLineItem
(transactionLineItemId VARCHAR(16) NOT NULL,
transactionId VARCHAR(16) NOT NULL,
deviceId VARCHAR(16) NOT NULL,
quantity INT NOT NULL,
PRIMARY KEY (transactionLineItemId));