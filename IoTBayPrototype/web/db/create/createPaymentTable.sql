CREATE TABLE Payment
(creditCardNumber VARCHAR(16) NOT NULL,
customerId INT,
creditCardExpiry VARCHAR(5),
creditCardCVC VARCHAR(3),
isDefault int,
createdOn TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
lastUpdated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (creditCardNumber),
FOREIGN KEY (customerId) REFERENCES CUSTOMER(customerId)
);