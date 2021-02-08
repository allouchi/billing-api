
INSERT INTO t_role_ref (id, role, description)
 VALUES  (1,'admin', 'admin');
 
INSERT INTO t_role_ref (id, role, description)
 VALUES  (2,'consult', 'consult');
 
INSERT INTO t_role_ref (id, role, description)
 VALUES  (3,'read', 'read');
 
INSERT INTO t_role_ref (id, role, description)
 VALUES  (4,'write', 'write');

 /************************************************************************************************************/
 
INSERT INTO t_role (id, role, description)
 VALUES  (1,'ADMIN', 'ADMIN');
 
INSERT INTO t_role (id, role, description)
 VALUES  (2,'CONSULT', 'CONSULT');
 
INSERT INTO t_role (id, role, description)
 VALUES  (3,'READ', 'READ');
 
INSERT INTO t_role (id, role, description)
 VALUES  (4,'WRITE', 'WRITE');
 
  /************************************************************************************************************/
 /*
 INSERT INTO t_user_role (user_id, role_id)
 VALUES  (1, 1);
 
  INSERT INTO t_user_role (user_id, role_id)
 VALUES  (2, 1); */
 
/************************************************************************************************************/
INSERT INTO t_user (user_name, last_name, first_name, mail, password, actived, company_id, role) 
VALUES ('allouchi@hotmail.fr', 'Aliane', 'Mustapha', 'allouchi@hotmail.fr', '123456', true, 2, 1);

INSERT INTO t_user (user_name, last_name,first_name, mail, password, actived, company_id, role) 
VALUES ('khalid@hotmail.fr', 'Alianne', 'Khalid', 'khalid@hotmail.fr', '123456', false, 1, 2);
 
 /************************************************************************************************************/
  
INSERT INTO T_ADRESSE (id , code_postal , localite , numero , pays , rue)
VALUES (1, '92500', 'Rueil-Malmaison', '111', 'FRANCE', 'Boulevard National');

INSERT INTO T_ADRESSE (id , code_postal , localite , numero , pays , rue)
VALUES (2, '92500', 'Rueil-Malmaison', '13', 'FRANCE', 'Domaine de la côte noire');

 INSERT INTO t_company (id, code_ape, numero_bic, numero_iban, numero_tva, rcsname, siret, social_reason, status, adresse_id)
VALUES (1, '6201Z','PSSTFRPPSCE', 'FR1720041010125407961J03367', 'FR18831502141', 'R.C.S. Nanterre 831 502 141', '85292702900011', 'SBATEC Consulting', 'SASU au capital de 500 Euros',1);


INSERT INTO t_company (id, code_ape, numero_bic, numero_iban, numero_tva, rcsname, siret, social_reason, status, adresse_id)
VALUES (2, '6201Z','CRLYFRPP', 'FR3330002008970000005896J14', 'FR18831502141', 'R.C.S. Nanterre 831 502 141', '83150214100011', 'ALIATECK', 'SASU au capital de 500 Euros',2);


INSERT INTO t_consultant( id,	last_name,	first_name,	mail, fonction,	company_id)
VALUES (1,	'ALIANE', 'Mustapha','allouchi@hotmail.fr' ,'Développeur Fullstack', 2);

INSERT INTO t_consultant( id, last_name, first_name, mail, fonction, company_id)
VALUES (2, 'ALIANE', 'Khalid', 'khalid@hotmail.fr', 'Développeur Fullstack', 1);

INSERT INTO t_consultant( id,	last_name,	first_name,	mail, fonction,	company_id)
VALUES (3,	'JERY', 'Hamed','jery@hotmail.fr' ,'Assistance Technique Business Intelligence', 2);








 
      
         
     
      
    
