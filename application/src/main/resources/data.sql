
INSERT INTO t_role_ref (id, role, description)
 VALUES  (1, 'ROLE_ADMIN', 'ROLE_ADMIN');
 
INSERT INTO t_role_ref (id, role, description)
 VALUES  (2, 'ROLE_CONSULT', 'ROLE_ADMIN');
 
INSERT INTO t_role_ref (id, role, description)
 VALUES  (3, 'ROLE_READ', 'ROLE_ADMIN');
 
INSERT INTO t_role_ref (id, role, description)
 VALUES  (4, 'ROLE_WRITE', 'ROLE_ADMIN');


 /************************************************************************************************************/
 
 
INSERT INTO t_user (user_name, password, actived, roles, company_id) 
VALUES ('allouchi@hotmail.fr', '123456', true, 'ROLE_ADMIN', 1);

INSERT INTO t_user (user_name, password, actived, roles, company_id) 
VALUES ('khalid@hotmail.fr', '123456', true, 'ROLE_ADMIN',2);

INSERT INTO t_user (user_name, password, actived, roles, company_id) 
VALUES ('salma@hotmail.fr', '123456', true, 'ROLE_ADMIN',1);

INSERT INTO t_user (user_name, password, actived, roles, company_id) 
VALUES ('btissame@hotmail.fr', '123456', true, 'ROLE_ADMIN',1);
 
/************************************************************************************************************/
 
/*
INSERT INTO t_role (id, user_name, role_name, description)
VALUES (1, 'allouchi@hotmail.fr', 'ROLE_CONSULT', 'ROLE_CONSULT');
INSERT INTO t_role (id, user_name, role_name, description)
VALUES (2, 'allouchi@hotmail.fr', 'ROLE_ADMIN', 'ROLE_ADMIN');
INSERT INTO t_role (id, user_name, role_name, description)
VALUES (3, 'khalid@hotmail.fr', 'ROLE_WRITE', 'ROLE_WRITE');
INSERT INTO t_role (id, user_name, role_name, description)
VALUES (4, 'salma@hotmail.fr', 'ROLE_READ', 'ROLE_READ');
INSERT INTO t_role (id, user_name, role_name, description)
VALUES (5, 'btissame@hotmail.fr', 'ROLE_READ', 'ROLE_READ');

*/
  
INSERT INTO T_ADRESSE (id , code_postal , localite , numero , pays , rue)
VALUES (1, '92500', 'Rueil-Malmaison', '111', 'FRANCE', 'Boulevard National');

INSERT INTO T_ADRESSE (id , code_postal , localite , numero , pays , rue)
VALUES (2, '92500', 'Rueil-Malmaison', '13', 'FRANCE', 'Domaine de la côte noire');

INSERT INTO t_company (id, code_ape, numero_bic, numero_iban, numero_tva, rcsname, siret, social_reason, status, adresse_id)
VALUES (1, '6201Z','PSSTFRPPSCE', 'FR1720041010125407961J03367', 'FR18831502141', 'R.C.S. Nanterre 831 502 141', '85292702900011', 'SBATEC Consulting', 'SASU au capital de 500 Euros',1);


INSERT INTO t_company (id, code_ape, numero_bic, numero_iban, numero_tva, rcsname, siret, social_reason, status, adresse_id)
VALUES (2, '6201Z','CRLYFRPP', 'FR3330002008970000005896J14', 'FR18831502141', 'R.C.S. Nanterre 831 502 141', '83150214100011', 'ALIATECK', 'SASU au capital de 500 Euros',2);


INSERT INTO t_consultant( id,	last_name,	first_name,	email, fonction,	company_id)
VALUES (1,	'ALIANE', 'Mustapha','allouchi@hotmail.fr' ,'Développeur Fullstack', 2);

INSERT INTO t_consultant( id, last_name, first_name, email, fonction, company_id)
VALUES (2, 'ALIANE', 'Khalid', 'khalid@hotmail.fr', 'Développeur Fullstack', 1);

INSERT INTO t_consultant( id,	last_name,	first_name,	email, fonction,	company_id)
VALUES (3,	'JERY', 'Hamed','jery@hotmail.fr' ,'Assistance Technique Business Intelligence', 2);








 
      
         
     
      
    
