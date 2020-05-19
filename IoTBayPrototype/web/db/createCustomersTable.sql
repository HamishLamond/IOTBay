CREATE TABLE Customer
(customerId VARCHAR(16) NOT NULL,
customerName VARCHAR(32) NOT NULL,
customerAddress VARCHAR(64) NOT NULL,
customerEmail VARCHAR(32) NOT NULL,
customerPhone INT,
creditCardNumber INT,
creditCardExpiry INT,
creditCardCVC INT,
customerPassword VARCHAR(32),
PRIMARY KEY (customerId));