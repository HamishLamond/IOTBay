CREATE TABLE Payment
(creditCardNumber VARCHAR(16) NOT NULL,
customerId INT NOT NULL,
creditCardExpiry VARCHAR(5),
creditCardCVC VARCHAR(3),
isDefault int,
PRIMARY KEY (creditCardNumber),
FOREIGN KEY (customerId) REFERENCES CUSTOMER(customerId)
);