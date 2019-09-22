CREATE TABLE users (
	userId INTEGER  NOT NULL PRIMARY KEY,
	userName NVARCHAR(50)  NOT NULL,
	grantAccess BOOLEAN NOT NULL
);

CREATE TABLE accessAttemptLogs (
	userId INTEGER NOT NULL,
	grantAccess BOOLEAN NOT NULL,
	visitOn DATE NOT NULL,
	attemptAt TIME (0) NOT NULL
);
