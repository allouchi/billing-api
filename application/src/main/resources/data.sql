
SET MODE MySQL;
--
-- Table structure for table `t_adresse`
--
DROP TABLE IF EXISTS t_adresse;
CREATE TABLE `t_adresse` (
  `id` bigint NOT NULL AUTO_INCREMENT,
   `code_postal` varchar(5) NOT NULL DEFAULT '0',
    `localite` varchar(255) NOT NULL DEFAULT '0',
    `numero` varchar(100) NOT NULL DEFAULT '0',
    `pays` varchar(30) NOT NULL DEFAULT '0',
    `rue` varchar(255) NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
);

--
-- Dumping data for table `t_adresse`
--
INSERT INTO t_adresse VALUES
(720,'92500','Rueil-Malmaison','13','FRANCE','Domaine de la côte noire'),
(725,'92500','Rueil-Malmaison','111','FRANCE','Boulevard National'),
(726,'75005','Paris','5','FRANCE','Rue Thénard'),
(728,'92400','Courbevoie','4','FRANCE','Place des Vosges'),
(729,'75009','Paris','15','FRANCE','rue Taitbout'),
(730,'69001','LYON 1ER','21','FRANCE','Rue Algérie'),
(731,'69001','LYON 1ER','21','FRANCE','Rue Algérie'),
(732,'69001','LYON 1ER','21','FRANCE','Rue Algérie'),
(733,'69001','LYON 1ER','21','FRANCE','Rue Algérie'),
(734,'69001','LYON 1ER','21','FRANCE','Rue Algérie');


--
-- Table structure for table `t_company`
--
DROP TABLE IF EXISTS t_company;
CREATE TABLE t_company (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code_ape` varchar(255) DEFAULT NULL,
  `numero_bic` varchar(255) DEFAULT NULL,
  `numero_iban` varchar(255) DEFAULT NULL,
  `numero_tva` varchar(255) DEFAULT NULL,
  `rcsname` varchar(255) DEFAULT NULL,
  `siret` varchar(255) NOT NULL,
  `social_reason` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `adresse_id` bigint DEFAULT NULL,
  `checked` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

--
-- Dumping data for table `t_company`
--
INSERT INTO t_company ( id,
                         code_ape,
                         numero_bic,
                         numero_iban,
                         numero_tva,
                         rcsname,
                         siret,
                         social_reason,
                         status,
                         adresse_id,
                         checked) VALUES
(1,'6201Z','PSSTFRPPSCE','FR1720041010125407961J03367','FR18831502141','R.C.S. Nanterre 831 502 141','85292702900011','SBATEC Consulting','SASU au capital de 500 Euros',725,TRUE),
(2,'6201Z','CRLYFRPP','FR3330002008970000005896J14','FR18831502141','R.C.S. Nanterre 831 502 141','83150214100011','ALIATECK','SASU au capital de 500 Euros',720,FALSE);



DROP TABLE IF EXISTS t_client;
CREATE TABLE t_client (
   `id` bigint NOT NULL AUTO_INCREMENT,
    `email` varchar(255) NOT NULL,
    `social_reason` varchar(255) NOT NULL DEFAULT '0',
    `adresse_id` bigint DEFAULT NULL,
    `company_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`)
);


--
-- Dumping data for table `t_client`
--
INSERT INTO t_client (id, email,social_reason, adresse_id, company_id)
VALUES (1,'odyssey.consulting@odyssey.com','Odyssey Consulting',726,1),
(2,'emagine.consulting@emagine.com','Emagine Consulting',728,1),
(3,'easy.partner@easy-partner.fr','Easy Partner',729,1),
(4,'hmejri@osircom.com','Osircom',734,1);

--
-- Table structure for table `t_consultant`
--
DROP TABLE IF EXISTS t_consultant;
CREATE TABLE t_consultant (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) NOT NULL DEFAULT '0',
  `first_name` varchar(255) NOT NULL DEFAULT '0',
  `email` varchar(255) NOT NULL,
  `fonction` varchar(255) NOT NULL DEFAULT '0',
  `company_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
);


--
-- Dumping data for table `t_consultant`
--
INSERT INTO t_consultant (id, last_name, first_name,  email ,  fonction , company_id )
VALUES
(1,'ALIANE','Mustapha','mustapha.aliane@free.fr','Développeur FullStack JEE/Angular',1),
(2,'ALIANE','Khalid','khalid@hotmail.fr','Développeur Fullstack JAVA/JEE/React',2);


--
-- Table structure for table `t_exercise`
--
DROP TABLE IF EXISTS t_exercise;
CREATE TABLE t_exercise (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `exercise` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

--
-- Dumping data for table `t_exercise`
--
INSERT INTO t_exercise (id,exercise )
VALUES
(6,'2025'),
(5,'2024'),
(4,'2023'),
(3,'2022'),
(2,'2021'),
(1,'Tous');
--
-- Table structure for table `t_prestation`
--
DROP TABLE IF EXISTS t_prestation;
CREATE TABLE t_prestation (
 `id` bigint NOT NULL AUTO_INCREMENT,
  `client_prestation` varchar(45) NOT NULL DEFAULT '0',
  `delai_paiement` bigint NOT NULL DEFAULT '0',
  `designation` varchar(100) NOT NULL DEFAULT '0',
  `numero_commande` varchar(100) NOT NULL DEFAULT '0',
  `quantite` float NOT NULL DEFAULT '0',
  `tarifht` float NOT NULL DEFAULT '0',
  `date_debut` varchar(20) DEFAULT NULL,
  `date_fin` varchar(20) DEFAULT NULL,
  `client_id` bigint DEFAULT NULL,
  `consultant_id` bigint DEFAULT NULL,
  `facture_id` bigint DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `siret` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

--
-- Dumping data for table `t_prestation`
--
INSERT INTO t_prestation (id,
                            client_prestation,
                            delai_paiement,
                            designation,
                            numero_commande,
                            quantite,
                            tarifht,
                            date_debut,
                            date_fin,
                            client_id,
                            consultant_id,
                            facture_id,
                            company_id,
                            siret)
VALUES
(1,'Odyssey Consulting',30,'La Prestation est réalisée pour le compte de','xxxxxxxxxxxxxxxxxx',0,470,'01/03/2021','30/06/2021',1,1,NULL,1,'85292702900011'),
(2,'Accor Hotels',30,'La Prestation est réalisée pour le compte de','N°13.21.19.05.14.01',0,490,'08/07/2021','31/12/2021',2,1,NULL,1,'85292702900011'),
(3,'Accor Hotels',30,'La Prestation est réalisée pour le compte de','N° 13.21.19.05.14.01',0,510,'01/01/2022','30/09/2022',2,1,NULL,1,'85292702900011'),
(4,'Ekino',30,'La Prestation est réalisée pour le compte de','N° 2022.11.07.00186',0,500,'09/11/2022','31/12/2022',3,1,NULL,1,'85292702900011'),
(5,'CS Group',60,'La Prestation est réalisée pour le compte de ','N° CS202305',0,510,'20/02/2023','31/12/2025',4,1,NULL,1,'85292702900011');

--
-- Table structure for table `t_facture`
--
DROP TABLE IF EXISTS t_facture;
CREATE TABLE t_facture (
  `id` bigint NOT NULL AUTO_INCREMENT,
    `client_prestation` varchar(255) NOT NULL,
    `date_echeance` varchar(255) DEFAULT NULL,
    `date_encaissement` varchar(255) DEFAULT NULL,
    `date_facturation` varchar(255) DEFAULT NULL,
    `delai_paiement` bigint NOT NULL,
    `facture_status` varchar(255) DEFAULT NULL,
    `file_path` varchar(255) DEFAULT NULL,
    `file_name` varchar(255) DEFAULT NULL,
    `file_content` longblob,
    `frais_retard` float DEFAULT NULL,
    `mois_facture` varchar(255) DEFAULT NULL,
    `montant_net_tva` float NOT NULL DEFAULT '0',
    `nb_jour_retard` bigint DEFAULT NULL,
    `numero_commande` varchar(255) NOT NULL,
    `numero_facture` varchar(255) DEFAULT NULL,
    `prix_totalht` float NOT NULL DEFAULT '0',
    `prix_totalttc` float NOT NULL DEFAULT '0',
    `quantite` float NOT NULL DEFAULT '0',
    `montant_tva` float NOT NULL,
    `status_desc` varchar(255) DEFAULT NULL,
    `tarifht` float NOT NULL,
    `prestation_id` bigint DEFAULT NULL,
    `exercice` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


--
-- Dumping data for table `t_facture`
--
INSERT INTO t_facture ( id,
                           client_prestation,
                           date_echeance ,
                           date_encaissement ,
                           date_facturation ,
                           delai_paiement,
                           facture_status ,
                           file_path ,
                           file_name ,
                           file_content,
                           frais_retard ,
                           mois_facture ,
                          montant_net_tva,
                           nb_jour_retard,
                           numero_commande,
                           numero_facture,
                           prix_totalht,
                           prix_totalttc,
                           quantite,
                           montant_tva,
                           status_desc,
                           tarifht,
                           prestation_id,
                           exercice)
             VALUES
(1,'Odyssey Consulting','30/04/2021','30/04/2021','31/03/2021',30,'OK','\\2021\\03-Mars\\Facture Client\\Facture_Sbatec_Odyssey_202103_1000.pdf','Facture_Sbatec_Odyssey_202103_1000.pdf',null,0,'Mars',0,0,'N°12.12.19.05.00.01','20210331-1000',10810,12972,23,2162,'Acquitté',470,1,'2021'),
(2,'Odyssey Consulting','30/05/2021','30/05/2021','30/04/2021',30,'OK','\\2021\\04-Avril\\Facture Client\\Facture_Sbatec_Odyssey_202104_1001.pdf','Facture_Sbatec_Odyssey_202104_1001.pdf',null,0,'Avril',0,0,'N°12.12.19.05.00.01','20210430-1001',9635,11562,20.5,1927,'Acquitté',470,1,'2021'),
(3,'Odyssey Consulting','30/06/2021','30/06/2021','31/05/2021',30,'OK','\\2021\\05-Mai\\Facture Client\\Facture_Sbatec_Odyssey_202105_1002.pdf','Facture_Sbatec_Odyssey_202105_1002.pdf',null,0,'Juin',0,0,'N°12.12.19.05.00.01','20210630-1003',9870,11844,21,1974,'Acquitté',470,1,'2021'),
(5,'Accor Hotels','30/08/2021','30/08/2021','31/07/2021',30,'OK','\\2021\\07-Juillet\\Facture Client\\Facture_Sbatec_Emagine_202107_1000.pdf','Facture_Sbatec_Emagine_202107_1000.pdf',null,0,'Juillet',0,0,'N°13.21.19.05.14.01','20210731-1000',7840,9408,16,1568,'Acquitté',490,2,'2021'),(
6,'Accor Hotels','30/09/2021','30/09/2021','31/08/2021',30,'OK','\\2021\\08-Août\\Facture Client\\Facture_Sbatec_Emagine_202108_1001.pdf','Facture_Sbatec_Emagine_202108_1001.pdf',null,0,'Août',0,0,'N°13.21.19.05.14.01','20210831-1001',5880,7056,12,1176,'Acquitté',490,2,'2021'),
(7,'Accor Hotels','30/10/2021','30/10/2021','30/09/2021',30,'OK','\\2021\\09-Septembre\\Facture Client\\Facture_Sbatec_Emagine_202109_1002.pdf','Facture_Sbatec_Emagine_202109_1002.pdf',null,0,'Septembre',0,0,'N°13.21.19.05.14.01','20210930-1002',10780,12936,22,2156,'Acquitté',490,2,'2021'),
(8,'Accor Hotels','30/11/2021','30/11/2021','31/10/2021',30,'OK','\\2021\\10-Octobre\\Facture Client\\Facture_Sbatec_Emagine_202110_1003.pdf','Facture_Sbatec_Emagine_202110_1003.pdf',null,0,'Octobre',0,0,'N°13.21.19.05.14.01','20211031-1003',7105,8526,14.5,1421,'Acquitté',490,2,'2021'),
(9,'Accor Hotels','30/12/2021','30/12/2021','30/11/2021',30,'OK','\\2021\\11-Novembre\\Facture Client\\Facture_Sbatec_Emagine_202111_1004.pdf','Facture_Sbatec_Emagine_202111_1004.pdf',null,0,'Novembre',0,0,'N°13.21.19.05.14.01','20211130-1004',9800,11760,20,1960,'Acquitté',490,2,'2021'),
(10,'Accor Hotels','30/01/2021','30/01/2022','31/12/2021',30,'OK','\\2021\\12-Décembre\\Facture Client\\Facture_Sbatec_Emagine_202112_1005.pdf','Facture_Sbatec_Emagine_202112_1005.pdf',null,0,'Décembre',0,0,'N°13.21.19.05.14.01','20211231-1005',11270,13524,23,2254,'Acquitté',490,2,'2021'),
(11,'Accor Hotels','02/03/2022','04/03/2022','31/01/2022',30,'OK','\\2022\\01-Janvier\\Facture Client\\Facture_Sbatec_Emagine_202201_1000.pdf','Facture_Sbatec_Emagine_202201_1000.pdf',null,0,'Janvier',0,0,'N° 13.21.19.05.14.01','20220131-1000',10455,12546,20.5,2091,'Acquitté',510,3,'2022'),
(12,'Accor Hotels','30/03/2022','31/03/2022','29/02/2022',30,'OK','\\2022\\02-Février\\Facture Client\\Facture_Sbatec_Emagine_202202_1001.pdf','Facture_Sbatec_Emagine_202202_1001.pdf',null,0,'Février',0,0,'N° 13.21.19.05.14.01','20220228-1001',10200,12240,20,2040,'Acquitté',510,3,'2022'),
(13,'Accor Hotels','30/04/2022','28/04/2022','31/03/2022',30,'OK','\\2022\\03-Mars\\Facture Client\\Facture_Sbatec_Emagine_202203_1002.pdf','Facture_Sbatec_Emagine_202203_1002.pdf',null,0,'Mars',0,0,'N° 13.21.19.05.14.01','20220331-1002',11220,13464,22,2244,'Acquitté',510,3,'2022'),
(14,'Accor Hotels','30/05/2022','27/05/2022','30/04/2022',30,'OK','\\2022\\04-Avril\\Facture Client\\Facture_Sbatec_Emagine_202204_1003.pdf','Facture_Sbatec_Emagine_202204_1003.pdf',null,0,'Avril',0,0,'N° 13.21.19.05.14.01','20220430-1003',10200,12240,20,2040,'Acquitté',510,3,'2022'),
(15,'Accor Hotels','30/06/2022','30/06/2022','31/05/2022',30,'OK','\\2022\\05-Mai\\Facture Client\\Facture_Sbatec_Emagine_202205_1004.pdf','Facture_Sbatec_Emagine_202205_1004.pdf',null,0,'Mai',0,0,'N° 13.21.19.05.14.01','20220531-1004',10200,12240,20,2040,'Acquitté',510,3,'2022'),
(17,'Accor Hotels','30/07/2022','28/07/2022','30/06/2022',30,'OK','\\2022\\06-Juin\\Facture Client\\Facture_Sbatec_Emagine_202206_1005.pdf','Facture_Sbatec_Emagine_202206_1005.pdf',null,0,'Juin',0,0,'N° 13.21.19.05.14.01','20220630-1005',10710,12852,21,2142,'Acquitté',510,3,'2022'),
(18,'Accor Hotels','30/08/2022','31/08/2022','31/07/2022',30,'OK','\\2022\\07-Juillet\\Facture Client\\Facture_Sbatec_Emagine_202207_1006.pdf','Facture_Sbatec_Emagine_202207_1006.pdf',null,0,'Juillet',0,0,'N° 13.21.19.05.14.01','20220731-1006',3570,4284,7,714,'Acquitté',510,3,'2022'),
(19,'Accor Hotels','30/09/2022','13/10/2022','31/08/2022',30,'OK','\\2022\\08-Août\\Facture Client\\Facture_Sbatec_Emagine_202208_1007.pdf','Facture_Sbatec_Emagine_202208_1007.pdf',null,0,'Septembre',0,0,'N° 13.21.19.05.14.01','20220930-1008',11220,13464,22,2244,'Acquitté',510,3,'2022'),
(21,'Ekino','30/12/2022','02/01/2023','30/11/2022',30,'OK','\\2022\\11-Novembre\\Facture Client\\Facture_Sbatec_Easy_202211_1000.pdf','Facture_Sbatec_Easy_202211_1000.pdf',null,0,'Novembre',0,0,'N° 2022.11.07.00186','20221130-1000',7000,8400,14,1400,'Acquitté',500,4,'2022'),
(22,'Ekino','30/01/2023','27/01/2023','31/12/2022',30,'OK','\\2022\\12-Décembre\\Facture Client\\Facture_Sbatec_Easy_202212_1001.pdf','Facture_Sbatec_Easy_202212_1001.pdf',null,0,'Décembre',0,0,'N° 2022.11.07.00186','20221231-1001',2000,2400,4,400,'Acquitté',500,4,'2022'),
(24,'CS Group','29/04/2023','30/04/2023','28/02/2023',60,'OK','\\2023\\02-Février\\Facture Client\\Facture_Sbatec_Osircom_202302_1000.pdf','Facture_Sbatec_Osircom_202302_1000.pdf',null,0,'Février',0,0,'N° CS202301','20230228-1000',3570,4284,7,714,'Acquitté',510,5,'2023'),
(25,'CS Group','30/05/2023','01/06/2023','30/03/2023',60,'OK','\\2023\\03-Mars\\Facture Client\\Facture_Sbatec_Osircom_202303_1001.pdf','Facture_Sbatec_Osircom_202303_1001.pdf',null,0,'Mars',0,0,'N° CS202301','20230331-1001',11730,14076,23,2346,'Acquitté',510,5,'2023'),
(27,'CS Group','29/06/2023','05/07/2023','29/04/2023',60,'OK','\\2023\\04-Avril\\Facture Client\\Facture_Sbatec_Osircom_202304_1002.pdf','Facture_Sbatec_Osircom_202304_1002.pdf',null,0,'Avril',0,0,'N° CS202301','20230430-1002',9690,11628,19,1938,'Acquitté',510,5,'2023'),
(28,'CS Group','30/07/2023','14/08/2023','30/05/2023',60,'OK','\\2023\\05-Mai\\Facture Client\\Facture_Sbatec_Osircom_202305_1003.pdf','Facture_Sbatec_Osircom_202305_1003.pdf',null,0,'Mai',0,0,'N° CS202301','20230531-1003',9180,11016,18,1836,'Acquitté',510,5,'2023'),
(30,'CS Group','29/08/2023','10/10/2023','30/06/2023',60,'OK','\\2023\\06-Juin\\Facture Client\\Facture_Sbatec_Osircom_202306_1004.pdf','Facture_Sbatec_Osircom_202306_1004.pdf',null,0,'Juin',0,0,'N° CS202301','20230630-1004',11220,13464,22,2244,'Acquitté',510,5,'2023'),
(31,'CS Group','29/09/2023','10/10/2023','31/07/2023',60,'OK','\\2023\\07-Juillet\\Facture Client\\Facture_Sbatec_Osircom_202307_1005.pdf','Facture_Sbatec_Osircom_202307_1005.pdf',null,0,'Juillet',0,0,'N° CS202301','20230731-1005',7140,8568,14,1428,'Acquitté',510,5,'2023'),
(32,'CS Group','30/10/2023','07/11/2023','31/08/2023',60,'OK','\\2023\\08-Août\\Facture Client\\Facture_Sbatec_Osircom_202308_1006.pdf','Facture_Sbatec_Osircom_202308_1006.pdf',null,0,'Août',0,0,'N° CS202301','20230831-1006',3060,3672,6,612,'Acquitté',510,5,'2023'),
(33,'CS Group','29/11/2023','04/12/2023','29/09/2023',60,'OK','\\2023\\09-Septembre\\Facture Client\\Facture_Sbatec_Osircom_202309_1007.pdf','Facture_Sbatec_Osircom_202309_1007.pdf',null,0,'Septembre',0,0,'N° CS202301','20230930-1007',9690,11628,19,1938,'Acquitté',510,5,'2023'),
(34,'CS Group','30/12/2023','09/01/2024','31/10/2023',60,'OK','\\2023\\10-Octobre\\Facture Client\\Facture_Sbatec_Osircom_202310_1008.pdf','Facture_Sbatec_Osircom_202310_1008.pdf',null,0,'Octobre',0,0,'N° CS202305','20231031-1008',11220,13464,22,2244,'Acquitté',510,5,'2023'),
(35,'CS Group','29/01/2024','06/02/2024','30/11/2023',60,'OK','\\2023\\11-Novembre\\Facture Client\\Facture_Sbatec_Osircom_202311_1009.pdf','Facture_Sbatec_Osircom_202311_1009.pdf',null,0,'Novembre',0,0,'N° CS202305','20231130-1009',10710,12852,21,2142,'Acquitté',510,5,'2023'),
(38,'CS Group','29/02/2024','01/03/2024','31/12/2023',60,'OK','\\2023\\12-Décembre\\Facture Client\\Facture_Sbatec_Osircom_202312_1010.pdf','Facture_Sbatec_Osircom_202312_1010.pdf',null,0,'Décembre',0,0,'N° CS202305','20231231-1010',8160,9792,16,1632,'Acquitté',510,5,'2023'),
(39,'CS Group','31/03/2024','02/01/2024','31/01/2024',60,'OK','\\2024\\01-Janvier\\Facture Client\\Facture_Sbatec_Osircom_202401_1011.pdf','Facture_Sbatec_Osircom_202401_1011.pdf',null,0,'Janvier',0,0,'N° CS202305','20240131-1011',11220,13464,22,2244,'Acquitté',510,5,'2024'),
(40,'CS Group','29/04/2024','07/05/2024','29/02/2024',60,'OK','\\2024\\02-Février\\Facture Client\\Facture_Sbatec_Osircom_202402_1012.pdf','Facture_Sbatec_Osircom_202402_1012.pdf',null,0,'Février',0,0,'N° CS202305','20240229-1012',10710,12852,21,2142,'Acquitté',510,5,'2024'),
(41,'CS Group','30/05/2024','31/03/2024','31/03/2024',60,'OK','\\2024\\03-Mars\\Facture Client\\Facture_Sbatec_Osircom_202403_1013.pdf','Facture_Sbatec_Osircom_202403_1013.pdf',null,0,'Mars',0,0,'N° CS202305','20240331-1013',10710,12852,21,2142,'Acquitté',510,5,'2024'),
(42,'CS Group','29/06/2024','30/04/2024','30/04/2024',60,'OK','\\2024\\04-Avril\\Facture Client\\Facture_Sbatec_Osircom_202404_1014.pdf','Facture_Sbatec_Osircom_202404_1014.pdf',null,0,'Avril',0,0,'N° CS202305','20240430-1014',10710,12852,21,2142,'Acquitté',510,5,'2024'),
(43,'CS Group','30/07/2024','05/07/2024','31/05/2024',60,'OK','\\2024\\05-Mai\\Facture Client\\Facture_Sbatec_Osircom_202405_1015.pdf','Facture_Sbatec_Osircom_202405_1015.pdf',null,0,'Mai',0,0,'N° CS202305','20240531-1015',9180,11016,18,1836,'Acquitté',510,5,'2024'),
(44,'CS Group','29/08/2024','06/09/2024','30/06/2024',60,'OK','\\2024\\06-Juin\\Facture Client\\Facture_Sbatec_Osircom_202406_1016.pdf','Facture_Sbatec_Osircom_202406_1016.pdf',null,0,'Juin',0,0,'N° CS202305','20240630-1016',2550,3060,5,510,'Acquitté',510,5,'2024'),
(45,'CS Group','29/09/2024','02/10/2024','31/07/2024',60,'OK','\\2024\\07-Juillet\\Facture Client\\Facture_Sbatec_Osircom_202407_1017.pdf','Facture_Sbatec_Osircom_202407_1017.pdf',null,0,'Juillet',0,0,'N° CS202305','20240731-1017',4590,5508,9,918,'Acquitté',510,5,'2024'),
(46,'CS Group','30/10/2024','02/11/2024','31/08/2024',60,'OK','\\2024\\08-Août\\Facture Client\\Facture_Sbatec_Osircom_202408_1018.pdf','Facture_Sbatec_Osircom_202408_1018.pdf',null,0,'Août',0,0,'N° CS202305','20240831-1018',4080,4896,8,816,'Acquitté',510,5,'2024'),
(47,'CS Group','29/11/2024','05/11/2024','30/09/2024',60,'OK','\\2024\\09-Septembre\\Facture Client\\Facture_Sbatec_Osircom_202409_1019.pdf','Facture_Sbatec_Osircom_202409_1019.pdf',null,0,'Septembre',2112,0,'N° CS202305','20240930-1019',10710,12852,21,2142,'Acquitté',510,5,'2024'),
(54,'CS Group','30/12/2024','08/01/2025','31/10/2024',60,'OK','\\2024\\10-Octobre\\Facture Client\\Facture_Sbatec_Osircom_202410_1020.pdf','Facture_Sbatec_Osircom_202410_1020.pdf',null,0,'Octobre',2316,0,'N° CS202305','20241031-1020',11730,14076,23,2346,'Acquitté',510,5,'2024'),
(56,'CS Group','29/01/2025','11/02/2025','30/11/2024',60,'OK','\\2024\\11-Novembre\\Facture Client\\Facture_Sbatec_Osircom_202411_1021.pdf','Facture_Sbatec_Osircom_202411_1021.pdf',null,0,'Novembre',1908,0,'N° CS202305','20241130-1021',9690,11628,19,1938,'Acquitté',510,5,'2024'),
(57,'CS Group','01/03/2025','04/03/2025','31/12/2024',60,'OK','\\2024\\12-Décembre\\Facture Client\\Facture_Sbatec_Osircom_202412_1022.pdf','Facture_Sbatec_Osircom_202412_1022.pdf',null,0,'Décembre',1500,0,'N° CS202305','20241231-1022',7650,9180,15,1530,'Acquitté',510,5,'2024'),
(58,'CS Group','01/04/2025','09/04/2025','31/01/2025',60,'OK','\\2025\\01-Janvier\\Facture Client\\Facture_Sbatec_Osircom_202501_1023.pdf','Facture_Sbatec_Osircom_202501_1023.pdf',null,0,'Janvier',2214,0,'N° CS202305','20250131-1023',11220,13464,22,2244,'Acquitté',510,5,'2025'),
(60,'CS Group','29/04/2025','05/05/2025','28/02/2025',60,'OK','\\2025\\02-Février\\Facture Client\\Facture_Sbatec_Osircom_202502_1024.pdf','Facture_Sbatec_Osircom_202502_1024.pdf',null,0,'Février',2010,0,'N° CS202305','20250228-1024',10200,12240,20,2040,'Acquitté',510,5,'2025'),
(61,'CS Group','30/05/2025',NULL,'31/03/2025',60,'KO','\\2025\\03-Mars\\Facture Client\\Facture_Sbatec_Osircom_202503_1025.pdf','Facture_Sbatec_Osircom_202503_1025.pdf',null,0,'Mars',2112,0,'N° CS202305','20250331-1025',10710,12852,21,2142,'N. Acquitté',510,5,'2025'),(63,'CS Group','29/06/2025',NULL,'30/04/2025',60,'KO','\\2025\\04-Avril\\Facture Client\\Facture_Sbatec_Osircom_202504_1026.pdf','Facture_Sbatec_Osircom_202504_1026.pdf',null,0,'Avril',2112,0,'N° CS202305','20250430-1026',10710,12852,21,2142,'N. Acquitté',510,5,'2025'),(65,'CS Group','30/07/2025',NULL,'31/05/2025',60,'KO','\\2025\\05-Mai\\Facture Client\\Facture_Sbatec_Osircom_202505_1027.pdf','Facture_Sbatec_Osircom_202505_1027.pdf',null,0,'Mai',1806,0,'N° CS202305','20250531-1027',9180,11016,18,1836,'N. Acquitté',510,5,'2025');

--
-- Table structure for table `t_role`
--
DROP TABLE IF EXISTS t_role;
CREATE TABLE t_role (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

--
-- Dumping data for table `t_role_ref`
--
INSERT INTO `t_role` (id,
                            role_name,
                            description)
VALUES
(1,'ROLE_ADMIN','Administrateur'),
(2,'ROLE_CONSULT','Consultation'),
(3,'ROLE_EDIT','Edition');

--
-- Table structure for table `t_user`
--
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `siret` varchar(20) NOT NULL,
  `activated`   tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
  );

--
-- Dumping data for table `t_user`
--



INSERT INTO t_user (id,
                    email,
                    first_name,
                    last_name,
                    password,
                    siret,
                    activated
                   )
VALUES
(1,'allouchi@hotmail.fr','Mustapha','Aliane','$2a$10$7XzFwbCwSWcAhVZQSF742eW2f0MZ6LOEcwRSAbOZa8bgrU9XVYK0u','85292702900011',TRUE),
(2,'khalid@hotmail.fr','Khalid','Aliane','$2a$10$7XzFwbCwSWcAhVZQSF742eW2f0MZ6LOEcwRSAbOZa8bgrU9XVYK0u','85292702900011',TRUE),
(3,'salma@hotmail.fr','Salma','Aliane','$2a$10$7XzFwbCwSWcAhVZQSF742eW2f0MZ6LOEcwRSAbOZa8bgrU9XVYK0u','85292702900011', TRUE),
(4,'btissame@hotmail.fr','Btissame','Aliane','$2a$10$7XzFwbCwSWcAhVZQSF742eW2f0MZ6LOEcwRSAbOZa8bgrU9XVYK0u','85292702900011',TRUE);


CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES t_user(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES t_role(id) ON DELETE NO ACTION
);

INSERT INTO user_roles (user_id,role_id)
VALUES
(1,1),
(1,2);


--
-- Table structure for table `t_tva`
--
DROP TABLE IF EXISTS t_tva;
CREATE TABLE t_tva (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_payment` varchar(255) NOT NULL,
  `exercise` varchar(255) NOT NULL,
  `montant_payment` float NOT NULL,
  `siret` varchar(20) NOT NULL,
  `month_payment` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

--
-- Dumping data for table `t_tva`
--
INSERT INTO t_tva (id,
                       date_payment,
                       exercise ,
                       montant_payment ,
                       siret ,
                       month_payment)
VALUES
(1,'28/12/2021','2021',2588,'85292702900011',NULL),
(2,'29/07/2021','2021',3533,'85292702900011',NULL),
(5,'05/05/2022','2021',9558,'85292702900011',NULL),
(8,'22/06/2022','2022',10479,'85292702900011',NULL),
(9,'29/07/2022','2022',684,'85292702900011',NULL),
(10,'23/11/2022','2022',2490,'85292702900011',NULL),
(11,'27/09/2023','2023',1806,'85292702900011',NULL),
(12,'20/11/2023','2023',1398,'85292702900011',NULL),
(13,'23/02/2024','2023',2214,'85292702900011',NULL),
(14,'27/03/2024','2024',1970,'85292702900011',NULL),
(17,'27/06/2024','2023',12338,'85292702900011','Février'),
(16,'29/04/2024','2024',1970,'85292702900011','Janvier'),
(18,'26/07/2024','2024',2072,'85292702900011','Mars'),
(19,'26/08/2024','2024',2112,'85292702900011','Avril'),
(20,'24/09/2024','2024',1806,'85292702900011','Mai'),
(21,'24/10/2024','2024',480,'85292702900011','Juin'),
(22,'24/12/2024','2024',786,'85292702900011','Août'),
(25,'24/02/2025','2024',2316,'85292702900011','Octobre'),
(23,'24/01/2025','2024',2112,'85292702900011','Septembre'),
(26,'24/03/2025','2024',1908,'85292702900011','Novembre'),
(27,'24/04/2025','2024',1500,'85292702900011','Décembre'),
(28,'24/05/2025','2025',2214,'85292702900011','Janvier');

DROP TABLE IF EXISTS t_operation;
CREATE TABLE t_operation (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_operation` varchar(10) NOT NULL,
  `type_operation` varchar(10) NOT NULL,
  `montant_operation` varchar(45) NOT NULL,
  `exercise` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
  );

  INSERT INTO t_operation (id,
                         date_operation,
                         type_operation ,
                         montant_operation ,
                         exercise )
  VALUES
  (1,'28/12/2025','DIV',2588,'2025'),
  (2,'28/12/2025','NDF',1400,'2025');

DROP TABLE IF EXISTS persistent_logins;

 CREATE TABLE persistent_logins(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(64) not null,
  `series` varchar(64),
  `token` varchar(64) not null,
  `last_used` timestamp not null,
  PRIMARY KEY (`series`));



