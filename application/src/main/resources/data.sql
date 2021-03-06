
INSERT INTO t_role_ref (id, role_name, description)
 VALUES  (1, 'ADMIN', 'ROLE_ADMIN');
 
INSERT INTO t_role_ref (id, role_name, description)
 VALUES  (2, 'CONSULT', 'ROLE_ADMIN');
 
INSERT INTO t_role_ref (id, role_name, description)
 VALUES  (3, 'READ', 'ROLE_ADMIN');
 
INSERT INTO t_role_ref (id, role_name, description)
 VALUES  (4, 'WRITE', 'ROLE_ADMIN');


 /************************************************************************************************************/
 
 
INSERT INTO t_user (id, user_name, last_name, first_name, password, actived, company_id) 
VALUES (1,'allouchi@hotmail.fr', 'Aliane', 'Mustapha', '123456', true, 1);

INSERT INTO t_user (id, user_name, last_name, first_name, password, actived, company_id) 
VALUES (2,'khalid@hotmail.fr','Aliane', 'Khalid', '123456', true, 2);

INSERT INTO t_user (id, user_name, last_name, first_name, password, actived, company_id) 
VALUES (3, 'salma@hotmail.fr','Aliane', 'Salma', '123456', true , 1);

INSERT INTO t_user (id, user_name, last_name, first_name, password, actived, company_id) 
VALUES (4, 'btissame@hotmail.fr','Aliane', 'Btissame', '123456', true, 1);
 

 /************************************************************************************************************/
 
INSERT INTO t_role (id, role_name, description, user_id)
VALUES (1, 'CONSULT', 'ROLE_CONSULT',2);
INSERT INTO t_role (id, role_name, description,user_id)
VALUES (2,  'ADMIN', 'ROLE_ADMIN', 1);
INSERT INTO t_role (id, role_name, description,user_id)
VALUES (3, 'WRITE', 'ROLE_WRITE', 2);
INSERT INTO t_role (id, role_name, description,user_id)
VALUES (4, 'READ', 'ROLE_READ', 2);
INSERT INTO t_role (id, role_name, description,user_id)
VALUES (5, 'READ', 'ROLE_READ', 1);


 /************************************************************************************************************/



  
INSERT INTO T_ADRESSE (id , code_postal , localite , numero , pays , rue)
VALUES (1, '92500', 'Rueil-Malmaison', '111', 'FRANCE', 'Boulevard National');

INSERT INTO T_ADRESSE (id , code_postal , localite , numero , pays , rue)
VALUES (2, '92500', 'Rueil-Malmaison', '13', 'FRANCE', 'Domaine de la côte noire');

INSERT INTO t_company (id, code_ape, numero_bic, numero_iban, numero_tva, rcsname, siret, social_reason, status, adresse_id)
VALUES (1, '6201Z','PSSTFRPPSCE', 'FR1720041010125407961J03367', 'FR18831502141', 'R.C.S. Nanterre 831 502 141', '85292702900011', 'SBATEC Consulting', 'SASU au capital de 500 Euros',1);


INSERT INTO t_company (id, code_ape, numero_bic, numero_iban, numero_tva, rcsname, siret, social_reason, status, adresse_id)
VALUES (2, '6201Z','CRLYFRPP', 'FR3330002008970000005896J14', 'FR18831502141', 'R.C.S. Nanterre 831 502 141', '83150214100011', 'ALIATECK', 'SASU au capital de 500 Euros',2);


INSERT INTO t_consultant( id,	last_name,	first_name,	email, fonction,	company_id)
VALUES (1,	'ALIANE', 'Mustapha','allouchi@hotmail.fr' ,'Développeur Fullstack', 1);

INSERT INTO t_consultant( id, last_name, first_name, email, fonction, company_id)
VALUES (2, 'ALIANE', 'Khalid', 'khalid@hotmail.fr', 'Développeur Fullstack', 2);

INSERT INTO t_consultant( id,	last_name,	first_name,	email, fonction, company_id)
VALUES (3,	'JERY', 'Hamed','jery@hotmail.fr' ,'Assistance Technique Business Intelligence', 2);








 
      
         
     
      
    
