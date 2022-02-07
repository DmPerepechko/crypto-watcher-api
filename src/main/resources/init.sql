create database crypto_db;

CREATE TABLE cryptocurrency (
                      id  INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      symbol VARCHAR(3),
                      price_usd double,
                      time timestamp);
CREATE TABLE users (
                             id  INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                             symbol VARCHAR(45),
                             price_usd double);

insert into cryptocurrency (id, symbol) values (90, 'BTC'), (80, 'ETH'), (48543, 'SOL');
