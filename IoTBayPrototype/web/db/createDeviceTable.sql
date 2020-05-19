CREATE TABLE Device
(deviceId VARCHAR(16) NOT NULL,
deviceName VARCHAR(50) NOT NULL,
description VARCHAR(250),
deviceCost DOUBLE NOT NULL,
stock INT DEFAULT 0,
stockWarningNumber INT DEFAULT 1,
availability BOOLEAN DEFAULT TRUE,
PRIMARY KEY (deviceId));