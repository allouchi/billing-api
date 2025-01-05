

-- init.sql
CREATE DATABASE IF NOT EXISTS billing-db;

USE billing-db;

CREATE TABLE T_USER (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(45) NOT NULL DEFAULT 0,
  first_name VARCHAR(45) NOT NULL DEFAULT 0,
  last_name VARCHAR(45) NOT NULL DEFAULT 0,
  password VARCHAR(45) NOT NULL DEFAULT 0,
  actived TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (id)
  );

INSERT INTO `billing-db`.t_user (id, user_name, last_name, first_name, password, actived)
VALUES (1,'allouchi@hotmail.fr', 'Aliane', 'Mustapha', '123456', true);

INSERT INTO `billing-db`.t_user (id, user_name, last_name, first_name, password, actived)
VALUES (2,'khalid@hotmail.fr','Aliane', 'Khalid', '123456', true);

INSERT INTO `billing-db`.t_user (id, user_name, last_name, first_name, password, actived)
VALUES (3, 'salma@hotmail.fr','Aliane', 'Salma', '123456', true);

INSERT INTO `billing-db`.t_user (id, user_name, last_name, first_name, password, actived)
VALUES (4, 'btissame@hotmail.fr','Aliane', 'Btissame', '123456', true);