
DROP TABLE IF EXISTS `billing-product`.t_user_role_ref;

CREATE TABLE `billing-product`.t_user_role_ref
(
    id smallint generated always as identity (start with 1 increment by 1)  primary key,
     role_id varchar(50) NOT NULL,
     role_code varchar(50) NOT NULL,
     role_name varchar(50) NOT NULL,
    CONSTRAINT uk_role_code_unique UNIQUE (role_code)
);



INSERT INTO `billing-product`.t_user_role_ref (id, role_id, role_code, role_name)
 VALUES VALUES (1,'1','admin', 'admin');
 
INSERT INTO `billing-product`.t_user_role_ref (id, role_id,role_code, role_name)
 VALUES VALUES (2,'2','read', 'read');
 
INSERT INTO `billing-product`.t_user_role_ref (id, role_id,role_code, role_name)
 VALUES VALUES (3,'3','write' 'write');
 
INSERT INTO `billing-product`.t_user_role_ref (id, role_id,role_code, role_name)
 VALUES VALUES (4,'4', 'cra','consultant');
