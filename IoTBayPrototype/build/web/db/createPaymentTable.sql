CREATE TABLE Payment
(cardNumber BIGINT NOT NULL,
cardExpiry INT,
cardCVC INT,
customerID INT,
PRIMARY KEY (cardNumber),
FOREIGN KEY (customerID) REFERENCES Customer(customerID));