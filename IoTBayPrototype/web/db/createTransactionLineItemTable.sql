CREATE TABLE TransactionLineItem
(transactionLineItemId INT GENERATED ALWAYS AS IDENTITY NOT NULL,
transactionId INT NOT NULL,
deviceId INT NOT NULL,
quantity INT NOT NULL,
PRIMARY KEY (transactionLineItemId),
FOREIGN KEY (transactionId) REFERENCES TRANSACTIONS(transactionId),
FOREIGN KEY (deviceId) REFERENCES DEVICE(deviceId));
