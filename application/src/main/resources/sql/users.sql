
DROP TABLE IF EXISTS `billing-product`.t_user;

  
CREATE TABLE `billing-product`.t_user
(
    id smallint generated always as identity (start with 1 increment by 1)  primary key,
     firstName varchar(50) NOT NULL,
     lastName varchar(50) NOT NULL,
     email varchar(50) NOT NULL,
     password varchar(50) NOT NULL,
    CONSTRAINT uk_password_unique UNIQUE (password)
);



INSERT INTO `bill-product`.`t_user` (`id`, `firstname`, `lastname`, `mail`, `password`, `company`, `user_role`) 
VALUES ('1', 'Mustapha', 'Aliane', 'allouchi@hotmail.fr', '123456', '1', 'admin');

INSERT INTO `bill-product`.`t_user` (`id`, `firstname`, `lastname`, `mail`, `password`, `company`, `user_role`) 
VALUES ('2', 'Mustapha', 'Aliane', 'allouchi1@hotmail.fr', '123456', '1', 'admin');

INSERT INTO `bill-product`.`t_user` (`id`, `firstname`, `lastname`, `mail`, `password`, `company`, `user_role`) 
VALUES ('3', 'Mustapha', 'Aliane', 'allouchi2@hotmail.fr', '123456', '1', 'admin');


INSERT INTO `bill-product`.`t_user` (`id`, `firstname`, `lastname`, `mail`, `password`, `company`, `user_role`) 
VALUES ('4', 'Mustapha', 'Aliane', 'allouchi3@hotmail.fr', '123456', '1', 'admin');
