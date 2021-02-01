/*
ALTER TABLE T_COMPANY
DROP CONSTRAINT FK_COMPANY_ADRESSE;

ALTER TABLE T_USER
DROP CONSTRAINT FK_USER_COMPANY;


ALTER TABLE T_CLIENT
DROP CONSTRAINT FK_CLIENT_ADRESSE;


ALTER TABLE T_PRESTATION
DROP CONSTRAINT FK_PRESTA_CLIENT;


ALTER TABLE T_PRESTATION
DROP CONSTRAINT FK_PRESTA_CONST;


ALTER TABLE T_PRESTATION
DROP CONSTRAINT FK_PRESTA_FACTURE;

ALTER TABLE T_CLIENT
DROP CONSTRAINT mail_unique;

ALTER TABLE T_CONSULTANT
DROP CONSTRAINT mail_unique;

ALTER TABLE T_USER
DROP CONSTRAINT mail_unique;

ALTER TABLE T_COMPANY
DROP CONSTRAINT siret_unique;
*/

DROP TABLE IF EXISTS T_USER_ROLE_REF;

DROP TABLE IF EXISTS T_USER;

DROP TABLE IF EXISTS T_USER_ROLE;

DROP TABLE IF EXISTS T_COMPANY;

DROP TABLE IF EXISTS T_ADRESSE;

DROP TABLE IF EXISTS T_CLIENT;

DROP TABLE IF EXISTS T_PRESTATION;

DROP TABLE IF EXISTS T_CONSULTANT;

DROP TABLE IF EXISTS T_FACTURE;


CREATE TABLE T_USER_ROLE_REF
(
     id bigint(20) NOT NULL AUTO_INCREMENT,
     role_id varchar(50) NOT NULL DEFAULT 0,
     role_code varchar(50) NOT NULL DEFAULT 0,
     role_name varchar(50) NOT NULL DEFAULT 0,
     PRIMARY KEY ( id )
);



CREATE TABLE T_USER_ROLE (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  role_code varchar(255) NOT NULL DEFAULT 0,
  role_id varchar(255) NOT NULL DEFAULT 0,
  role_name varchar(255) NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
) ;



CREATE TABLE T_ADRESSE (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  code_postal varchar(5) NOT NULL DEFAULT 0,
  localite varchar(255) NOT NULL DEFAULT 0,
  numero varchar(100) NOT NULL DEFAULT 0 ,
  pays varchar(30) NOT NULL DEFAULT 0,
  rue varchar(255) NOT NULL DEFAULT 0,  
  PRIMARY KEY (id)
);

CREATE TABLE T_CLIENT (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  mail varchar(255) NOT NULL DEFAULT 0,
  social_reason varchar(255) NOT NULL DEFAULT 0,
  adresse_id bigint(20) DEFAULT NULL DEFAULT 0,
  company_id bigint(20) DEFAULT NULL REFERENCES T_COMPANY(id),
  PRIMARY KEY (id)  
);

CREATE TABLE T_CONSULTANT (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  lastName varchar(255) NOT NULL DEFAULT 0,
  firstName varchar(255) NOT NULL DEFAULT 0,
  mail varchar(255) NOT NULL DEFAULT 0,
  fonction varchar(255) NOT NULL DEFAULT 0,
  company_id bigint(20) DEFAULT NULL REFERENCES T_COMPANY(id),
  PRIMARY KEY (id)  
);


CREATE TABLE T_FACTURE (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  client_prestation varchar(255) NOT NULL DEFAULT 0,
  date_echeance varchar(255) NOT NULL DEFAULT 0,
  date_encaissement varchar(255) DEFAULT NULL DEFAULT 0,
  date_facturation varchar(255) NOT NULL DEFAULT 0,
  delai_paiement bigint(20) NOT NULL DEFAULT 0,
  facture_status varchar(255) DEFAULT NULL DEFAULT 0,
  file_path varchar(255) DEFAULT NULL DEFAULT 0,
  frais_retard float DEFAULT NULL DEFAULT 0,
  mois_facture varchar(255) DEFAULT NULL DEFAULT 0,
  montanttva float NOT NULL DEFAULT 0,
  nb_jour_retard bigint(20) DEFAULT NULL DEFAULT 0,
  numero_commande varchar(255) NOT NULL DEFAULT 0,
  numero_facture varchar(255) DEFAULT NULL DEFAULT 0,
  prix_totalht float NOT NULL DEFAULT 0,
  prix_totalttc float NOT NULL DEFAULT 0,
  quantite float NOT NULL DEFAULT 0, 
  PRIMARY KEY (id)
 
); 


CREATE TABLE T_PRESTATION (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  client_prestation varchar(255) NOT NULL DEFAULT 0,
  delai_paiement bigint(20) NOT NULL DEFAULT 0,
  designation varchar(255) NOT NULL DEFAULT 0,
  numero_commande varchar(255) NOT NULL DEFAULT 0,
  quantite float NOT NULL DEFAULT 0,
  tarifht float(11) NOT NULL DEFAULT 0,  
  client_id bigint(20) DEFAULT NULL DEFAULT 0,
  consultant_id bigint(20) DEFAULT NULL DEFAULT 0 ,
  facture_id bigint(20) DEFAULT NULL DEFAULT 0,
  PRIMARY KEY (id)  
); 


CREATE TABLE T_COMPANY (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  code_ape varchar(255) DEFAULT NULL,
  numero_bic varchar(255) DEFAULT NULL,
  numero_iban varchar(255) DEFAULT NULL,
  numero_tva varchar(255) DEFAULT NULL,
  rcsname varchar(255) DEFAULT NULL,
  siret varchar(255) NOT NULL,
  socialreason varchar(255) NOT NULL,
  status varchar(255) NOT NULL, 
  adresse_id bigint(20),
  PRIMARY KEY (id)
  
);

/* -------------------------------------------------------------------*/
  
CREATE TABLE T_USER
(
     id bigint(20) NOT NULL AUTO_INCREMENT,
     firstName varchar(50) NOT NULL DEFAULT 0,
     lastName varchar(50) NOT NULL DEFAULT 0,
     mail varchar(50) NOT NULL DEFAULT 0,
     password varchar(50) NOT NULL DEFAULT 0,
     user_role_id bigint(20),
     company_id bigint(20),
     PRIMARY KEY ( id )     
);

/* ------------------------------------------------------------------- */

/*

ALTER TABLE T_USER
ADD CONSTRAINT FK_USER_COMPANY
FOREIGN KEY (company_id) REFERENCES T_COMPANY(id);

ALTER TABLE T_CLIENT
ADD CONSTRAINT FK_CLIENT_ADRESSE
FOREIGN KEY (adresse_id) REFERENCES T_ADRESSE(id);

ALTER TABLE T_CLIENT
ADD CONSTRAINT FK_CLIENT_COMPANY
FOREIGN KEY (company_id) REFERENCES T_COMPANY(id);


ALTER TABLE T_CONSULTANT
ADD CONSTRAINT FK_CONSULTANT_COMPANY
FOREIGN KEY (company_id) REFERENCES T_COMPANY(id);

ALTER TABLE T_PRESTATION
ADD CONSTRAINT FK_PRESTA_CLIENT
FOREIGN KEY (client_id) REFERENCES T_CLIENT(id);

ALTER TABLE T_PRESTATION
ADD CONSTRAINT FK_PRESTA_CONST
FOREIGN KEY (consultant_id) REFERENCES T_CONSULTANT(id);


ALTER TABLE T_PRESTATION
ADD CONSTRAINT FK_PRESTA_FACTURE
FOREIGN KEY (facture_id) REFERENCES T_FACTURE(id);

ALTER TABLE T_CLIENT
ADD CONSTRAINT mail_unique UNIQUE (mail);

ALTER TABLE T_CONSULTANT
ADD CONSTRAINT mail_unique UNIQUE (mail);


ALTER TABLE T_USER
ADD CONSTRAINT mail_unique UNIQUE (mail);

ALTER TABLE T_COMPANY
ADD CONSTRAINT siret_unique UNIQUE (siret);

*/

/* -------------------------------------------------------------------*/








