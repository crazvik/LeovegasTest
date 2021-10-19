CREATE TABLE IF NOT EXISTS players(
    playerId INT PRIMARY KEY,
    username varchar(255) UNIQUE,
    password varchar(255) NOT NULL,
    funds DOUBLE,
    isAdmin BOOLEAN
);

CREATE TABLE IF NOT EXISTS transactions(
    transactionId INT PRIMARY KEY,
    transaction varchar(255) UNIQUE,
    playerId INT REFERENCES players(playerId)
);

CREATE TABLE IF NOT EXISTS sessions(
    sessionId INT PRIMARY KEY,
    session varchar(255) UNIQUE,
    playerId INT REFERENCES players(playerId)
);