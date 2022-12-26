-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: billing-prod
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `batch_job_execution`
--

DROP TABLE IF EXISTS `batch_job_execution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_job_execution` (
  `JOB_EXECUTION_ID` bigint NOT NULL,
  `VERSION` bigint DEFAULT NULL,
  `JOB_INSTANCE_ID` bigint NOT NULL,
  `CREATE_TIME` datetime(6) NOT NULL,
  `START_TIME` datetime(6) DEFAULT NULL,
  `END_TIME` datetime(6) DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime(6) DEFAULT NULL,
  `JOB_CONFIGURATION_LOCATION` varchar(2500) DEFAULT NULL,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  KEY `JOB_INST_EXEC_FK` (`JOB_INSTANCE_ID`),
  CONSTRAINT `JOB_INST_EXEC_FK` FOREIGN KEY (`JOB_INSTANCE_ID`) REFERENCES `batch_job_instance` (`JOB_INSTANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch_job_execution`
--

LOCK TABLES `batch_job_execution` WRITE;
/*!40000 ALTER TABLE `batch_job_execution` DISABLE KEYS */;
INSERT INTO `batch_job_execution` VALUES (1,2,1,'2022-02-05 19:24:53.591000','2022-02-05 19:24:53.640000','2022-02-05 19:24:53.816000','COMPLETED','COMPLETED','','2022-02-05 19:24:53.817000',NULL),(2,2,1,'2022-02-05 19:27:16.840000','2022-02-05 19:27:16.881000','2022-02-05 19:27:16.895000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-02-05 19:27:16.896000',NULL),(3,2,1,'2022-02-05 19:42:06.660000','2022-02-05 19:42:06.672000','2022-02-05 19:42:06.680000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-02-05 19:42:06.680000',NULL),(4,2,1,'2022-02-05 20:45:20.044000','2022-02-05 20:45:20.084000','2022-02-05 20:45:20.099000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-02-05 20:45:20.100000',NULL),(5,2,1,'2022-02-05 20:52:53.973000','2022-02-05 20:52:54.010000','2022-02-05 20:52:54.025000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-02-05 20:52:54.026000',NULL),(6,2,1,'2022-02-05 20:55:23.108000','2022-02-05 20:55:23.163000','2022-02-05 20:55:23.179000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-02-05 20:55:23.180000',NULL),(7,2,1,'2022-02-05 21:29:32.339000','2022-02-05 21:29:32.378000','2022-02-05 21:29:32.393000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-02-05 21:29:32.394000',NULL),(8,2,1,'2022-02-05 21:36:58.096000','2022-02-05 21:36:58.102000','2022-02-05 21:36:58.108000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-02-05 21:36:58.108000',NULL),(9,2,1,'2022-02-06 17:45:30.164000','2022-02-06 17:45:30.213000','2022-02-06 17:45:30.229000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-02-06 17:45:30.229000',NULL),(10,2,1,'2022-02-06 18:20:03.140000','2022-02-06 18:20:03.189000','2022-02-06 18:20:03.203000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-02-06 18:20:03.204000',NULL),(11,2,1,'2022-03-06 20:22:37.543000','2022-03-06 20:22:37.617000','2022-03-06 20:22:37.636000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-03-06 20:22:37.636000',NULL),(12,2,1,'2022-03-12 20:32:55.989000','2022-03-12 20:32:56.043000','2022-03-12 20:32:56.059000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-03-12 20:32:56.059000',NULL),(13,2,1,'2022-03-20 12:15:26.332000','2022-03-20 12:15:26.384000','2022-03-20 12:15:26.404000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-03-20 12:15:26.404000',NULL),(14,2,1,'2022-03-30 16:58:42.280000','2022-03-30 16:58:42.507000','2022-03-30 16:58:42.558000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-03-30 16:58:42.559000',NULL),(15,2,1,'2022-03-31 17:09:31.391000','2022-03-31 17:09:31.478000','2022-03-31 17:09:31.501000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-03-31 17:09:31.502000',NULL),(16,2,1,'2022-04-02 16:42:24.312000','2022-04-02 16:42:24.386000','2022-04-02 16:42:24.413000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-04-02 16:42:24.414000',NULL),(17,2,1,'2022-04-02 17:11:22.832000','2022-04-02 17:11:22.886000','2022-04-02 17:11:22.903000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-04-02 17:11:22.903000',NULL),(18,2,1,'2022-04-02 17:13:27.794000','2022-04-02 17:13:27.803000','2022-04-02 17:13:27.807000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-04-02 17:13:27.807000',NULL),(19,2,1,'2022-04-03 16:52:23.547000','2022-04-03 16:52:23.590000','2022-04-03 16:52:23.604000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-04-03 16:52:23.604000',NULL),(20,2,1,'2022-04-03 18:04:17.982000','2022-04-03 18:04:18.157000','2022-04-03 18:04:18.186000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-04-03 18:04:18.186000',NULL),(21,2,1,'2022-04-10 17:43:31.002000','2022-04-10 17:43:31.047000','2022-04-10 17:43:31.062000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-04-10 17:43:31.062000',NULL),(22,2,1,'2022-04-18 15:12:32.469000','2022-04-18 15:12:32.517000','2022-04-18 15:12:32.536000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-04-18 15:12:32.536000',NULL),(23,2,1,'2022-04-18 15:13:16.288000','2022-04-18 15:13:16.295000','2022-04-18 15:13:16.301000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-04-18 15:13:16.301000',NULL),(24,2,1,'2022-04-30 23:09:34.416000','2022-04-30 23:09:34.477000','2022-04-30 23:09:34.492000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-04-30 23:09:34.492000',NULL),(25,2,1,'2022-04-30 23:13:35.997000','2022-04-30 23:13:36.009000','2022-04-30 23:13:36.065000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-04-30 23:13:36.065000',NULL),(26,2,1,'2022-05-03 19:30:24.098000','2022-05-03 19:30:24.145000','2022-05-03 19:30:24.168000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-05-03 19:30:24.169000',NULL),(27,2,1,'2022-05-03 20:05:45.245000','2022-05-03 20:05:45.293000','2022-05-03 20:05:45.315000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-05-03 20:05:45.316000',NULL),(28,2,1,'2022-05-25 11:25:31.346000','2022-05-25 11:25:31.390000','2022-05-25 11:25:31.410000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-05-25 11:25:31.411000',NULL),(29,2,1,'2022-05-31 18:38:40.347000','2022-05-31 18:38:40.405000','2022-05-31 18:38:40.428000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-05-31 18:38:40.429000',NULL),(30,2,1,'2022-06-04 20:26:39.949000','2022-06-04 20:26:40.031000','2022-06-04 20:26:40.070000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-06-04 20:26:40.071000',NULL),(31,2,1,'2022-06-16 18:18:39.842000','2022-06-16 18:18:39.965000','2022-06-16 18:18:40.006000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-06-16 18:18:40.007000',NULL),(32,2,1,'2022-06-25 10:24:21.938000','2022-06-25 10:24:22.051000','2022-06-25 10:24:22.109000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-06-25 10:24:22.110000',NULL),(33,2,1,'2022-06-30 19:56:31.998000','2022-06-30 19:56:32.062000','2022-06-30 19:56:32.086000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-06-30 19:56:32.087000',NULL),(34,2,1,'2022-07-10 19:12:44.517000','2022-07-10 19:12:44.574000','2022-07-10 19:12:44.593000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-07-10 19:12:44.594000',NULL),(35,2,1,'2022-07-16 21:26:04.301000','2022-07-16 21:26:04.409000','2022-07-16 21:26:04.447000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-07-16 21:26:04.448000',NULL),(36,2,1,'2022-09-03 09:12:22.002000','2022-09-03 09:12:22.186000','2022-09-03 09:12:22.227000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-09-03 09:12:22.228000',NULL),(37,2,1,'2022-09-29 17:05:31.470000','2022-09-29 17:05:31.551000','2022-09-29 17:05:31.576000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-09-29 17:05:31.577000',NULL),(38,2,1,'2022-09-29 17:16:27.108000','2022-09-29 17:16:27.115000','2022-09-29 17:16:27.122000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-09-29 17:16:27.122000',NULL),(39,2,1,'2022-09-29 17:17:02.925000','2022-09-29 17:17:02.931000','2022-09-29 17:17:02.936000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-09-29 17:17:02.936000',NULL),(40,2,1,'2022-09-29 17:17:54.592000','2022-09-29 17:17:54.600000','2022-09-29 17:17:54.605000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-09-29 17:17:54.606000',NULL),(41,2,1,'2022-09-29 17:20:09.695000','2022-09-29 17:20:09.703000','2022-09-29 17:20:09.710000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-09-29 17:20:09.710000',NULL),(42,2,1,'2022-09-29 17:20:20.551000','2022-09-29 17:20:20.558000','2022-09-29 17:20:20.561000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-09-29 17:20:20.561000',NULL),(43,2,1,'2022-09-29 17:21:34.709000','2022-09-29 17:21:34.717000','2022-09-29 17:21:34.722000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-09-29 17:21:34.723000',NULL),(44,2,1,'2022-10-05 10:32:53.998000','2022-10-05 10:32:54.084000','2022-10-05 10:32:54.114000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-05 10:32:54.114000',NULL),(45,2,1,'2022-10-05 13:47:03.332000','2022-10-05 13:47:03.390000','2022-10-05 13:47:03.413000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-05 13:47:03.413000',NULL),(46,2,1,'2022-10-05 13:47:50.854000','2022-10-05 13:47:50.902000','2022-10-05 13:47:50.920000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-05 13:47:50.921000',NULL),(47,2,1,'2022-10-05 13:49:21.891000','2022-10-05 13:49:21.898000','2022-10-05 13:49:21.903000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-05 13:49:21.903000',NULL),(48,2,1,'2022-10-05 13:49:39.704000','2022-10-05 13:49:39.711000','2022-10-05 13:49:39.716000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-05 13:49:39.716000',NULL),(49,2,1,'2022-10-05 13:51:14.042000','2022-10-05 13:51:14.049000','2022-10-05 13:51:14.053000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-05 13:51:14.054000',NULL),(50,2,1,'2022-10-05 18:03:08.075000','2022-10-05 18:03:08.087000','2022-10-05 18:03:08.096000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-05 18:03:08.096000',NULL),(51,2,1,'2022-10-05 18:03:31.894000','2022-10-05 18:03:31.949000','2022-10-05 18:03:31.968000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-05 18:03:31.968000',NULL),(52,2,1,'2022-10-05 18:04:54.667000','2022-10-05 18:04:54.675000','2022-10-05 18:04:54.682000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-05 18:04:54.682000',NULL),(53,2,1,'2022-10-05 18:06:13.218000','2022-10-05 18:06:13.231000','2022-10-05 18:06:13.237000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-05 18:06:13.237000',NULL),(54,2,1,'2022-10-05 18:08:07.813000','2022-10-05 18:08:07.822000','2022-10-05 18:08:07.826000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-05 18:08:07.827000',NULL),(55,2,1,'2022-10-13 10:07:20.320000','2022-10-13 10:07:20.371000','2022-10-13 10:07:20.395000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-13 10:07:20.396000',NULL),(56,2,1,'2022-10-19 16:57:43.782000','2022-10-19 16:57:43.824000','2022-10-19 16:57:43.842000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-19 16:57:43.842000',NULL),(57,2,1,'2022-10-19 16:58:59.853000','2022-10-19 16:58:59.859000','2022-10-19 16:58:59.866000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-19 16:58:59.866000',NULL),(58,2,1,'2022-10-19 17:00:09.685000','2022-10-19 17:00:09.734000','2022-10-19 17:00:09.751000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-19 17:00:09.751000',NULL),(59,2,1,'2022-10-19 17:00:40.807000','2022-10-19 17:00:40.815000','2022-10-19 17:00:40.823000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-19 17:00:40.824000',NULL),(60,2,1,'2022-10-20 10:08:07.370000','2022-10-20 10:08:07.423000','2022-10-20 10:08:07.442000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-20 10:08:07.442000',NULL),(61,2,1,'2022-10-20 10:09:58.000000','2022-10-20 10:09:58.007000','2022-10-20 10:09:58.014000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-20 10:09:58.014000',NULL),(62,2,1,'2022-10-20 10:10:33.407000','2022-10-20 10:10:33.415000','2022-10-20 10:10:33.421000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-20 10:10:33.421000',NULL),(63,2,1,'2022-10-20 10:10:36.162000','2022-10-20 10:10:36.170000','2022-10-20 10:10:36.176000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-20 10:10:36.176000',NULL),(64,2,1,'2022-10-20 10:10:39.900000','2022-10-20 10:10:39.905000','2022-10-20 10:10:39.910000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-20 10:10:39.910000',NULL),(65,2,1,'2022-10-25 16:19:52.990000','2022-10-25 16:19:53.052000','2022-10-25 16:19:53.076000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-10-25 16:19:53.077000',NULL),(66,2,1,'2022-11-03 12:12:18.463000','2022-11-03 12:12:18.562000','2022-11-03 12:12:18.597000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-11-03 12:12:18.598000',NULL),(67,2,1,'2022-11-11 17:45:22.826000','2022-11-11 17:45:22.895000','2022-11-11 17:45:22.922000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-11-11 17:45:22.923000',NULL),(68,2,1,'2022-11-11 17:59:01.558000','2022-11-11 17:59:01.625000','2022-11-11 17:59:01.646000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-11-11 17:59:01.646000',NULL),(69,2,1,'2022-11-11 18:20:55.806000','2022-11-11 18:20:56.000000','2022-11-11 18:20:56.040000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-11-11 18:20:56.041000',NULL),(70,2,1,'2022-11-11 19:15:51.011000','2022-11-11 19:15:51.141000','2022-11-11 19:15:51.165000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-11-11 19:15:51.166000',NULL),(71,2,1,'2022-11-11 19:19:29.346000','2022-11-11 19:19:29.439000','2022-11-11 19:19:29.462000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-11-11 19:19:29.463000',NULL),(72,2,1,'2022-11-11 19:21:17.013000','2022-11-11 19:21:17.183000','2022-11-11 19:21:17.207000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-11-11 19:21:17.208000',NULL),(73,2,1,'2022-11-11 19:24:01.888000','2022-11-11 19:24:02.062000','2022-11-11 19:24:02.084000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-11-11 19:24:02.085000',NULL),(74,2,1,'2022-11-23 19:26:52.844000','2022-11-23 19:26:52.859000','2022-11-23 19:26:52.871000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-11-23 19:26:52.871000',NULL),(75,2,1,'2022-11-30 22:32:21.147000','2022-11-30 22:32:21.225000','2022-11-30 22:32:21.250000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-11-30 22:32:21.250000',NULL),(76,2,1,'2022-12-03 12:12:18.019000','2022-12-03 12:12:18.099000','2022-12-03 12:12:18.117000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-03 12:12:18.118000',NULL),(77,2,1,'2022-12-07 16:31:16.865000','2022-12-07 16:31:16.932000','2022-12-07 16:31:16.957000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-07 16:31:16.957000',NULL),(78,2,1,'2022-12-07 19:43:01.909000','2022-12-07 19:43:01.959000','2022-12-07 19:43:01.977000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-07 19:43:01.978000',NULL),(79,2,1,'2022-12-07 20:05:47.167000','2022-12-07 20:05:47.215000','2022-12-07 20:05:47.242000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-07 20:05:47.243000',NULL),(80,2,1,'2022-12-07 20:06:41.826000','2022-12-07 20:06:41.886000','2022-12-07 20:06:41.911000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-07 20:06:41.912000',NULL),(81,2,1,'2022-12-14 09:26:56.270000','2022-12-14 09:26:56.326000','2022-12-14 09:26:56.346000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-14 09:26:56.347000',NULL),(82,2,1,'2022-12-14 09:28:19.071000','2022-12-14 09:28:19.116000','2022-12-14 09:28:19.130000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-14 09:28:19.130000',NULL),(83,2,1,'2022-12-14 11:46:17.322000','2022-12-14 11:46:17.370000','2022-12-14 11:46:17.388000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-14 11:46:17.389000',NULL),(84,2,1,'2022-12-14 11:49:36.615000','2022-12-14 11:49:36.671000','2022-12-14 11:49:36.688000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-14 11:49:36.688000',NULL),(85,2,1,'2022-12-14 11:49:58.186000','2022-12-14 11:49:58.230000','2022-12-14 11:49:58.246000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-14 11:49:58.247000',NULL),(86,2,1,'2022-12-14 11:51:13.978000','2022-12-14 11:51:14.018000','2022-12-14 11:51:14.030000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-14 11:51:14.030000',NULL),(87,2,1,'2022-12-14 12:08:24.864000','2022-12-14 12:08:24.922000','2022-12-14 12:08:24.939000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-14 12:08:24.940000',NULL),(88,2,1,'2022-12-14 12:10:25.623000','2022-12-14 12:10:25.669000','2022-12-14 12:10:25.686000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-14 12:10:25.686000',NULL),(89,2,1,'2022-12-14 12:13:03.508000','2022-12-14 12:13:03.555000','2022-12-14 12:13:03.572000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-14 12:13:03.572000',NULL),(90,2,1,'2022-12-14 14:05:13.788000','2022-12-14 14:05:13.834000','2022-12-14 14:05:13.846000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-14 14:05:13.846000',NULL),(91,2,1,'2022-12-14 14:08:13.828000','2022-12-14 14:08:13.878000','2022-12-14 14:08:13.895000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-14 14:08:13.896000',NULL),(92,2,1,'2022-12-14 14:10:57.414000','2022-12-14 14:10:57.460000','2022-12-14 14:10:57.478000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-14 14:10:57.479000',NULL),(93,2,1,'2022-12-15 08:50:05.212000','2022-12-15 08:50:05.262000','2022-12-15 08:50:05.278000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 08:50:05.279000',NULL),(94,2,1,'2022-12-15 08:57:09.422000','2022-12-15 08:57:09.469000','2022-12-15 08:57:09.486000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 08:57:09.487000',NULL),(95,2,1,'2022-12-15 09:03:20.503000','2022-12-15 09:03:20.553000','2022-12-15 09:03:20.569000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 09:03:20.570000',NULL),(96,2,1,'2022-12-15 09:14:25.293000','2022-12-15 09:14:25.339000','2022-12-15 09:14:25.354000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 09:14:25.355000',NULL),(97,2,1,'2022-12-15 09:15:18.972000','2022-12-15 09:15:19.029000','2022-12-15 09:15:19.043000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 09:15:19.043000',NULL),(98,2,1,'2022-12-15 09:15:51.461000','2022-12-15 09:15:51.512000','2022-12-15 09:15:51.533000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 09:15:51.534000',NULL),(99,2,1,'2022-12-15 09:18:59.944000','2022-12-15 09:18:59.999000','2022-12-15 09:19:00.024000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 09:19:00.025000',NULL),(100,2,1,'2022-12-15 09:19:25.885000','2022-12-15 09:19:25.926000','2022-12-15 09:19:25.947000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 09:19:25.948000',NULL),(101,2,1,'2022-12-15 09:45:18.705000','2022-12-15 09:45:18.749000','2022-12-15 09:45:18.764000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 09:45:18.765000',NULL),(102,2,1,'2022-12-15 09:49:46.041000','2022-12-15 09:49:46.087000','2022-12-15 09:49:46.102000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 09:49:46.103000',NULL),(103,2,1,'2022-12-15 10:06:12.108000','2022-12-15 10:06:12.156000','2022-12-15 10:06:12.174000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 10:06:12.175000',NULL),(104,2,1,'2022-12-15 10:06:26.970000','2022-12-15 10:06:27.023000','2022-12-15 10:06:27.037000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 10:06:27.038000',NULL),(105,2,1,'2022-12-15 10:07:54.472000','2022-12-15 10:07:54.536000','2022-12-15 10:07:54.557000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 10:07:54.559000',NULL),(106,2,1,'2022-12-15 10:12:01.461000','2022-12-15 10:12:01.516000','2022-12-15 10:12:01.539000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 10:12:01.540000',NULL),(107,2,1,'2022-12-15 10:13:28.431000','2022-12-15 10:13:28.492000','2022-12-15 10:13:28.515000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 10:13:28.516000',NULL),(108,2,1,'2022-12-15 10:45:47.875000','2022-12-15 10:45:47.914000','2022-12-15 10:45:47.929000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 10:45:47.930000',NULL),(109,2,1,'2022-12-15 10:47:06.482000','2022-12-15 10:47:06.532000','2022-12-15 10:47:06.546000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 10:47:06.547000',NULL),(110,2,1,'2022-12-15 10:49:14.556000','2022-12-15 10:49:14.627000','2022-12-15 10:49:14.649000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 10:49:14.650000',NULL),(111,2,1,'2022-12-15 10:53:45.382000','2022-12-15 10:53:45.429000','2022-12-15 10:53:45.445000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 10:53:45.445000',NULL),(112,2,1,'2022-12-15 10:59:36.653000','2022-12-15 10:59:36.714000','2022-12-15 10:59:36.734000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 10:59:36.735000',NULL),(113,2,1,'2022-12-15 13:00:31.601000','2022-12-15 13:00:31.649000','2022-12-15 13:00:31.666000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-15 13:00:31.667000',NULL),(114,2,1,'2022-12-16 10:37:14.473000','2022-12-16 10:37:14.554000','2022-12-16 10:37:14.583000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-16 10:37:14.584000',NULL),(115,2,1,'2022-12-16 10:38:44.180000','2022-12-16 10:38:44.230000','2022-12-16 10:38:44.247000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-16 10:38:44.248000',NULL),(116,2,1,'2022-12-16 19:04:57.238000','2022-12-16 19:04:57.283000','2022-12-16 19:04:57.295000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-16 19:04:57.296000',NULL),(117,2,1,'2022-12-19 11:27:54.910000','2022-12-19 11:27:54.953000','2022-12-19 11:27:54.970000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-19 11:27:54.971000',NULL),(118,2,1,'2022-12-19 12:21:13.880000','2022-12-19 12:21:13.923000','2022-12-19 12:21:13.939000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-19 12:21:13.940000',NULL),(119,2,1,'2022-12-19 12:22:07.944000','2022-12-19 12:22:07.990000','2022-12-19 12:22:08.008000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-19 12:22:08.009000',NULL),(120,2,1,'2022-12-19 13:11:07.523000','2022-12-19 13:11:07.573000','2022-12-19 13:11:07.589000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-19 13:11:07.590000',NULL),(121,2,1,'2022-12-19 14:02:25.918000','2022-12-19 14:02:25.995000','2022-12-19 14:02:26.051000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-19 14:02:26.052000',NULL),(122,2,1,'2022-12-19 14:03:48.841000','2022-12-19 14:03:48.887000','2022-12-19 14:03:48.902000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-19 14:03:48.902000',NULL),(123,2,1,'2022-12-19 14:05:59.960000','2022-12-19 14:06:00.018000','2022-12-19 14:06:00.040000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-19 14:06:00.041000',NULL),(124,2,1,'2022-12-19 14:07:07.177000','2022-12-19 14:07:07.231000','2022-12-19 14:07:07.248000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-19 14:07:07.249000',NULL),(125,2,1,'2022-12-19 15:30:18.590000','2022-12-19 15:30:18.641000','2022-12-19 15:30:18.660000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-19 15:30:18.660000',NULL),(126,2,1,'2022-12-19 15:38:05.130000','2022-12-19 15:38:05.178000','2022-12-19 15:38:05.195000','COMPLETED','NOOP','All steps already completed or no steps configured for this job.','2022-12-19 15:38:05.195000',NULL);
/*!40000 ALTER TABLE `batch_job_execution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `batch_job_execution_context`
--

DROP TABLE IF EXISTS `batch_job_execution_context`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_job_execution_context` (
  `JOB_EXECUTION_ID` bigint NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_CTX_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch_job_execution_context`
--

LOCK TABLES `batch_job_execution_context` WRITE;
/*!40000 ALTER TABLE `batch_job_execution_context` DISABLE KEYS */;
INSERT INTO `batch_job_execution_context` VALUES (1,'{\"@class\":\"java.util.HashMap\"}',NULL),(2,'{\"@class\":\"java.util.HashMap\"}',NULL),(3,'{\"@class\":\"java.util.HashMap\"}',NULL),(4,'{\"@class\":\"java.util.HashMap\"}',NULL),(5,'{\"@class\":\"java.util.HashMap\"}',NULL),(6,'{\"@class\":\"java.util.HashMap\"}',NULL),(7,'{\"@class\":\"java.util.HashMap\"}',NULL),(8,'{\"@class\":\"java.util.HashMap\"}',NULL),(9,'{\"@class\":\"java.util.HashMap\"}',NULL),(10,'{\"@class\":\"java.util.HashMap\"}',NULL),(11,'{\"@class\":\"java.util.HashMap\"}',NULL),(12,'{\"@class\":\"java.util.HashMap\"}',NULL),(13,'{\"@class\":\"java.util.HashMap\"}',NULL),(14,'{\"@class\":\"java.util.HashMap\"}',NULL),(15,'{\"@class\":\"java.util.HashMap\"}',NULL),(16,'{\"@class\":\"java.util.HashMap\"}',NULL),(17,'{\"@class\":\"java.util.HashMap\"}',NULL),(18,'{\"@class\":\"java.util.HashMap\"}',NULL),(19,'{\"@class\":\"java.util.HashMap\"}',NULL),(20,'{\"@class\":\"java.util.HashMap\"}',NULL),(21,'{\"@class\":\"java.util.HashMap\"}',NULL),(22,'{\"@class\":\"java.util.HashMap\"}',NULL),(23,'{\"@class\":\"java.util.HashMap\"}',NULL),(24,'{\"@class\":\"java.util.HashMap\"}',NULL),(25,'{\"@class\":\"java.util.HashMap\"}',NULL),(26,'{\"@class\":\"java.util.HashMap\"}',NULL),(27,'{\"@class\":\"java.util.HashMap\"}',NULL),(28,'{\"@class\":\"java.util.HashMap\"}',NULL),(29,'{\"@class\":\"java.util.HashMap\"}',NULL),(30,'{\"@class\":\"java.util.HashMap\"}',NULL),(31,'{\"@class\":\"java.util.HashMap\"}',NULL),(32,'{\"@class\":\"java.util.HashMap\"}',NULL),(33,'{\"@class\":\"java.util.HashMap\"}',NULL),(34,'{\"@class\":\"java.util.HashMap\"}',NULL),(35,'{\"@class\":\"java.util.HashMap\"}',NULL),(36,'{\"@class\":\"java.util.HashMap\"}',NULL),(37,'{\"@class\":\"java.util.HashMap\"}',NULL),(38,'{\"@class\":\"java.util.HashMap\"}',NULL),(39,'{\"@class\":\"java.util.HashMap\"}',NULL),(40,'{\"@class\":\"java.util.HashMap\"}',NULL),(41,'{\"@class\":\"java.util.HashMap\"}',NULL),(42,'{\"@class\":\"java.util.HashMap\"}',NULL),(43,'{\"@class\":\"java.util.HashMap\"}',NULL),(44,'{\"@class\":\"java.util.HashMap\"}',NULL),(45,'{\"@class\":\"java.util.HashMap\"}',NULL),(46,'{\"@class\":\"java.util.HashMap\"}',NULL),(47,'{\"@class\":\"java.util.HashMap\"}',NULL),(48,'{\"@class\":\"java.util.HashMap\"}',NULL),(49,'{\"@class\":\"java.util.HashMap\"}',NULL),(50,'{\"@class\":\"java.util.HashMap\"}',NULL),(51,'{\"@class\":\"java.util.HashMap\"}',NULL),(52,'{\"@class\":\"java.util.HashMap\"}',NULL),(53,'{\"@class\":\"java.util.HashMap\"}',NULL),(54,'{\"@class\":\"java.util.HashMap\"}',NULL),(55,'{\"@class\":\"java.util.HashMap\"}',NULL),(56,'{\"@class\":\"java.util.HashMap\"}',NULL),(57,'{\"@class\":\"java.util.HashMap\"}',NULL),(58,'{\"@class\":\"java.util.HashMap\"}',NULL),(59,'{\"@class\":\"java.util.HashMap\"}',NULL),(60,'{\"@class\":\"java.util.HashMap\"}',NULL),(61,'{\"@class\":\"java.util.HashMap\"}',NULL),(62,'{\"@class\":\"java.util.HashMap\"}',NULL),(63,'{\"@class\":\"java.util.HashMap\"}',NULL),(64,'{\"@class\":\"java.util.HashMap\"}',NULL),(65,'{\"@class\":\"java.util.HashMap\"}',NULL),(66,'{\"@class\":\"java.util.HashMap\"}',NULL),(67,'{\"@class\":\"java.util.HashMap\"}',NULL),(68,'{\"@class\":\"java.util.HashMap\"}',NULL),(69,'{\"@class\":\"java.util.HashMap\"}',NULL),(70,'{\"@class\":\"java.util.HashMap\"}',NULL),(71,'{\"@class\":\"java.util.HashMap\"}',NULL),(72,'{\"@class\":\"java.util.HashMap\"}',NULL),(73,'{\"@class\":\"java.util.HashMap\"}',NULL),(74,'{\"@class\":\"java.util.HashMap\"}',NULL),(75,'{\"@class\":\"java.util.HashMap\"}',NULL),(76,'{\"@class\":\"java.util.HashMap\"}',NULL),(77,'{\"@class\":\"java.util.HashMap\"}',NULL),(78,'{\"@class\":\"java.util.HashMap\"}',NULL),(79,'{\"@class\":\"java.util.HashMap\"}',NULL),(80,'{\"@class\":\"java.util.HashMap\"}',NULL),(81,'{\"@class\":\"java.util.HashMap\"}',NULL),(82,'{\"@class\":\"java.util.HashMap\"}',NULL),(83,'{\"@class\":\"java.util.HashMap\"}',NULL),(84,'{\"@class\":\"java.util.HashMap\"}',NULL),(85,'{\"@class\":\"java.util.HashMap\"}',NULL),(86,'{\"@class\":\"java.util.HashMap\"}',NULL),(87,'{\"@class\":\"java.util.HashMap\"}',NULL),(88,'{\"@class\":\"java.util.HashMap\"}',NULL),(89,'{\"@class\":\"java.util.HashMap\"}',NULL),(90,'{\"@class\":\"java.util.HashMap\"}',NULL),(91,'{\"@class\":\"java.util.HashMap\"}',NULL),(92,'{\"@class\":\"java.util.HashMap\"}',NULL),(93,'{\"@class\":\"java.util.HashMap\"}',NULL),(94,'{\"@class\":\"java.util.HashMap\"}',NULL),(95,'{\"@class\":\"java.util.HashMap\"}',NULL),(96,'{\"@class\":\"java.util.HashMap\"}',NULL),(97,'{\"@class\":\"java.util.HashMap\"}',NULL),(98,'{\"@class\":\"java.util.HashMap\"}',NULL),(99,'{\"@class\":\"java.util.HashMap\"}',NULL),(100,'{\"@class\":\"java.util.HashMap\"}',NULL),(101,'{\"@class\":\"java.util.HashMap\"}',NULL),(102,'{\"@class\":\"java.util.HashMap\"}',NULL),(103,'{\"@class\":\"java.util.HashMap\"}',NULL),(104,'{\"@class\":\"java.util.HashMap\"}',NULL),(105,'{\"@class\":\"java.util.HashMap\"}',NULL),(106,'{\"@class\":\"java.util.HashMap\"}',NULL),(107,'{\"@class\":\"java.util.HashMap\"}',NULL),(108,'{\"@class\":\"java.util.HashMap\"}',NULL),(109,'{\"@class\":\"java.util.HashMap\"}',NULL),(110,'{\"@class\":\"java.util.HashMap\"}',NULL),(111,'{\"@class\":\"java.util.HashMap\"}',NULL),(112,'{\"@class\":\"java.util.HashMap\"}',NULL),(113,'{\"@class\":\"java.util.HashMap\"}',NULL),(114,'{\"@class\":\"java.util.HashMap\"}',NULL),(115,'{\"@class\":\"java.util.HashMap\"}',NULL),(116,'{\"@class\":\"java.util.HashMap\"}',NULL),(117,'{\"@class\":\"java.util.HashMap\"}',NULL),(118,'{\"@class\":\"java.util.HashMap\"}',NULL),(119,'{\"@class\":\"java.util.HashMap\"}',NULL),(120,'{\"@class\":\"java.util.HashMap\"}',NULL),(121,'{\"@class\":\"java.util.HashMap\"}',NULL),(122,'{\"@class\":\"java.util.HashMap\"}',NULL),(123,'{\"@class\":\"java.util.HashMap\"}',NULL),(124,'{\"@class\":\"java.util.HashMap\"}',NULL),(125,'{\"@class\":\"java.util.HashMap\"}',NULL),(126,'{\"@class\":\"java.util.HashMap\"}',NULL);
/*!40000 ALTER TABLE `batch_job_execution_context` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `batch_job_execution_params`
--

DROP TABLE IF EXISTS `batch_job_execution_params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_job_execution_params` (
  `JOB_EXECUTION_ID` bigint NOT NULL,
  `TYPE_CD` varchar(6) NOT NULL,
  `KEY_NAME` varchar(100) NOT NULL,
  `STRING_VAL` varchar(250) DEFAULT NULL,
  `DATE_VAL` datetime(6) DEFAULT NULL,
  `LONG_VAL` bigint DEFAULT NULL,
  `DOUBLE_VAL` double DEFAULT NULL,
  `IDENTIFYING` char(1) NOT NULL,
  KEY `JOB_EXEC_PARAMS_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_PARAMS_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch_job_execution_params`
--

LOCK TABLES `batch_job_execution_params` WRITE;
/*!40000 ALTER TABLE `batch_job_execution_params` DISABLE KEYS */;
/*!40000 ALTER TABLE `batch_job_execution_params` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `batch_job_execution_seq`
--

DROP TABLE IF EXISTS `batch_job_execution_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_job_execution_seq` (
  `ID` bigint NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch_job_execution_seq`
--

LOCK TABLES `batch_job_execution_seq` WRITE;
/*!40000 ALTER TABLE `batch_job_execution_seq` DISABLE KEYS */;
INSERT INTO `batch_job_execution_seq` VALUES (126,'0');
/*!40000 ALTER TABLE `batch_job_execution_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `batch_job_instance`
--

DROP TABLE IF EXISTS `batch_job_instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_job_instance` (
  `JOB_INSTANCE_ID` bigint NOT NULL,
  `VERSION` bigint DEFAULT NULL,
  `JOB_NAME` varchar(100) NOT NULL,
  `JOB_KEY` varchar(32) NOT NULL,
  PRIMARY KEY (`JOB_INSTANCE_ID`),
  UNIQUE KEY `JOB_INST_UN` (`JOB_NAME`,`JOB_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch_job_instance`
--

LOCK TABLES `batch_job_instance` WRITE;
/*!40000 ALTER TABLE `batch_job_instance` DISABLE KEYS */;
INSERT INTO `batch_job_instance` VALUES (1,0,'listFacturesJob','d41d8cd98f00b204e9800998ecf8427e');
/*!40000 ALTER TABLE `batch_job_instance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `batch_job_seq`
--

DROP TABLE IF EXISTS `batch_job_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_job_seq` (
  `ID` bigint NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch_job_seq`
--

LOCK TABLES `batch_job_seq` WRITE;
/*!40000 ALTER TABLE `batch_job_seq` DISABLE KEYS */;
INSERT INTO `batch_job_seq` VALUES (1,'0');
/*!40000 ALTER TABLE `batch_job_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `batch_step_execution`
--

DROP TABLE IF EXISTS `batch_step_execution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_step_execution` (
  `STEP_EXECUTION_ID` bigint NOT NULL,
  `VERSION` bigint NOT NULL,
  `STEP_NAME` varchar(100) NOT NULL,
  `JOB_EXECUTION_ID` bigint NOT NULL,
  `START_TIME` datetime(6) NOT NULL,
  `END_TIME` datetime(6) DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `COMMIT_COUNT` bigint DEFAULT NULL,
  `READ_COUNT` bigint DEFAULT NULL,
  `FILTER_COUNT` bigint DEFAULT NULL,
  `WRITE_COUNT` bigint DEFAULT NULL,
  `READ_SKIP_COUNT` bigint DEFAULT NULL,
  `WRITE_SKIP_COUNT` bigint DEFAULT NULL,
  `PROCESS_SKIP_COUNT` bigint DEFAULT NULL,
  `ROLLBACK_COUNT` bigint DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  KEY `JOB_EXEC_STEP_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_STEP_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch_step_execution`
--

LOCK TABLES `batch_step_execution` WRITE;
/*!40000 ALTER TABLE `batch_step_execution` DISABLE KEYS */;
INSERT INTO `batch_step_execution` VALUES (1,3,'processingStep',1,'2022-02-05 19:24:53.665000','2022-02-05 19:24:53.810000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-02-05 19:24:53.811000');
/*!40000 ALTER TABLE `batch_step_execution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `batch_step_execution_context`
--

DROP TABLE IF EXISTS `batch_step_execution_context`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_step_execution_context` (
  `STEP_EXECUTION_ID` bigint NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  CONSTRAINT `STEP_EXEC_CTX_FK` FOREIGN KEY (`STEP_EXECUTION_ID`) REFERENCES `batch_step_execution` (`STEP_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch_step_execution_context`
--

LOCK TABLES `batch_step_execution_context` WRITE;
/*!40000 ALTER TABLE `batch_step_execution_context` DISABLE KEYS */;
INSERT INTO `batch_step_execution_context` VALUES (1,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL);
/*!40000 ALTER TABLE `batch_step_execution_context` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `batch_step_execution_seq`
--

DROP TABLE IF EXISTS `batch_step_execution_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_step_execution_seq` (
  `ID` bigint NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch_step_execution_seq`
--

LOCK TABLES `batch_step_execution_seq` WRITE;
/*!40000 ALTER TABLE `batch_step_execution_seq` DISABLE KEYS */;
INSERT INTO `batch_step_execution_seq` VALUES (1,'0');
/*!40000 ALTER TABLE `batch_step_execution_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_adresse`
--

DROP TABLE IF EXISTS `t_adresse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_adresse` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code_postal` varchar(5) NOT NULL DEFAULT '0',
  `localite` varchar(255) NOT NULL DEFAULT '0',
  `numero` varchar(100) NOT NULL DEFAULT '0',
  `pays` varchar(30) NOT NULL DEFAULT '0',
  `rue` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_adresse`
--

LOCK TABLES `t_adresse` WRITE;
/*!40000 ALTER TABLE `t_adresse` DISABLE KEYS */;
INSERT INTO `t_adresse` VALUES (1,'92500','Rueil-Malmaison','111','FRANCE','Boulevard National'),(2,'92500','Rueil-Malmaison','13','FRANCE','Domaine de la c√¥te noire'),(3,'75005','Paris','5','FRANCE','Rue Th√©nard'),(4,'92400','Courbevoie La D√©fense','4','FRANCE','Place des Vosges'),(5,'75005','Paris','5','FRANCE','Rue Th√©nard'),(6,'92400','Courbevoie La D√©fense','4','FRANCE','Place des Vosges'),(7,'92400','Courbevoie La D√©fense','4','FRANCE','Place des Vosges'),(8,'75005','Paris','5','FRANCE','Rue Th√©nard'),(9,'92400','Courbevoie La D√©fense','4','FRANCE','Place des Vosges'),(10,'92400','Courbevoie La D√©fense','4','FRANCE','Place des Vosges'),(11,'92400','Courbevoie La D√©fense','4','FRANCE','Place des Vosges'),(12,'92400','Courbevoie La D√©fense','4','FRANCE','Place des Vosges'),(13,'75005','Paris','6','FRANCE','Rue Th√©nard'),(14,'92400','Courbevoie La D√©fense','5','FRANCE','Place des Vosges'),(15,'75009','Paris','15','FRANCE','rue Taitbout'),(16,'75009','Paris','15','FRANCE','rue Taitbout'),(17,'75009','Paris','15','FRANCE','rue Taitbout'),(18,'75009','Paris','15','FRANCE','rue Taitbout');
/*!40000 ALTER TABLE `t_adresse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_client`
--

DROP TABLE IF EXISTS `t_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_client` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `social_reason` varchar(255) NOT NULL DEFAULT '0',
  `adresse_id` bigint DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgqaooiuekeicdqpuvfs2lgc22` (`adresse_id`),
  KEY `FKrmxmvd7yuj589w2k2te7qrb6m` (`company_id`),
  CONSTRAINT `FKgqaooiuekeicdqpuvfs2lgc22` FOREIGN KEY (`adresse_id`) REFERENCES `t_adresse` (`id`),
  CONSTRAINT `FKrmxmvd7yuj589w2k2te7qrb6m` FOREIGN KEY (`company_id`) REFERENCES `t_company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_client`
--

LOCK TABLES `t_client` WRITE;
/*!40000 ALTER TABLE `t_client` DISABLE KEYS */;
INSERT INTO `t_client` VALUES (1,'odyssey.consulting@odyssey.com','Odyssey Consulting',13,1),(2,'emagine.consulting@emagine.com','Emagine Consulting',14,1),(3,'easy.partner@easy-partner.fr','Easy Partner',18,1);
/*!40000 ALTER TABLE `t_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_company`
--

DROP TABLE IF EXISTS `t_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_company` (
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
  PRIMARY KEY (`id`),
  KEY `FKbjx03vmm17db3lx4ocrscixs9` (`adresse_id`),
  CONSTRAINT `FKbjx03vmm17db3lx4ocrscixs9` FOREIGN KEY (`adresse_id`) REFERENCES `t_adresse` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_company`
--

LOCK TABLES `t_company` WRITE;
/*!40000 ALTER TABLE `t_company` DISABLE KEYS */;
INSERT INTO `t_company` VALUES (1,'6201Z','PSSTFRPPSCE','FR1720041010125407961J03367','FR18831502141','R.C.S. Nanterre 831 502 141','85292702900011','SBATEC Consulting','SASU au capital de 500 Euros',1),(2,'6201Z','CRLYFRPP','FR3330002008970000005896J14','FR18831502141','R.C.S. Nanterre 831 502 141','83150214100011','ALIATECK','SASU au capital de 500 Euros',2);
/*!40000 ALTER TABLE `t_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_consultant`
--

DROP TABLE IF EXISTS `t_consultant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_consultant` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) NOT NULL DEFAULT '0',
  `first_name` varchar(255) NOT NULL DEFAULT '0',
  `email` varchar(255) NOT NULL,
  `fonction` varchar(255) NOT NULL DEFAULT '0',
  `company_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2vo95j5qxtnn4nbn1sieob907` (`company_id`),
  CONSTRAINT `FK2vo95j5qxtnn4nbn1sieob907` FOREIGN KEY (`company_id`) REFERENCES `t_company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_consultant`
--

LOCK TABLES `t_consultant` WRITE;
/*!40000 ALTER TABLE `t_consultant` DISABLE KEYS */;
INSERT INTO `t_consultant` VALUES (1,'ALIANE','Mustapha','mustapha.aliane@free.fr','D√©veloppeur Fullstack',1),(2,'ALIANE','Khalid','khalid@hotmail.fr','D√©veloppeur Fullstack',2),(3,'JERY','Hamed','jery@hotmail.fr','Assistance Technique Business Intelligence',2);
/*!40000 ALTER TABLE `t_consultant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_exercise`
--

DROP TABLE IF EXISTS `t_exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_exercise` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `exercise` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_exercise`
--

LOCK TABLES `t_exercise` WRITE;
/*!40000 ALTER TABLE `t_exercise` DISABLE KEYS */;
INSERT INTO `t_exercise` VALUES (4,'2023'),(3,'2022'),(2,'2021'),(1,'Tous');
/*!40000 ALTER TABLE `t_exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_facture`
--

DROP TABLE IF EXISTS `t_facture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_facture` (
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
  `montanttva` float NOT NULL DEFAULT '0',
  `nb_jour_retard` bigint DEFAULT NULL,
  `numero_commande` varchar(255) NOT NULL,
  `numero_facture` varchar(255) DEFAULT NULL,
  `prix_totalht` float NOT NULL DEFAULT '0',
  `prix_totalttc` float NOT NULL DEFAULT '0',
  `quantite` float NOT NULL DEFAULT '0',
  `montant_tva` float NOT NULL,
  `status_desc` varchar(255) DEFAULT NULL,
  `tarifht` float NOT NULL,
  `facture_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4epk9gihnga26xmkj3ud9l5dw` (`facture_id`),
  CONSTRAINT `FK4epk9gihnga26xmkj3ud9l5dw` FOREIGN KEY (`facture_id`) REFERENCES `t_prestation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_facture`
--

LOCK TABLES `t_facture` WRITE;
/*!40000 ALTER TABLE `t_facture` DISABLE KEYS */;
INSERT INTO `t_facture` VALUES (1,'Odyssey Consulting','30/04/2021','30/04/2021','31/03/2021',30,'OK','\\2021\\03-Mars\\Facture Client\\Facture_Sbatec_Odyssey_202103_1000.pdf','Facture_Sbatec_Odyssey_202103_1000.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1431>>stream\nxúΩX\Àr\€6\›\Î+Ó¶ìd\∆a\‚\À;Y±S{bEëòŒ§M\ÀL!R\·\√uv˝Ü~Å?°üîeÿØ\Ë%Jñ!ö°¶\„ë\0 \Œ\≈}û\ÀœΩì®\«=à—¥w\Zı\ﬁˆ\\ò_)¸3ﬂÅ`\Õ{/\Œ(P\—U\Ô\È≥\ËìŸª\ŸB û\ﬂ( \‡áû\„Û\ÂÉ®g$\Õr>\Î=ùúÙ£\”≤¥®tô§3s$Å\Ÿwé˝\ÌwßçXª)e\‡s\Ê0∫F$€àÅ\À d>!Ñv47Ù©#ºm\’l(•píUZ\›\»|\nCY&Y*ı=\‘áπ°\Ó<,d.!0ÆT¢ü_J=óIë•∏A ,7òÙ\'\Ô@V\ÀERJ\rSFä\”*œä`m≠\«\ÿcg\‡LT[Z™<Wpä\‡hU\—\’pîÑ\‡\”¿	wz\ \Ÿh\0ß.:ÖŒàxYoê=îqY\Â™;åÜé\'\÷˜\€0\√:\"0éf\„\ƒd#Ù◊é(\‹è1K˙x3˝R\Í\À\·Úá`Å	?èxéÎÆùím£∫&!∫ÆS\È\ÔG≤\¬	}4JøÛ\√»ÖëÃìb\«)ò<<\≈G°vûr6\Óß]ïM∏îX≤\0\„GÑt’Æ\'åv\Ì@î\‹î \ÿ_H.4¥$ó\Ëó>w°\n\‹2IrUvá¡Hô-Qn◊Ñ#\–\n3\◊\¬_0\¬X\◊\\âFÛ9µ¿?á˛\Ë¥˚-	0\œw\ËÜn√ºVêõi5Wi	Ö\ %\‰µ\‘IQ\√W\–O™Tô\‚´¶≤T\≈\›1\ yd\“,Z`™Ú4Q9| ™º\Ÿu-?cœ≥§8:Ä\ÿXA[±˘#±1=MS¯ÉºêIsÅ†2jE\ÕÚiñ¶5\Í\„£LcâûX¿\¬\‹_\„d≠H¸M\Ê\›√Ä∫\Ã\’#Lñ:)\ÎTäL¢Q&P¥RîU\Â\Íø˚\nÇ+c\·N\Zì-¥JRò\ÊYbÃâb∑g∂\¬ﬂóFêè\⁄\»KW⁄®\ŸL)´[shâ\«\·)ˇÆ\Ãgπˆ$I\À:ˇ∑]œ§>Bã≥˘yàyL\À\∆\«pW_◊®]\Â\Ï•B\‚\–~P&î\‘e\Ê=YÜô\Èı\Ï\·/Wºıoµvoåõõ-´\…ˆ∑Ä∫ùlq\Ô™÷öã\\±±ı\„\Íˇ≤.íY\⁄0›ÆL\√TAè\Ì\¬z[!5D\ËZˇ∞∑±ΩKÄÇ\\¿é2 ì[®\ +¸u-∏.w\Ë>êefò}gºFçX\›=∂+\\`4wf‹Ñ6\«\∆Ú`\¬lπ\Á\⁄\»\—ı\ÿ\ﬁ`ès\0\« ∫\Ãf¶◊≥á\„2p\·∂{pˆplb|πe5\Ÿ\‚P∑ì\Ìa\ﬂ¿ô≠…à\Záä¢Å\…˜\Í\0≠¢huÒT\ \ÓBCX9\‡N#¶*¸É\–TD]K\»,U˘sñ\…\€\Œ\Ì!\¬!\r∑¿!+˛äHX\Âb°ìX~ƒí\»™SG\Ï0¸Akj∏*\·ú>ß§s≥Åe.±ê…¢Jn∞x qT∑ã$o\Í\÷Û\nøj-¨Ú]´\Ê\rª\r-`-â·≥±Z,%iH\∆\’Úù¿~\‰\¬\"ä@∂(B\'`ªD\·ùıç_ò$wÛ\—\›\0»îXê/≥i\”\‰u\€;\»ÿ®πk=G\‡0∞$à\›\Ì\nj†yf®7\‹`≠o∂¥,≤™YàØøaè\‚¿•ö\ƒ_ß£o∆∏\—\Ã\’#\“K qﬁô*\Zoí≥ºÆõÆp#\—pW´ì}]\ÌO\‹t±B\Zøj\◊\ÃK.q∑)g|5◊Ω\…˛\ÔÆH\‡∏¡:A<Ù!la\ e§6çÃõó\Ô\'ì\”˜0x3úº{ù_u7©πEÇK\”#†/]\ |iΩ\À\n%Z\\K\Ëø>\Ôªæà1¯~`I\œ/\Î•≥\≈k*úUZ#x¸\«ZI\‚Y@\œO˙\√c8S7ÅÎ∏É2W\0Ò\—\„\ËA>\‡˘à\„êY\ƒ89\√h2â\Œ∆£\—dœó^ˇfé1\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220205205556+01\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220205205556+01\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001514 00000 n \n0000001808 00000 n \n0000001896 00000 n \n0000001989 00000 n \n0000000015 00000 n \n0000002089 00000 n \n0000001773 00000 n \n0000002152 00000 n \n0000002206 00000 n \n0000002238 00000 n \n0000002342 00000 n \ntrailer\n<</Info 11 0 R/ID [<852be9e440d544feb50fd8cbfb538b82><b5ef7873100f63c53a6ed81c77151704>]/Root 10 0 R/Size 12>>\nstartxref\n2552\n%%EOF\n',0,'Mars',0,0,'N¬∞12.12.19.05.00.01','20210331-1000',10810,12972,23,2162,'Acquitt√©',470,1),(2,'Odyssey Consulting','30/05/2021','30/05/2021','30/04/2021',30,'OK','\\2021\\04-Avril\\Facture Client\\Facture_Sbatec_Odyssey_202104_1001.pdf','Facture_Sbatec_Odyssey_202104_1001.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1434>>stream\nxúµX\Õr\€6æ\Î)ˆ\“I2\„0\0\«7Y±S{bEëòŒ§M	\ÀL!R\·è\Î\‹˙}?B)«∞O\—%Jñ1ö!;	∞∞ƒ∑\ÿ\≈\Ó~\ÀœÉì``;\‡Çhp\Z\ﬁ\\ò_)¸3\ﬂg,/\Œ(P¡\’\‡\È≥\‡ìë›ä˜Ú∏æcπˆ\ÍA\‘1íz9õû\ŒNÜ¡\ÈFiíó∫àìπŸí¿¸;\€˛ˆ;éQ≠\÷~@J∏6≥\› í]DO0ôÑ˘@°\Õ	]jqg\◊4[<J)ú§•V72ã`,ã8M§æá˙`3\·[˛\ﬁ\Õ|&Åi©b˝¸RÍÖåÛ4\È\·o9¡l8{≤ÑP.\„Bjà-N\À,\Õ{¿\Ê\Ó\∆[è±ß\÷»öYh∂§PY¶¿≥)Ç£WyW\«Q\‚ÉK=\À\ﬂ{SŒ¶@=l*≤PËåàáuv\0\ŸC@e¶∫\√ﬂ∑æ9ﬂÖˇ\”Å\Ÿ\Ë6õ¥ô\√˝µ#ä-∏eÉ\√XK˙x}\…sı•ø¸¡ôg\¬\œ!é%\ƒ\ÊR≤]Ta\"Ç\Î*¡ê˛~$s\Œ-\ﬂEG†ˆ{˜q1åLd\Á{v¡d\‡\‡..*µwó≥\Èp<:\Ìjl\¬AP“í9§´u]ba˙mÚ\Ô[ÚWπÖ˙-π%¯e\«]A(FÇ\›2ã3Utá¡@pY[û\‹-	G†˙Ù\·/a¨k™ƒàpm\⁄ˇÜì\”\Óß$¿◊¢[∫ÛZAˆmÆ\’B%\‰*ìêUR\«y_A?)ejS®ràd°Úªc\‘Û\»dYÙ@§≤$V|JÀ¨ñ∫ñü1àiúı†6\–Fm˚ë⁄òù¢\ÿ\‘˝\‹ /e\\†TF[Q\”,Jì§B{|îI(Ò&\Ê∞4\Á\◊8\ŸìY˜0†Çµıs•éã*ï ë®\rÅÅÅ˘Ω§e±˛ÔæÅ\‡\ x8DÖ\„\⁄eK≠\‚¢,çç;Q\Ìf\œF˘˚\⁄p≤¶Q[B©\√Rs ô)dyk6-pª><µ?<É+3\≈¿Y≠=âì¢\ ˛-@Ws©èäÖ\Èbâ4\ƒ<¶e}\«P™\nØ+¥Æ≤2!±<h>®j*á\≈@ \…03Ωô=\√’ä≥ëq\÷k˜∆∞ﬁ∞YOvá∞\‘\ÕdwWç≤≠πH≠ØˇóUœìö\Ëv%\Z\ËB¡©\Â∞}XoKdÜx∫ñ?lm⁄Å\ﬁ%=† hGôdÒ-îeÇ~∫\÷[a[Ù\»\"5ƒæ3^mF,\Ó€ó.0öª\ Ò\râ∞±Ø¸ˇIDÊà∂\Ô É¡ß\0\Î*òô\ﬁ\Ãé´`s\—\»\‡\Ï\·Xá¯Jd=\Ÿ\¬P7ì\›\·\–`≥∂#®\ÔSåL∫W=4äú€ñ\œÄJ\ÈùpX^∑=\€ÚúT$≥}∞TDÛEKƒ¨L˘sö\Â\»\€\Œ\Õ!\¬9º\rIÒWD\¬\n(óKáÚ#VD$ˇ~\Íà\Ì˚?hL\rU%\‹&\œi\Ôe∞z\“\¬%Û2æ¡⁄ÅºQ\›.\„¨.WX\ŒK¸™¥å±\»w-^ò7\⁄U®\…hMù\r\’r•I\Õ1ÆVo\„-™p$ã‹∑<∂Oªkkátîbí\‹OG˜3¿ê)iAæL£∫1»™¶uê°1s\◊ré¿æ◊í ˆw+hÅ˙\Ìôa\ﬁpÉ•æiH:§eΩ^\√≈ÇKµ¯àøF\„\›Q\–\Ã\’#\ŒK¿sQﬁπ\ \Î\€$\ÁYU\’M\·V£-†TcìCØ⁄ü(t±Föæj\÷\Ã+.qQó3{=◊É\Ÿ\·oÆàg	oì \ﬁ!\Ï`äU§\÷}Ãõó\Ôg≥\”˜0z3ûΩ{úè_uw)˜\Ì\r.Mã=\ﬁd±Æ-xY¢F\Àk	\√\◊\Á\√q\◊\◊0\ﬂıZ\“Û\À\ÍF\Ètπƒö\ng•\÷˛\—C\'Iú\–Ûì\·¯Œ¶\‘EA\¬q%(àã7é^\‰é\€C˚¨EçìÛ\—1Lf≥\‡l:ô\Ã|\Âılç\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220205205623+01\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220205205623+01\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001517 00000 n \n0000001811 00000 n \n0000001899 00000 n \n0000001992 00000 n \n0000000015 00000 n \n0000002092 00000 n \n0000001776 00000 n \n0000002155 00000 n \n0000002209 00000 n \n0000002241 00000 n \n0000002345 00000 n \ntrailer\n<</Info 11 0 R/ID [<7225a386f46f3c77c494e876e2bff3dd><2328338c66a697a3cc626d85969fa00b>]/Root 10 0 R/Size 12>>\nstartxref\n2555\n%%EOF\n',0,'Avril',0,0,'N¬∞12.12.19.05.00.01','20210430-1001',9635,11562,20.5,1927,'Acquitt√©',470,1),(3,'Odyssey Consulting','30/06/2021','30/06/2021','31/05/2021',30,'OK','\\2021\\05-Mai\\Facture Client\\Facture_Sbatec_Odyssey_202105_1002.pdf','Facture_Sbatec_Odyssey_202105_1002.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1431>>stream\nxúµXMr\€6\ﬁ\Îo\”I2\„0\0\«;Ÿ±{lEëòŒ§M\ÀL!R\·è\Î\ÏzÜû¿GËë≤{ä>P¢d\Ÿ¢\’ÒHÄ\ﬂ\√˚˝?˜é¢û\ÎA@<à&Ωì®˜∂\«\‡\‹¸JÅ\‡ü˘8Éh\÷{qJÅàÆzOüEü\Ã\ﬁıÒ\Ï˛C?Ù\ﬂ]<»Äz\ÊA\“,\Á\”\ﬁ\”ÒQ?:9Ü\„,-*]&\È\‘I`˙ùc˝\r\«I#\÷v@J¯.s]!íM\ƒ@0ôÑÖ@°\Õ\r}\ÍpoS5k<J)eïV72ü¿@ñIñJ}ı¡a\"t¬≠áÖL£J%˙˘•\‘3ôY∫á\‹rÉq¸d±ú\'•\‘0Q`§8©Ú¨\ÿ6˜W\÷zå=réù±ÉjKKï\Á\nó\"8Zïw5%!¯4p¬≠ûr:\Z\‡RÅ\ŒB°3\"^\÷\€\0de\\Vπ\Í#\¬\–Ò¯\Í^|fwG\Ê¢\Ÿ\\b	2è˙KGWp\«è1K˙x3˘R\Í\À˛ÚgÅ	?èxé+ßdõ®\¬D D\◊uä!˝˝H\Êú;°èÜ@È∑û\„c	\ <)∂úÇ\…¿\√S|j\Î)ß£˛\‡¯§´≤	Aâ%0r@HW\Ì˙\ƒ¡Ùk\n\Ô81X\Ó!∑\––í[¢ü˚p\ÿÑb$∏êqí´≤;Ç\œlyr≥$ÄVò∏^ÒÇ∆∫¶Jåﬂ•¯\Á\–ûtø%\Ê˘]\√\–Mò˘∑©V3ïñP®\\B^Kù5|˝§Jï©M±*`\"KU\‹¢ú&À¢&*Oï√ß¨ õ]\◊Ú3Ò,KäÉ=àç¥\€}$6fßIb\Í~aê\Á2i.∞TF≠®Y>\…“¥F}|îi,\—òõ˚kú¨âø…º{P¡,A=\ƒ\\©ì≤.@•H$\ZE```˛D+EYU.ˇªØ ∏2éQ\‡§1\Ÿ\\´$ÖIû%∆ú(v{f+¸}i8Y“®µ±\‘q•ç:êÃî≤∫5áñx\\û∫û¡ïôb\‡,÷û$iY\Áˇî†\Î©\‘\Ëbq6õ#\r1èi\Ÿ¯\Ó™\„\Î\Zµ´úùTHú\0\⁄ Ñí\n\∆a\÷H2\ÃLØf\«x±\‚≠ˆxÀµ{c\‹\ÿlYN6á∏\‘\Ìdsà{W≠∞\÷\\$¯\⁄÷èãˇÀ∫H¶iCtª\r4°\‡\‘Ò\ÿ6¨∑2CÙÅÆ\Â[;–ªt(H\Ï(\√<πÖ\n°L∞\¬\Î®kΩÆCwÅ,3C\Ï;\„5j\ƒ\‚\Ó±my\‡£πÎ•º–ê˚\ ˇüD¥`û∞µxwA∞;\ÿ\„\‡ba]§\03”´\Ÿ\√që\\.\⁄=8{86!æÿ≤úlq®\€\…\Ê∞k\npô≠≈à\Zä¢cì\Ó\’\ZE\Œ]\'\‰?@•Ùé˝CX¸8pù¿˚OE2ªñäh°∞D\ÃBïØ≥ºÄH\ﬁvn\Œ\„68$\≈_	+†ú\œuÀèX\r˘áü:bá\·\ZSCUâp\ÈsJHWŒäeÇX∏dQ%7X;ê7™\€yí7\Â\n\ÀyÖ_µñ	˘Æ\≈ÛÜ]ÑÜå∞í\ƒ\–\ŸX\Õí4\„jÒF`7naÖ#Y\‰°∞m¢∏][;§£ì\‰v:∫ù\ÓôÚe6i\ZÉºn[5w-\Áñ±Ω[A\r4o\œÛÜ,ıÕññ§CV5Òı7lQ∏T≥è¯\Î\‰/Ù\Õ7öπz\ƒy	\ﬁ#\ ;UE\„Mrö\◊u\”Æ%ZC\0\Óju≤´´˝ÅõŒóH£W\Ìöy≈Ö!.ör\Ê.\Á∫7\ﬁ˝\Õ	¨\ƒC\¬¶\\Dj\”«ºy˘~<>y\«o\„w\—\Ÿ\‡UwìÚ–µHpiZÙ•K\ÃFÅó\n4øñ–ø8\Î∫æÖ1~`\…\Œ/\Î•≥˘K*úVZ#x¸˚\ZI\‚Y@œé˙ÉC8Q7éÎ∏É2¡Å¯\ËpÙú ¸=Ñq\»,bù\¬p<éNG\√\·x\«7^ˇÑ\ÔçN\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220205205636+01\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220205205636+01\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001514 00000 n \n0000001808 00000 n \n0000001896 00000 n \n0000001989 00000 n \n0000000015 00000 n \n0000002089 00000 n \n0000001773 00000 n \n0000002152 00000 n \n0000002206 00000 n \n0000002238 00000 n \n0000002342 00000 n \ntrailer\n<</Info 11 0 R/ID [<721ab7c07d44d23bdbac51baedfab68e><9976328590a1cc424b042c2265ab719e>]/Root 10 0 R/Size 12>>\nstartxref\n2552\n%%EOF\n',0,'Mai',0,0,'N¬∞12.12.19.05.00.01','20210531-1002',9400,11280,20,1880,'Acquitt√©',470,1),(4,'Odyssey Consulting','30/07/2021','30/07/2021','30/06/2021',30,'OK','\\2021\\06-Juin\\Facture Client\\Facture_Sbatec_Odyssey_202106_1003.pdf','Facture_Sbatec_Odyssey_202106_1003.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1430>>stream\nxúµXMr\€6\ﬁ\Îo\”I2\„0\0˛y\'+vjO¨(”ô¥\È°`ô)D*¸qù]\œ\–¯=RñaO\—Jî,;B4u<`\ƒ˜~ø\«œΩì∏\Á˙\‚i\Ô4\ÓΩ\Ì1∏–øR ¯ßøC\Œ û˜^úQ†\‚´\ﬁ\”gÒ\'Ωw≥Ö@2øˇPH à|\'pó2†æ~ê¥\À≈¨˜tr“èO0»≥≤VUö\ÕÙëf\ﬂ9ˆ∑\ﬂqú∂b\Ì§îA\‡2á\—5\"\ŸF=Ä∞!\‘P\ﬂ0†˜∑U≥¡£î\¬I^+y#ä)Eï\ÊôP˜P\ÊEN¥Û∞àyÑ¿∏ñ©z~)\‘\\§eû\‡!7\‹`“üºQC\"i%L%h)N\Î\"/ÄÕÉµµcèùÅ3qPmY%ãBB\ËRG´r[\√QA@C\'\⁄\È)gc†a.ı\–Y(X#\‚e˝-@ˆP$U]H{/äüØ\Ô≈∑aÜˇX\"0\Õ\ÊCê˘å\–_-Q\\è;.¯å\“«õÈó≤î_ó?8u¯˘\ƒw<o\Ìîl\’\”ÒuìaH?í9\ÁN†!P˙ù\ÁFåDëñ;N¡d\‡\„)\nµÛî≥q88µU6\·\‡Qb\»åb´›Ä8ò~\Õ@\—]\ƒ˙\Î\0πÖFÜ\‹ˇ“ác[äë\‡\Z@&i!+{ÑÄôÚ\‰vI8%—¶/àˇÇ\∆lS%FD\‡R¸s\ËèN\ÌoIÄ˘ÅC70tÊµÑ\‚\€L…π\Ã*(e!†hÑJ\ÀæÇzRgR◊¶Dñ0ï,\ÔéQ\Œ#ùe\—SYd©,\‡S^\ÌÆkÒÉxûß\Â\—\ƒ\∆⁄â\Ì>≥\”4\’uø\‘\»ë∂8\0*£F‘ºò\ÊY÷†>>ä,\Ëâ%,Ù˝N÷ä\ƒ\ﬂDa\‘cÜ†aÆTi’î 3$≠\"000¢ï‚ºÆVˇ\›W\\i\'(p⁄öl°dö¡¥\»SmNª;≥˛æ4ú¨h\‘FÜD®§VZHf*Q\ﬂ\ÍC+<ÆÑO\›\œ\‡JO1pñkO“¨jä+P\ÕL®#t±$ü/êÜ\Ë«îh}w5\…uÉ⁄ï\Œ^*$N\›eBI=\∆a\ﬁÛêd\ËôZ\œé\…r\≈_\ÔÒWk˜∆§=∞›≤ölI®∫\…ˆêÙÆ:açπ\»\„[?.˛/õ2ùe-—µ%\ZhBèS\«gª∞\ﬁ\÷\»\—l\À∂6f†w\ŸPê\nòQFEz5B\È`Öüc\€z\Îπ\›≤\ 5±∑\∆k’à\≈\›gªÚ¿F≥\Ì•¸Hì˚\ ˇüDt`ægjÒÓ¢Ä\Ô\rˆ8∏XXó)@\œ\‘zˆp\\¶\0ó{\›ú=\€_nYM∂á§T\›d{\ÿ7∏\Ã\‘bƒ≠?\≈Ò@ß{yÄFës◊â¯P)Ω˘˛Ü0¯q\Ë:°ˇû\ \√R-Ú≥T\Â\œyQB,n≠õCÑÛπ	IÒWD\¬\n(ï&\‚#VDÜWÖü,±£\Ëç©¶™\ƒw\…sJàkQ\Êó,\ÎÙkÚFyªHã∂\\a9ØÒ´Q\"\≈\"o[º0oòEh\…h	kI4ùM\‰b)I\À1Æñoˆ\„Q8íE9!\€%äk\€\⁄!•ò$w\”\—\›\0»îê/Ûi\€M\◊:àD´Ÿ∂ú#p\Z\ƒ\Ón5–æ=\”\Ãn∞‘∑[:íy\›.$\◊ﬂ∞Eq\‡R\Œ?\‚Ø”ø\—7‹®\ÁÚ\Á%˙è(\ÔLñ≠7âY\—4mS∏ëh∏´\”…æÆˆ\'n∫X!ç_uk˙Ü∏◊ñ3w5WΩ\…˛oÆH\Ëx\·:A<Ù!\Ï`™e§∂}Ãõó\Ô\'ì\”˜0x3úº{ü_ŸõîGÆAÇK\›\"†/]\‘ÿäh\r^\÷(\—\‚Z@ˇıyh˚\ZF\„°!=øln§\ ¨©pV+Ö\‡\…\Ë$âo\0=?\Èè\·lL\‹H8Æ\„\ <$@è£˘Ä é#f\„\‰|p£\…$>èFì=_y˝0Kç\Ô\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220205205648+01\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220205205648+01\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001513 00000 n \n0000001807 00000 n \n0000001895 00000 n \n0000001988 00000 n \n0000000015 00000 n \n0000002088 00000 n \n0000001772 00000 n \n0000002151 00000 n \n0000002205 00000 n \n0000002237 00000 n \n0000002341 00000 n \ntrailer\n<</Info 11 0 R/ID [<ee25cc67be0984ec60a519b8542281d8><1338f692f593038592bbd224ff02878e>]/Root 10 0 R/Size 12>>\nstartxref\n2551\n%%EOF\n',0,'Juin',0,0,'N¬∞12.12.19.05.00.01','20210630-1003',9870,11844,21,1974,'Acquitt√©',470,1),(5,'Accor Hotels','30/08/2021','30/08/2021','31/07/2021',30,'OK','\\2021\\07-Juillet\\Facture Client\\Facture_Sbatec_Emagine_202107_1000.pdf','Facture_Sbatec_Emagine_202107_1000.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1440>>stream\nxúµXMr\€6\ﬁ\Îo\”I2\„0\0Ç§w≤\"7Òÿé\"1Y¥\È¶`ô)D*¸qº\Ïz°G\ 2\Ï)˙@âíeGàf®éG,Ä¯\ﬁ\Ô˜¯•wı\\M{√®˜æ\«\‡\Ã¸JÅ\‡ü˘8Éh\ﬁ{uJÅàÆ{\œ_Dü\Õ\ﬁ\ÕÒ¸\·C?é\Ô.d@Öyê4\À˘¨˜|r“èÜdiQ\È2Ig\ÊH≥˚˚8N±vR\ ¿wô\√\Ë\Zël#Éê˘@XÑ\⁄\–\‹–ß€™\Ÿ\‡QJ\·$´¥∫ï˘.eôd©\‘P\ÊÖN∏Û∞êyÑ¿∏Râ~y!ı\\&Eñ\‡∑\‹`“ü|\0YA,I)5L)ÜUû¿\Ê˛\⁄ZO±\«\Œ¿ô8®∂¥TyÆ p)Ç£UyW\√QÇO\'\‹\È)ßc†A\0.ı\–Y(tF\ƒÀä-@ˆP\∆eï´\Ó0^:ÇØ\Ô≈∑a.ˇ\Èà¿\\4õK,A&°øuDq=Ó†µcñ¸1ú\ÀYí™\√%NòrD8û∑ˆJ∂ç\ a§e¨0\n¯ò3U¸8û].\∆\—xÖùáÖåc4\r≤*øR∑Y¢\‡\\\¬\Î˙Z•Ö˙Ò©SÑ¡Û—óvûz:\Ó_Ü]M@PxJl\ŸM\“U\Â>q0)€Å¸˚Ä\ƒ˙\Î\0áÜñå}\Ï\√qWäÒ\·Z@&IÆ\ \Ó0.üŸ≤\Áv°8≠0ùΩ\"˛+F\Îö@±L˘.µ¿øÑ˛h\ÿ˝ñò∫Å°\€0\Á\nÚ\Ô3≠\Ê*-°PπÑºñ:)j¯˙Yïö8ÖCu*KU\‹£úG&˜¢¶*Oï\√gåæf◊ç¸R)ògIqt\0±±¨∂bªO\ƒ∆î5M(ÚB&\ÕÄ ®5ÀßYö÷®è+ô\∆=±ÄÖπø\∆\…Zë¯õÃªáıò%®G5R°§¨P)“ãFHî\–JQVï´ˇ*ÆçÖc8iL∂\–*IaögI\Ÿ\‰\„E{f+¸Ci8Yë´ç±\‘q•ç:ê‚î≤∫3áñx\\üûªü^¿µôb\‡,◊û%iY\Áˇñ†\Îô\‘G\Ëbq6_ 91èi\Ÿ¯\Ó™\„õ\Zµ´úΩTHú\0\⁄ ÑízX6\Ê=©áô\Èı\ÏÒ/W\ƒzèX≠=\„\Ê¿f\Àj≤=\ƒ-†n\'\€C‹ªnÖµ\Ê\"èol˝îºÆãdñ6Ù∑+˝@zú:Ç\Ì\¬z_!_D\ËZ˛∞\·±}HÄÇ\‘¿é2 ì;®\ +ºâ∫\÷[\œu\Ë>êef\Ë~gºFçX\‹€ï\Œ0öª^JÑÜD∏\ÿm˛ˇ$¢ûç\Z\›{\"\ÿ\Ïi\np±∞.SÄô\Èı\ÏÒ∏L.˜\⁄=8{<6!æ‹≤ölq®\€\…ˆ∞o\npô≠Òà\Zä¢ÅI˜\Í\0\Ì#˜®\È\r\Ï®\·=\'˚\€¡\‚∆Å\Î\‚\'4ïá!©àzñÄYjÚMñ…ª\Œ#\¬	nÉCN¸\rë∞\0\ \≈B\'±º¬Ç\»™KG\Ï0¸I∑jò*Aû˘ííŒù^îy\ƒB%ã*π\≈“Å¥Q\›-íº©VX\Õ+¸™µL∞\∆w≠]ò6\Ï\"4\\¥Äµ$Ü\Õ\∆j±î§°\◊\À\◊˚Qã(π\"ùÄ\Ì\≈\Ì¨o¸\¬πõç\Ó&Ä@¶ƒÇ|ëMõæ Ø\€\ŒA\∆F\Õ]´9áÅ%A\ÏnVP\Õ+5Cº\·+}≥•\Â\ËêU\ÕB|Û;.\‘¸\nù˛çæ\„F3WO(/Å@<aº3U4\ﬁ$gy]7=\·F¢\r\‡ÆV\'˚∫\⁄W\‹t∂B\Zˇ⁄Æô˜^\‚^S\Õ\‹\’\\˜&˚ø\Œ\"Å\„\ÎÒÿá∞Å)óë⁄¥1˝¡\‡\›ﬁºãÜ\Áì\Ó\∆\‰°k¡æ0Ωz\—Yïh≠ñÍª®Pú≈çÑ˛˘\€˛e\◊W0F?∞\‰\Ê\◊ı≠\“\ŸbÅıN+≠<˛Û\0]$–∑\'˝\Àc8S7éÎ∏É2èÒ\—\›\Ë¡™,¸q\»,búº\√h2âN«£\—d\œ\◊]ˇÃî`\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220205205725+01\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220205205725+01\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001523 00000 n \n0000001817 00000 n \n0000001905 00000 n \n0000001998 00000 n \n0000000015 00000 n \n0000002098 00000 n \n0000001782 00000 n \n0000002161 00000 n \n0000002215 00000 n \n0000002247 00000 n \n0000002351 00000 n \ntrailer\n<</Info 11 0 R/ID [<1213a88067780c0af3e82fd730c14dfa><8c610f92fcdb7457f239f11bb609dbe3>]/Root 10 0 R/Size 12>>\nstartxref\n2561\n%%EOF\n',0,'Juillet',0,0,'N¬∞13.21.19.05.14.01','20210731-1000',7840,9408,16,1568,'Acquitt√©',490,2),(6,'Accor Hotels','30/09/2021','30/09/2021','31/08/2021',30,'OK','\\2021\\08-Ao√ªt\\Facture Client\\Facture_Sbatec_Emagine_202108_1001.pdf','Facture_Sbatec_Emagine_202108_1001.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1441>>stream\nxúµXMr\€6\ﬁ\Îo\”I2\„0\0Ç§w≤\"7Òÿé\"1Y¥\È¶`ô)D*¸qΩ\Ïz°G\ 2\Ï∫\È%Jñ!ö°:	∞\0\‚{xø\ﬂ\„ó\ﬁI\‘sD@4\Ì\r£\ﬁ˚É3Û+Ç\Ê;\‡¢y\Ô\’)J ∫\Ó=}6{7[\ƒÛá¸P8æª|ê\ÊA\“,\Á≥\ﬁÛ\…I?\Z`ê•E•\À$ùô#	ÃæsÏØø\·8m\ƒ\⁄\rH)\ﬂe£kD≤çxB\Êa!BhG@sCü:\\l´fÉG)Öì¨\“\ÍV\ÊS∏îeí•R?@}tò:\·\Œ\√B\Ê\„J%˙\ÂÖ\‘sôYzÄ\‹rÉIÚd±\\$•\‘0U`§VyV\0õ˚kk=\≈;g\‚†\⁄\“RÂπÇ¿•éV\Â]\rGI>\rúpßßúéÅ∏\‘Cg°\–/+∂\0\Ÿc@óUÆ∫\√xa\ËææﬂÜπ¸ª#s\—l.±ô`Ñ˛\“\≈ı∏É\÷åYÚ\«p.gI™ó@8aN\»A\·x\ﬁ\⁄+\Ÿ6*áëñ±\¬H(\‡cV\ÃTÒ˝xvπpGs\‡v2é\—4»™¸J\›fâÇs	Ø\Îkï\Í˚ßrL\¬\œG_\⁄y\Í\È∏9v5A\·)±e7vDHWï˚\ƒ¡§lÚÓÉÄ ÷ü\»84¥dú\ËcéªÇPå\◊2IrUváqA¯Ãñ=∑\≈hÖ\È\Ï	^1\¬X\◊äe\ w©˛%ÙG\√\Ó∑$¿Ñ\Ô\–\r›Ü9Wêõi5Wi	Ö\ %\‰µ\‘IQ\√W\–œ™\‘\ƒ)\ƒ™SY™\‚˛\Â<2π-0Uyö®>cÙ5ªn\‰óJ¡<Kä£àçeµ\€}\"6¶¨ib\ÿ@aê2i.p\0TF≠®Y>\Õ“¥F}\\\…4ñ\Ëâ,\Ã˝5N÷ä\ƒ\ﬂd\ﬁ=®\«,A=™ë\n%e]ÄJë^4ä¿¿@¢ÑVä≤™\\˝˜PApm,£¿Ic≤ÖVI\n\”<K\ &/\⁄3[\·J\√…ä\\mdà•é+m‘Åßî’ù9¥\ƒ\„\n¯Ù\‹˝ÙÆ\Õgπˆ,I\À:ˇß]œ§>Bã≥˘…âyL\À\∆\«pW\ﬂ‘®]\Â\Ï•B\‚\–~P&î\‘√≤1\ÔyH=\ÃLØgè\«xπ\"\÷{\ƒj\Ì¡76[Vì\Ì!nu;\Ÿ\‚\ﬁu+¨5y|cÎßî\‡u]$≥¥°ø]\Èö\–\„\‘l\÷˚\n˘\"˙@\◊Úá\rè\ËCz\0§vîQû\‹AÖP&X\·M‘µ\ﬁzÆC˜Å,3C˜;\„5j\ƒ\‚.ÿÆ<pÜ\—\‹ıR\"4$\¬\≈nÛˇ\'-òl\‘\Ëû˙bo∞ß)¿\≈¬∫Lf¶◊≥\«\„2∏\‹k˜\‡\ÏÒÿÑ¯r\Àj≤=\ƒ-†n\'\€√æ)¿e∂\∆#j¸)ä&›´¥è‹£¶7∞£˙˜\ƒ\€\ﬂ7\\\'?†©<<IE¥–≥\ÃRìo≤ºÄH\ﬁu\ÓNpr‚ØàÑP.:â\ÂDÜWÖü:bá\·∫U\√T	∂˝/\È^\÷`ÒàÖJUrã•i£∫[$yS≠∞öW¯Ukô`ç\ÔZª0m\ÿEh∏hkIõç\’b)IC1ÆóØ	ˆ£Q8rE:\€%ä€µ≥C6J1G\Óf£ª	\‡ê)± _d”¶/\»\Î∂sê±Qs\◊jé¿a`Iªõ\‘@ÛJ\Õo∏\≈J\ﬂli9:dU≥\ﬂ|\√≈Å5ø\¬_ß°o∆∏\—\Ã\’\ K O\ÔLç7\…Y^\◊MO∏ëh∏´\’…æÆˆn:[!çn\◊\Ã{/qØ©f\ÓjÆ{ì˝_gë¿ÒÇuÇx\ÏC\ÿ¿î\ÀHm⁄ò˛`no\ﬁE\√ÛIwcÚ–µ`_ò\ﬁ`˙¨ü˝ªT\›EÖ¢,n$Ù\œ\ﬂˆ/ªæ~1~`\…ÀØ\Î[•≥\≈k)úVZ#x¸˚:H\",†oO˙ó\«p:¶>n$\◊qe‚£´\—3ÇY¯\‡êY\ƒ8y;8Ü\—dùéG£…ûØ∫˛0øìC\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220205210428+01\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220205210428+01\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001524 00000 n \n0000001818 00000 n \n0000001906 00000 n \n0000001999 00000 n \n0000000015 00000 n \n0000002099 00000 n \n0000001783 00000 n \n0000002162 00000 n \n0000002216 00000 n \n0000002248 00000 n \n0000002352 00000 n \ntrailer\n<</Info 11 0 R/ID [<a2daa45c767d6cdf7fa74c7aef52bf0b><3dc16442d6ed8d0be16785fa68462dc9>]/Root 10 0 R/Size 12>>\nstartxref\n2562\n%%EOF\n',0,'Ao√ªt',0,0,'N¬∞13.21.19.05.14.01','20210831-1001',5880,7056,12,1176,'Acquitt√©',490,2),(7,'Accor Hotels','30/10/2021','30/10/2021','30/09/2021',30,'OK','\\2021\\09-Septembre\\Facture Client\\Facture_Sbatec_Emagine_202109_1002.pdf','Facture_Sbatec_Emagine_202109_1002.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1445>>stream\nxúΩX\Àr\€6\›\Î+Ó¶ìd\∆a\‚\À;YëõxlGëò,\⁄tS∞\Ã\">/˚\r˝B?)À∞_\—Jî,;B4CM\«#@úã˚<ó_z\'Qè{¢ioı\ﬁ˜úô_)¸3ﬂÅ`\Õ{ØN)P\—u\Ô˘ã\Ë≥Ÿª\ŸB û?|( \‡áû\„Û\ÂÉ®g$\Õr>\Î=üúÙ£\·\0YZT∫L“ô9í¿\Ï\«˛˛é\”F¨›Äî29s]#ím\ƒ¿e2ÅB;ö˙\‘ﬁ∂j6xîR8\…*≠ne>ÖKY&Y*ı\‘Gáπ°\Ó<,d.!0ÆT¢_^H=óIë•∏A ,7òÙ\'@V\ÀERJ\rSFäaïg\≈∞Öø∂\÷S\Ï±3p&™--Uû+8Ep¥™\Ëj8JBi\‡Ñ;=\Ât4ÄSùÖBgDº¨∑\» ∏¨r\’\∆\rC\«\Î{âmò\À:\"0éf\„\ƒd#Ù∑é(\‹Z\€cÃí?Üs9KRu∏\"sBÒ\◊]{%\€F0\“2V	|Ãäô*~\œ\\xhº\¬\Œ\√B&0öYï_©\€,Qp.\·u}≠\“B˝¯TÅ)\¬Û¡ı—óvûz:\Ó_Ü]M@PxJ,πÅ±#B∫™\‹&\ÂŸÅ(π˜Ç` \Â\––ír¢è}8\Ó\nB1@∏dí\‰™\Ï\√¡Ûô-}nWä#\–\nç˙äÑØa¨kE£˘úZ\‡_B4\Ï~K\ÃÛ∫Å°\€0\Á\nÚ\Ô3≠\Ê*-°PπÑºñ:)j¯˙Yïö@Öcu*KU\‹£úG&˘¢¶*Oï\√gøf◊ç¸R)ògIqt\0±±Æ∂bÛ\'bcŒö&Üy!ì\Ê@e‘äö\Â\”,Mk\‘«ïLcâûX¿\¬\‹_\„d≠H¸M\Ê\›√Ä∫\Ã’£\ZπPR\÷®˘E£dJh•(´\ \’\◊\∆\¬1\nú4&[hï§0Õ≥§lÚ¢=≥˛°4Ç¨\ÿ\’FÜX\Í∏\“F\»qJY›ôCK<ÆÄO\œ˘ßpm¶8ÀµgIZ\÷˘ø%\Ëz&ı∫Xú\Õ\»N\ÃcZ6>Üª\Í¯¶F\Ì*g/\'ÄˆÉ2°§.÷çy\œE\Óafz={<\∆\ÀoΩ\«[≠=\„\Ê¿f\Àj≤=\ƒ-†n\'\€C‹ªnÖµ\Ê\"Wll˝îºÆãdñ6¸∑+ˇ0\’BP\«cª∞\ﬁWH\—∫\÷?\Ïx\Ï@\“† 7∞£åÚ\‰*Ñ2¡\no¢Æ\◊\Â\›≤\Ã\ﬂ\Ôå◊®´ª\«v\ÂÅ3å\Ê\Œ,Çõ\–\Ê\ÿn˛,Bx°Éç$˜\\9∫ßÆ∑7\ÿ\”¿±≤.sÄô\Èı\ÏÒ∏\Ã\\∏\Ìú=õ_nYM∂á∏\‘\Ìd{\ÿ7pfk=¢∆°¢h`ÚΩ:@)7t›éJ\Ÿ}\»˜7Ñ≈ë\Ó?c™\"<ME¥–µÑ\ÃRïo≤ºÄH\ﬁun\Zi∏YÒ7D\¬(ù\ƒÚ\nK\"√´\¬/±\√\'\r´\·™$\‰\‰%%§+i≈ã2óX\»dQ%∑X<ê8™ªEí7ı\n\ÎyÖ_µñ	V˘Æ\’ÛÜ]ÑÜç∞ñ\ƒ\ŸX-ñí4$\„z˘¶`?raE [°∞]¢Æ\ÕÚQäIr7\›MÄLâ˘\"õ6ùA^∑ΩÉåçöª\÷sKÇ\ÿ›Æ†ö∑jÜz\√-\÷˙fK\À\“!´öÖ¯\Ê;ˆ(\\®˘˛:˝}3∆çfÆûê^Å˜ÑÛ\ŒT\—xìú\Âu\›tÖâ6ÄªZù\Ï\Îj_q\”\Ÿ\ni¸kªf^}aàªM9„´π\ÓMˆ£E\«\r\÷	\‚±aS.#µid˙É¡ª1ºy\r\œ\'›ç)Bn¡æ0\›z\—µ¥4àQ\‡EÖ-n$Ù\œ\ﬂˆ/ªæá1B¯Å%;øÆoï\Œ,©pZiç\‡Òü\Ë$âg}{“ø<Ü\”1ıq#∏é;(séû§ûÄ0ôEåì∑ÉcM&\—\Èx4ö\Ï˘\Œ\Î?Éï\ƒ\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220205210556+01\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220205210556+01\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001528 00000 n \n0000001822 00000 n \n0000001910 00000 n \n0000002003 00000 n \n0000000015 00000 n \n0000002103 00000 n \n0000001787 00000 n \n0000002166 00000 n \n0000002220 00000 n \n0000002252 00000 n \n0000002356 00000 n \ntrailer\n<</Info 11 0 R/ID [<e1ba93643926f7772855bfedf9b77c8b><3fe01ef83693319678fcb37ed15e7894>]/Root 10 0 R/Size 12>>\nstartxref\n2566\n%%EOF\n',0,'Septembre',0,0,'N¬∞13.21.19.05.14.01','20210930-1002',10780,12936,22,2156,'Acquitt√©',490,2),(8,'Accor Hotels','30/11/2021','30/11/2021','31/10/2021',30,'OK','\\2021\\10-Octobre\\Facture Client\\Facture_Sbatec_Emagine_202110_1003.pdf','Facture_Sbatec_Emagine_202110_1003.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1441>>stream\nxúµXMr\€6\ﬁ\Îo\”I2\„0\0Ç§w≤\"7Òÿé\"1Y¥\È¶`ô)D*¸qº\Ïz°G\ 2\Ï)˙@âíeGàf®éG,Ä¯\ﬁ\Ô˜¯•wı\\M{√®˜æ\«\‡\Ã¸JÅ\‡ü˘8Éh\ﬁ{uJÅàÆ{\œ_Dü\Õ\ﬁ\ÕÒ¸\·C?é\Ô.d@Öyê4\À˘¨˜|r“èÜdiQ\È2Ig\ÊH≥˚˚8N±vR\ ¿wô\√\Ë\Zël#Éê˘@XÑ\⁄\–\‹–ß€™\Ÿ\‡QJ\·$´¥∫ï˘.eôd©\‘P\ÊÖN∏Û∞êyÑ¿∏Râ~y!ı\\&Eñ\‡∑\‹`“ü|\0YA,I)5L)ÜUû¿\Ê˛\⁄ZO±\«\Œ¿ô8®∂¥TyÆ p)Ç£UyW\√QÇO\'\‹\È)ßc†A\0.ı\–Y(tF\ƒÀä-@ˆP\∆eï´\Ó0^:ÇØ\Ô≈∑a.ˇ\Èà¿\\4õK,A&°øuDq=Ó†µcñ¸1ú\ÀYí™\√%NòrD8û∑ˆJ∂ç\ a§e¨0\n¯ò3U¸8û].\∆\—xÖùáÖåc4\r≤*øR∑Y¢\‡\\\¬\Î˙Z•Ö˙Ò©SÑ¡Û—óvûz:\Ó_Ü]M@PxJlŸçy§´\ }\‚`R∂˘˜îxGÑ¸uÄåCCK∆â>ˆ\·∏+\≈¯p- ì$WewÑ\œl\ŸsªPÅVò\Œ^QÚä∆∫&P,SæK-/°?\Zvø%&|án`\Ë6ÃπÇ¸˚L´πJK(T.!Ø•Nä\ZæÅ~V•&N!\∆Pù\ R˜\«(\Áë…ΩhÅ©\ \”D\Â£Ø\Ÿu#øT\n\ÊYR@l,´≠\ÿ\Ó±1eM\√\nÉºêIsÅ†2jE\ÕÚiñ¶5\Í\„J¶±DO,`a\ÓØq≤V$˛&Û\Óa@=f	\ÍQçT()\ÎTäÙ¢Q%¥RîU\ÂÍøá\nÇkc\·N\Zì-¥JRò\ÊYR6˘x—û\Ÿ\nˇP\ZNV\‰j#C,u\\i£§8•¨\ÓÃ°%W¿ß\Á\Óßpm¶8ÀµgIZ\÷˘ø%\Ëz&ı∫Xú\ÕHN\ÃcZ6>Üª\Í¯¶F\Ì*g/\'ÄˆÉ2°§ñçy\œC\Íafz={<\∆\À±\ﬁ#Vk∆∏9∞Ÿ≤ölq®\€\…ˆ˜Æ[a≠π\»\„[?•Ø\Î\"ô•\r˝\ÌJ?–Ñßé`ª∞\ﬁW\»\—∫ñ?lx\Ï@\“† 5∞£åÚ\‰*Ñ2¡\no¢Æı\÷s∫dô∫\ﬂØQ#w¡v\ÂÅ3åÊÆó°!.võˇ?âh¡Ñg£F˜ú—Ω¡û¶\0\Î2òô^\œè\À\‡rØ›É≥\«c\‚\À-´\…ˆ∑Ä∫ùl˚¶\0ó\Ÿ\Zè®Òß(\ZòtØ\–>rèö\ﬁ¿é\Z\‹{L¿\Ën\‡:Å¯	M\Â!9IE¥–≥\ÃRìo≤ºÄH\ﬁu\ÓNpr\‚oàÑP.:â\ÂDÜWÖ_:bá\·O∫U\√T)q\ÈKJà\€˝¢\Ã#*YT\…-ñ§ç\Ínë\‰Mµ\¬j^\·W≠eÇ5æk\Ì¬¥a°\·¢¨%1l6Vã•$\r≈∏^æ&ÿèZXD\·\»y\Ëló(n\◊\Œ\Ÿ(\≈πõç\Ó&Ä@¶ƒÇ|ëMõæ Ø\€\ŒA\∆F\Õ]´9áÅ%A\ÏnVP\Õ+5Cº\·+}≥•\Â\ËêU\ÕB|Û;.\‘¸\nù˛çæ\„F3WO(/Å@<aº3U4\ﬁ$gy]7=\·F¢\r\‡ÆV\'˚∫\⁄W\‹t∂B\Zˇ⁄Æô˜^\‚^S\Õ\‹\’\\˜&˚ø\Œ\"Å\„\ÎÒÿá∞Å)óë⁄¥1˝¡\‡\›ﬁºãÜ\Áì\Ó\∆\‰°k¡æ0Ω¡ÙŸª∏Ãå5åˆ.*îfq#°˛∂\Ÿı\råë¿,©˘u}´t∂X`9Ö\”Jkèˇ<@IÑÙ\ÌIˇÚN\«\‘«çÑ\„:\Ó†\Ã\„@|Ù6zF∞(ˇ\012ã\'o\«0öL¢\”Òh4\ŸÛm\◊\ÿﬁì\Ÿ\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220205210704+01\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220205210704+01\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001524 00000 n \n0000001818 00000 n \n0000001906 00000 n \n0000001999 00000 n \n0000000015 00000 n \n0000002099 00000 n \n0000001783 00000 n \n0000002162 00000 n \n0000002216 00000 n \n0000002248 00000 n \n0000002352 00000 n \ntrailer\n<</Info 11 0 R/ID [<2aab4b48b373b41437db345dcec5433b><7b4be2c9943f959c55beab63299331a0>]/Root 10 0 R/Size 12>>\nstartxref\n2562\n%%EOF\n',0,'Octobre',0,0,'N¬∞13.21.19.05.14.01','20211031-1003',7105,8526,14.5,1421,'Acquitt√©',490,2),(9,'Accor Hotels','30/12/2021','30/12/2021','30/11/2021',30,'OK','\\2021\\11-Novembre\\Facture Client\\Facture_Sbatec_Emagine_202111_1004.pdf','Facture_Sbatec_Emagine_202111_1004.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1440>>stream\nxúµXMr\€6\ﬁ\Îo\”I2\„0\0Ç§w≤\"7Òÿé\"1Y¥\È¶`ô)D*¸qΩ\Ïz°G\ 2\Ï)˙@âíeGà:T\«#@|\Ô˜{¸\“;âzÆÄÄà¶Ωa\‘{\ﬂcpf~•@\œ|úA4\ÔΩ:•@	D◊Ω\Á/¢\œf\ÔfÅx˛°ÄÄ\n\«wó2†\¬<Hö\Â|\÷{>9\ÈG\√≤¥®tô§3s$Å\Ÿwé˝ı7ßçXª)e\‡ª\ÃatçH∂èA\»| ,B\Ìhn\ËSáãm\’l(•píUZ\›\ |\nó≤L≤T\Í®èÛB\'\‹yX\»<B`\\©Døºêz.ì\"KpÉÄ[n0\ÈO>Ä¨ ñã§î\Z¶\nå\√*œä`sm≠ß\ÿcg\‡LT[Z™<W∏¡—™º´\·(	¡ßÅ\ÓÙî\”1\– \0óz\Ë,:#\‚e\≈ {(\„≤\ Uw/¡\◊˜\‚\€0ówD`.ö\Õ%ñ å\–_:¢∏w\–⁄Ç1K˛\Œ\Â,I\’\·\'\Ã	9\"\œ[{%\€F\Â0\“2V	|Ãäô*æ\œ.\„hº\¬\Œ\√B\∆1öYï_©\€,Qp.\·u}≠\“B}ˇTé)B¯\‡˘\ËK;O=˜/√Æ& (<%ñ\‹¿\»!]U\Óì≤(ºà¡˙Û\0áÜñå}\Ï\√qWäÒ\·Z@&IÆ\ \Ó0.üŸ≤\Áv°8≠–¶Ø(}\≈c](ñ)ﬂ•¯ó\–\rªﬂí\0æC70t\Ê\\A˛m¶\’\\•%*óê\◊R\'E\r_A?´Rßc®Ne©ä˚cîÛ\»\‰^¥¿T\Âi¢r¯å\—\◊Ï∫ë_*Û,)é 6ñ\’Vl˜âÿò≤¶âaÖA^»§π¿Pµ¢f˘4K\”\Zıq%\”X¢\'∞0˜\◊8Y+ìy˜0†≥ı®F*îîu*Ez\—(âZ) ™rı\ﬂC¡µ±på\'ç\…Z%)LÛ,)õ|ºh\œlÖ(\r\'+rµë!ñ:Æ¥QRúRVw\Ê\–è+\‡\”s˜\”∏6Sú\Â⁄≥$-\Î¸üt=ì˙],\Œ\Ê$\'\Ê1-\√]u|S£vï≥ó\nâ@˚AôPR\À∆º\Á!ı03Ωû=\„\ÂäX\Ô´µc\‹\ÿlYM∂á∏\‘\Ìd{à{◊≠∞\÷\\\‰Òç≠üRÇ\◊uë\Ã“Ü˛v•hBèSG∞]X\Ô+\‰ã\Ë]\À6<v†\ÈPê\Z\ÿQFyrBô`Ö7Q\◊z\Îπ\›≤\Ã\›\Ôå◊®ãª`ªÚ¿Fs\◊Kâ–êª\ÕˇüD¥`¬≥5~˜°\ÿ\Ïi\np±∞.SÄô\Èı\ÏÒ∏L.˜\⁄=8{<6!æ‹≤ölq®\€\…ˆ∞o\npô≠Òà\Zä¢ÅI˜\Í\0\Ì#\ÁÆ!\ÎvTJ\Ô˝ˇ`ãÆàTÑ•\"Z\ËY\"f©\ 7Y^@$\Ô:∑å\'∏\rIÒWD\¬\n(ù\ƒÚ\n+¢!ˇSG\Ï0¸Aªj®*•.yI	\·\›/\ <b\·íEï\‹b\Ì@ﬁ®\ÓIﬁî+,\Á~\’Z&X\‰ª/\Ãv\Z2Z¿ZCgcµXJ\“på\Î\Â{Ç˝∏ÖEédëáN¿vâ\‚vmÌêéRLíª\È\Ënx\0dJ,\»Ÿ¥iÚ∫mdl\‘‹µú#pX\ƒ\Ón5–ºS3\Ãn±\‘7[ZíY\’,\ƒ7ﬂ∞Eq\‡BÕØ\◊\È_\Ëõ1n4sıÑÛ\ƒ\ ;SE\„Mrñ\◊u\”n$\⁄@\0\Óju≤Ø´˝Åõ\ŒVH\„ü\€5Û\‚C\‹k ôªö\Î\ﬁdˇ˜Y$pº`ù ˚v0\Â2Rõ>¶?º√õw\—|\“›ò<t-\ÿ¶9@/∫\Ãnóˆ0˙ª®Pû≈çÑ˛˘\€˛e◊ó0F?∞$\Á\◊ı≠\“\ŸbÅN+≠<˛˝\0}$–∑\'˝\Àc8S7éÎ∏É2èÒ\—\ﬂ\ËA6 ¸Dq\»,búº\√h2âN«£\—d\œ^ˇ¯Gî\ﬁ\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220205210720+01\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220205210720+01\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001523 00000 n \n0000001817 00000 n \n0000001905 00000 n \n0000001998 00000 n \n0000000015 00000 n \n0000002098 00000 n \n0000001782 00000 n \n0000002161 00000 n \n0000002215 00000 n \n0000002247 00000 n \n0000002351 00000 n \ntrailer\n<</Info 11 0 R/ID [<4e32159e803c739f1b759401569bc6b9><eb3fa6c6242ae241645804879bbe4efe>]/Root 10 0 R/Size 12>>\nstartxref\n2561\n%%EOF\n',0,'Novembre',0,0,'N¬∞13.21.19.05.14.01','20211130-1004',9800,11760,20,1960,'Acquitt√©',490,2),(10,'Accor Hotels','30/01/2021','30/01/2022','31/12/2021',30,'OK','\\2021\\12-D√©cembre\\Facture Client\\Facture_Sbatec_Emagine_202112_1005.pdf','Facture_Sbatec_Emagine_202112_1005.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1443>>stream\nxúΩX\Àr\€6\›\Î+Ó¶ìd\∆a\‚\À;YñõxlGëò,\⁄tS∞\Ã\"U>\\/˚\r˝B?)À∞_\—Jî,;B4CM\«#@úã˚<óÙN¢˜  D\”\ﬁ0\Í}\Ë187øR ¯gæ¡ ö˜ﬁúQ†¢õ\ﬁ\ÀW\—≥w≥Ö@<¸P@¿=\«\Á\ÀP\œ<Hö\Â|\÷{99\ÈG\√≤¥®tô§3s$Å\Ÿwé˝ı7ßçXª)e\‡s\Ê0∫F$€àÅ\À d>!Ñv47Ù©#ºm\’l(•píUZ\›\…|\nW≤L≤T\ÍG®OsC\'\‹yX\»\\B`\\©Døæîz.ì\"KpÉ@Xn0\ÈO>Ç¨ ñã§î\Z¶\nå\√*œä`m≠\Á\ÿcg\‡LT[Z™<Wpä\‡hU\—\’pîÑ\‡\”¿	wz\ \Ÿh\0ß.:ÖŒàxYoê=îqY\Â™;åÜé\'\÷˜\€0WˇtD`\Õ∆â%\»<F\Ë/Q∏+¥∂«ò%\Árñ§\Íp	D\ÊÑ<\‚9ÆªˆJ∂ç*`§e¨0\n¯î3U|?ûπ&\–xÖùáÖL`4\r≤*øVwY¢\‡B\¬i}£\“B}ˇTÅ)\¬Û¡ı—óvûz6\Ó_\rÜ]M@PxJ,πÅÒ#B∫™\‹&\ÂŸÅ(}`>A∞øêrhhI9—ß>w° \‹2IrUvá\·\‡˘Ãñ>∑+\≈hÖ˘\Ï\reoa¨kE£˘úZ\‡_C4\Ï~K\ÃÛ∫Å°\€0\nÚo3≠\Ê*-°PπÑºñ:)j¯\n˙Eïö@Öcu*KU<£úG&˘¢¶*Oï\√øf◊≠¸£R0œí\‚\Ë\0bc]m\≈\Ê\œ\ƒ∆ú5M(ÚB&\ÕÄ ®5ÀßYö÷®èkô\∆=±ÄÖπø\∆\…Zë¯õÃªáuô%™G5r°§¨P)ÚãF»î\–JQVï´ˇ+nåÖc8iL∂\–*IaögI\Ÿ$\‰E{f+¸ciY±´ç±\‘q•ç:ê„î≤∫7áñx\\ü_ÚœØ\‡\∆L1pñk/í¥¨ÛK\–ıL\Í#t±8õ/êùò«¥l|w\’Òmç\⁄U\Œ^*$N\0\ÌeBI]¨Ûûã\‹\√\ÃÙzˆtåó+\ﬁzè∑Z{4\∆ÕÅÕñ\’d{à[@\›N∂á∏w\”\nk\ÕEÆ\ÿ\ÿ˙9\'8≠ãdñ6¸∑+ˇ0\’BP\«cª∞>TH\—∫\÷?\Ïx\Ï@\”† 7∞£åÚ\‰*Ñ2¡\no£Æ\◊\Â\›≤\Ã\ﬂ\Ôå◊®´ª\«v\ÂÅså\Ê\Œ,Çõ\–\Ê\ÿn˛,Bx°Éç$˜\\9z`Æ\ÿ\Ïy\‡XYó9¿\ÃÙzˆt\\\Ê\0.\‹vŒûéMå/∑¨&\€C\‹\Ív≤=\Ïõ8≥µQ\„PQ40˘^†ÅÇ∫nG•¸\”\ÿ¨\Œ\Ó?b™\"<ME¥–µÑ\ÃRïo≥ºÄH\ﬁwn\Zi∏YÒWD\¬(ù\ƒÚ\ZK\"√´\¬O±\√\r´·™îq˙ö\‚vø(sâÖLUrá\≈â£∫_$ySØ∞ûW¯Ukô`ï\ÔZΩ0o\ÿEh\ÿhkIüç\’b)IC2nño\nˆ#Q≤E:\€%\n\Ô\⁄\‹!•ò$wÛ\—\›\0»îXê/≥i\”\‰u\€;\»ÿ®πk=G\‡0∞$à\›\Ì\nj†y´f®7\‹a≠o∂¥,≤™Yàoøaè\‚¿•ö_\„Ø”ø\—7c\‹h\Ê\Í\È%x\œ8\ÔLç7\…Y^\◊MW∏ëh∏´\’…æÆˆ\'n:_!çn\◊Ã´/q∑)g|5◊Ω\…˛o¥H\‡∏¡:A<ı!la\ e§6çL0x?Ü∑\Ô£\·≈§ª1E\»-ÿó¶;@/:\≈m\ÏaÙwY°<ã[	˝ãw˝´ÆØaå~`IŒßıù\“\ŸbÅ\Œ*≠<˛˝\0ç$Ò,†\ÔN˙W\«p6¶>n$\◊qE\∆\ƒG£\ÁŸÄ\Á äCf\„\‰\›\‡FìIt6ç&{æÚ˙ °ï°\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220205210737+01\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220205210737+01\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001526 00000 n \n0000001820 00000 n \n0000001908 00000 n \n0000002001 00000 n \n0000000015 00000 n \n0000002101 00000 n \n0000001785 00000 n \n0000002164 00000 n \n0000002218 00000 n \n0000002250 00000 n \n0000002354 00000 n \ntrailer\n<</Info 11 0 R/ID [<645e2932d472263af86ea724c1ff5457><89f39f87f52eae81a83faa0996f7f045>]/Root 10 0 R/Size 12>>\nstartxref\n2564\n%%EOF\n',0,'D√©cembre',0,0,'N¬∞13.21.19.05.14.01','20211231-1005',11270,13524,23,2254,'Acquitt√©',490,2),(11,'Accor Hotels','02/03/2022','04/03/2022','31/01/2022',30,'OK','\\2022\\01-Janvier\\Facture Client\\Facture_Sbatec_Emagine_202201_1000.pdf','Facture_Sbatec_Emagine_202201_1000.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1445>>stream\nxúΩX\Àr\€6\›\Î+Ó¶ìd\∆a\‚\À;YëõxlGëò,\⁄tS∞\Ã\">/˚\r˝B?)À∞_\—Jî,;B4CM\«#@úã˚<ó_z\'Qè{¢ioı\ﬁ˜úô_)¸3ﬂÅ`\Õ{ØN)P\—u\Ô˘ã\Ë≥Ÿª\ŸB û?|( \‡áû\„Û\ÂÉ®g$\Õr>\Î=üúÙ£\·\0YZT∫L“ô9í¿\Ï\«˛˛é\”F¨›Äî29s]#ím\ƒ¿e2ÅB;ö˙\‘ﬁ∂j6xîR8\…*≠ne>ÖKY&Y*ı\‘Gáπ°\Ó<,d.!0ÆT¢_^H=óIë•∏A ,7òÙ\'@V\ÀERJ\rSFäaïg\≈∞Öø∂\÷S\Ï±3p&™--Uû+8Ep¥™\Ëj8JBi\‡Ñ;=\Ât4ÄSùÖBgDº¨∑\» ∏¨r\’\∆\rC\«\Î{âmò\À:\"0éf\„\ƒd#Ù∑é(\‹Z\€cÃí?Üs9KRu∏\"sBÒ\◊]{%\€F0\“2V	|Ãäô*~\œ\\xhº\¬\Œ\√B&0öYï_©\€,Qp.\·u}≠\“B˝¯TÅ)\¬Û¡ı—óvûz:\Ó_Ü]M@PxJ,πÅë#ótUπ\'L ≥Qr/\\˜àêøêrhhI9\—\«>w° \‹2IrUvá\·\‡˘Ãñ>∑+\≈hÖ˘\Ï°Øa¨kE£˘úZ\‡_B4\Ï~K\ÃÛ∫Å°\€0\Á\nÚ\Ô3≠\Ê*-°PπÑºñ:)j¯˙Yïö@Öcu*KU\‹£úG&˘¢¶*Oï\√gøf◊ç¸R)ògIqt\0±±Æ∂bÛ\'bcŒö&Üy!ì\Ê@e‘äö\Â\”,Mk\‘«ïLcâûX¿\¬\‹_\„d≠H¸M\Ê\›√Ä∫\Ã’£\ZπPR\÷®˘E£dJh•(´\ \’\◊\∆\¬1\nú4&[hï§0Õ≥§lÚ¢=≥˛°4Ç¨\ÿ\’FÜX\Í∏\“F\»qJY›ôCK<ÆÄO\œ˘ßpm¶8ÀµgIZ\÷˘ø%\Ëz&ı∫Xú\Õ\»N\ÃcZ6>Üª\Í¯¶F\Ì*g/\'ÄˆÉ2°§.÷çy\œE\Óafz={<\∆\ÀoΩ\«[≠=\„\Ê¿f\Àj≤=\ƒ-†n\'\€C‹ªnÖµ\Ê\"Wll˝îºÆãdñ6¸∑+ˇ0\’BP\«cª∞\ﬁWH\—∫\÷?\Ïx\Ï@\“† 7∞£åÚ\‰*Ñ2¡\no¢Æ\◊\Â\›≤\Ã\ﬂ\Ôå◊®´ª\«v\ÂÅ3å\Ê\Œ,Çõ\–\Ê\ÿn˛,Bx°Éç$˜\\9∫\'!\›\Ïi\‡XYó9¿\ÃÙzˆx\\\Ê\0.\‹v\ŒèMå/∑¨&\€C\‹\Ív≤=\Ïõ8≥µQ\„PQ40˘^†ÅÇ∫nG•\Ï\ﬁ\ﬁ¨\Œ\Ó?c™∏zöäh°k	ô•*\ﬂdyëº\Î\‹4\"\“p≤\‚oàÑ%P.:â\ÂñD§ˇ~\ÈàÜ?iX\rW%î”óîêÆ\Õ^îπ\ƒB&ã*π\≈\‚Å\ƒQ\›-íº©WX\œ+¸™µL∞\ w≠^ò7\Ï\"4l¥Äµ$Ü\œ\∆j±î§!\◊\À7˚ëã(Ÿ¢ùÄ\ÌÖw\÷7~aí\‹\ÕGwS¿ SbAæ»¶Mgê\◊m\Ô c£\ÊÆıÅ\√¿í v∑+®ÅÊ≠ö°\ﬁpãµæ\Ÿ“≤t»™f!æ˘é=äj~ÖøNˇFﬂåq£ô´\'§ó@\‡=\·º3U4\ﬁ$gy]7]\·F¢\r\‡ÆV\'˚∫\⁄W\‹t∂B\Zˇ⁄ÆôW_\‚nS\Œ¯jÆ{ì˝\ﬂhë¿qÉuÇx\ÏC\ÿ¬î\ÀHm\Zô˛`no\ﬁE\√ÛIwcäê[∞/LwÄ^t&\”[\”I\Zı]T(\Œ\‚FBˇ¸mˇ≤\Î[#ÇXrÛ\Î˙V\Èl±¿Ç\nßï\÷ˇyÄ>íx–∑\'˝\Àc8S7ÅÎ∏É2W\0Ò\—\›\ËA2\‡˘\‚êY\ƒ8y;8Ü\—dùéG£…ûoº˛µcî\—\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220205210933+01\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220205210933+01\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001528 00000 n \n0000001822 00000 n \n0000001910 00000 n \n0000002003 00000 n \n0000000015 00000 n \n0000002103 00000 n \n0000001787 00000 n \n0000002166 00000 n \n0000002220 00000 n \n0000002252 00000 n \n0000002356 00000 n \ntrailer\n<</Info 11 0 R/ID [<33f91f61f697ef9eb35d4879a06be6c9><88dcafa7945cdbb485bbfea7d77bb40a>]/Root 10 0 R/Size 12>>\nstartxref\n2566\n%%EOF\n',0,'Janvier',0,0,'N¬∞ 13.21.19.05.14.01','20220131-1000',10455,12546,20.5,2091,'Acquitt√©',510,3),(12,'Accor Hotels','30/03/2022','31/03/2022','29/02/2022',30,'OK','\\2022\\02-F√©vrier\\Facture Client\\Facture_Sbatec_Emagine_202202_1001.pdf','Facture_Sbatec_Emagine_202202_1001.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1443>>stream\nxúΩXMr\€6\ﬁ\Îo”â3\„0\0˛y\'+Rè\Ì(ìEõ.`\nñôB§\¬\◊Àû°\'z§,√û¢î(Y∂Ö®CN«∂\0 æá˜˚=~ÌùÜ=\€ü∏\Œz√∞˜°\«\‡LKÅ\‡è˛Ù9Ép\—{=¢@	Ñ◊Ω£ó\·ΩwªÖ@¥x¯êO¿\\À≥W2†Æ~ê\‘\ÀŸºw4=\Ìá\√\“$/U\'s}$Å˘3\«˛˙é≥Z¨˝Äî2lf1∫A$ªàæ\√ `\0!Ñ∂\‘7Ù®\≈\›]\’lÒ(•pöñJﬁäló¢à\”D®®ès+\ÿ{X¿B`R\ XΩ∫j!\‚<M:∏Å\œ\r7òˆßAîâe\\3	Zäaô•y\ÿ\‹\€X\Î)ˆ\ƒ\ZXS’ñ2\À$¯6Ep¥*ok8J®o{=e4\Í˚`SùÖBkDº¨ª\»ä®(3\Ÿ\∆	\À\Âõ{Ò]òÀø[\"0\ÕfCêπå\–_Z¢\ÿ∑\–\⁄.cÜ¸1\\àyú\»\Ó\'\Ã\n8∏ƒµg\„ïlï\√XâHb$\‰)\Õ\Á2>ûm\ÓZå£9\n{\«h\Z§ev%o\”X¬πÄ7’µLr˘¸©SÑ\ÎÅ\„°/\Ì=u4\È_ÜmM@PxJπÅëcB⁄™\‹\Â:ÂôÅ(πgDÉ˝\ŸA °Å!ÂÑü˙p\“ÑbÄ\ÿêiú…¢=å\rÆ\«L\Ès∑RÉí¿Ç◊ÑΩfÑ±∂ç\Ê\Ÿ\‘\0ˇ\n˙\„a˚[`Æg\—-›Ö9óê}ü+πêIπ\ÃdïPq^¡7P/\ D*D´3Q\»¸˛\Â<\÷\…-0ìY\Àæ`¯’ªn\ƒ\◊R\¬\"çÛ\„\ƒ∆∫⁄àm?s\÷,\÷t \◊\»K\◊\Ë\0ïQ#jö\Õ\“$©PW\"âzbK}Öìç\"Ò;ëµ\Í0CTè+\‰BqQ\Â \‰µ\"00ê)°ï¬¥,\÷ˇ=T\\kG(p\\õl©dú¿,K\„¢N\»\À\Ê\ÃF¯á\“p≤fW[\"°¢Riu \«)Dyß-∏>Ÿü_¬µûb\‡¨\÷^\ƒIQeˇ†™πP\«\ËbQ∫X\";—è)Q˚Ó™¢õ\nµ+≠ÉTH,ö?î	%u∞n,zr=Sõ\Ÿ\„1Z≠∏õ=\Óz\Ì¡\’\÷[÷ì\›!j\0U3\Ÿ¢\ﬁu#¨19|kÎßú\‡Mï\«Û§\Êøm˘áÆúZ.€áı°D¬à>–∂˛a\«c˙òtÄÇ\‹¿å2\Œ\‚;(J+º\r\€\\«∂\Ë!êE™˘~kºZçX\›]∂/úa4∑f∂m\€\ÕˇÅEp7∞∞ë¥]\«Dé\Ó	?\Ïi∞±≤ÆrÄû©\Õ\ÏÒ∏\ 6wö=8{<\÷1æ⁄≤û\ÏQ®ö\…\Óph∞ô©ıká\n√Å\Œ˜≤Éís[\”u3*e˜\Ï?\¬\‡»æm˘?b™∏\⁄ME¥¿1Ñ\ÃJïo\”,áP‹µn\Zi∏YÒ7D\¬(ñKG\‚JsIº*¸\‘;~–∞jÆäø˛+\⁄¡˚\Z,1ê…ºåo±x qîw\À8´\Î\÷Û?*%b¨Úm´\Ê\r≥5\Õa#âÊ≥ë\\Æ$©I\∆ı\ÍM¡a\‰\¬ \nG∂\»\Àg˚D±\€6w\»G)&\…˝|t?\Ï\0ôÚE:´;É¨jzi5∑≠\Á¯Ü±ø]A\r\‘o\’4ıÜ[¨ııñÜ•CZ\÷\—\Õw\ÏQ,∏êã+¸vˆ˙fÑı\\>!Ω|˜	\ÁùÀºˆ&1œ™™\Ó\n∑m!\0w5:9\‘\’˛¿Mgk§\…\œÕö~ıÖ!\Ó\‘\Â\Ã^\œUoz¯-\‚[éøIè}[òb©u#\”\ﬁO\‡\Ì˚px>moL\ÿ\Ï\›†ç™\€LwíZ}%ä≥º\–?◊øl˚Fã\‡˘Ü\‹¸¶∫ï*].±†¬®T\n¡£\ﬂ;\Ë#âk\0}w⁄ø<Å—Ñz∏ëp\\\«î9àá\ÓF\œí\◊\Î àf\„Ù\›\‡\∆\”i8öå\«\”\ﬂx˝ëï=\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220306212328+01\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220306212328+01\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001526 00000 n \n0000001820 00000 n \n0000001908 00000 n \n0000002001 00000 n \n0000000015 00000 n \n0000002101 00000 n \n0000001785 00000 n \n0000002164 00000 n \n0000002218 00000 n \n0000002250 00000 n \n0000002354 00000 n \ntrailer\n<</Info 11 0 R/ID [<0084a2170e04249d17978b73afa6a875><a1f8afafd6184191896a37ae98e5a363>]/Root 10 0 R/Size 12>>\nstartxref\n2564\n%%EOF\n',0,'F√©vrier',0,0,'N¬∞ 13.21.19.05.14.01','20220228-1001',10200,12240,20,2040,'Acquitt√©',510,3),(13,'Accor Hotels','30/04/2022','28/04/2022','31/03/2022',30,'OK','\\2022\\03-Mars\\Facture Client\\Facture_Sbatec_Emagine_202203_1002.pdf','Facture_Sbatec_Emagine_202203_1002.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1441>>stream\nxúΩX\Àr\€6\›\Î+Ó¶ìd\∆a\‚\À;YëõxlGëò,\⁄tS∞\Ã\">/˚\r˝B?)À∞_\—Jî,;B4CM\«#@úã˚<ó_z\'Qè{¢ioı\ﬁ˜úô_)¸3ﬂÅ`\Õ{ØN)P\—u\Ô˘ã\Ë≥Ÿª\ŸB û?|( \‡áû\„Û\ÂÉ®g$\Õr>\Î=üúÙ£\·\0YZT∫L“ô9í¿\Ï\«˛˛é\”F¨›Äî29s]#ím\ƒ¿e2ÅB;ö˙\‘ﬁ∂j6xîR8\…*≠ne>ÖKY&Y*ı\‘Gáπ°\Ó<,d.!0ÆT¢_^H=óIë•∏A ,7òÙ\'@V\ÀERJ\rSFäaïg\≈∞Öø∂\÷S\Ï±3p&™--Uû+8Ep¥™\Ëj8JBi\‡Ñ;=\Ât4ÄSùÖBgDº¨∑\» ∏¨r\’\∆\rC\«\Î{âmò\À:\"0éf\„\ƒd#Ù∑é(\‹Z\€cÃí?Üs9KRu∏\"sBÒ\◊]{%\€F0\“2V	|Ãäô*~\œ\\xhº\¬\Œ\√B&0öYï_©\€,Qp.\·u}≠\“B˝¯TÅ)\¬Û¡ı—óvûz:\Ó_Ü]M@PxJ,πÅ±#B∫™\‹&\ÂŸÅ(Ωgå \ÿ_H94¥§ú\ËcéªÇPnô$π*ª\√p|fKü€ï\‚¥\¬|ˆäWå0\÷5É¢\—|N-/°?\Zvø%\Ê˘\›¿\–mòs˘˜ôVsïñP®\\B^Kù5|˝¨JM†Bå±:ï•*\ÓèQ\Œ#ì|\—Sïßâ\ \·3Ü_≥\ÎF~©Ã≥§8:Ä\ÿXW[±˘±1gMC\nÉºêIsÅ†2jE\ÕÚiñ¶5\Í\„J¶±DO,`a\ÓØq≤V$˛&Û\Óa@]fâ\ÍQç\\()\ÎTä¸¢Q2%¥RîU\ÂÍøá\nÇkc\·N\Zì-¥JRò\ÊYR6	y—û\Ÿ\nˇP\ZAV\Ïj#C,u\\i£\‰8•¨\ÓÃ°%W¿ß\Á¸\”∏6Sú\Â⁄≥$-\Î¸\ﬂt=ì˙],\Œ\Êd\'\Ê1-\√]u|S£vï≥ó\nâ@˚AôPR\Î∆º\Á\"˜03Ωû=\„Âä∑\ﬁ\„≠\÷åqs`≥e5\Ÿ\‚P∑ì\Ì!\Ó]∑\¬Zsë+6∂~\ 	^\◊E2K˛€ïòj!®\„±]X\Ô+$å\Ë]\Îv<v†\ÈPê\ÿQFyrBô`Ö7Q◊Ç\Îrá\ÓYfÜ\Ôw\∆k‘à\’\›cªÚ¿Fsg¡Mhsl7ˇ!º\–¡Fí{Æç\›3!ˆ{ö8V\÷e03Ωû=ó9Ä∑›É≥\«c\„\À-´\…ˆ∑Ä∫ùl˚\Ê\0\Œl≠G\‘8T\rLæWh Ö\‡ÜÆ\€Q)øGéy\0´ÛÄ;¡œò*ÆÇ¶\"Z\ËZBf©\ 7Y^@$\Ô:7çá4\‹á¨¯\"a	îãÖNbyÖ%\—\ƒ¸\“;“∞\ZÆJ8ß/)!]I+^îπ\ƒB&ã*π\≈\‚Å\ƒQ\›-íº©WX\œ+¸™µL∞\ w≠^ò7\Ï\"4l¥Äµ$Ü\œ\∆j±î§!\◊\À7˚ëã(Ÿ¢ùÄ\ÌÖwmÓêèRLíª˘\Ën\nx\0dJ,\»Ÿ¥\ÈÚ∫\Ìdl\‘‹µû#pX\ƒ\Óv5–ºU3\‘n±\÷7[ZñY\’,\ƒ7ﬂ±Gq\‡BÕØ\◊\È\ﬂ\Ëõ1n4sıÑÙº\'úw¶ä∆õ\‰,Ø\Î¶+\‹H¥Å\0\‹\’\Íd_W˚äõ\ŒVH\„_\€5Û\ÍC\‹m\ _\Õuo≤ˇ-8n∞Nè}[òr©M#\”ﬁç\·Õªhx>\ÈnLrˆÖ\È–ã.dæ¥\€EÖ≤,n$Ù\œ\ﬂˆ/ªæÇ1¯~`IÃØ\Î[•≥\≈´)úVZ#x¸\ÁöH\‚Y@ﬂûÙ/è\·tL}\‹HÆ\„\ \\\ƒG_£gôÄ\Á ÇCf\„\‰\Ì\‡FìIt:ç&{æ\Ó˙ùìÜ\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220312213511+01\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220312213511+01\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001524 00000 n \n0000001818 00000 n \n0000001906 00000 n \n0000001999 00000 n \n0000000015 00000 n \n0000002099 00000 n \n0000001783 00000 n \n0000002162 00000 n \n0000002216 00000 n \n0000002248 00000 n \n0000002352 00000 n \ntrailer\n<</Info 11 0 R/ID [<f552632847ae93f4829c1e0f531a65fc><327948d3cf858fd474de7ac9482fb30d>]/Root 10 0 R/Size 12>>\nstartxref\n2562\n%%EOF\n',0,'Mars',0,0,'N¬∞ 13.21.19.05.14.01','20220331-1002',11220,13464,22,2244,'Acquitt√©',510,3),(14,'Accor Hotels','30/05/2022','27/05/2022','30/04/2022',30,'OK','\\2022\\04-Avril\\Facture Client\\Facture_Sbatec_Emagine_202204_1003.pdf','Facture_Sbatec_Emagine_202204_1003.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1440>>stream\nxúΩX\Àr\€6\›\Î+Ó¶ìd\∆a\‚\À;YëõxlGëò,\⁄tS∞\Ã\">\\/˚\r˝B?)À∞_\—Jî,;B‘°¶\„ë\0 \Œ\≈}û\À/Ωì®\«=à—¥7åz\Ô{\ŒÃØ˛ô\Ô@0à\ÊΩWß(Å\Ë∫˜¸EÙ\Ÿ\Ï\›l!\œ>C\œÒ˘ÚA\‘3íf9üıûON˙\—p\0É,-*]&\È\ÃI`ˆùc˝\r\«i#\÷n@J¯ú9åÆ\…6b\‡2ôÑÖ@°\Õ\r}\Ío[5<J)údïV∑2ü¬•,ì,ï˙\Í£\√\‹\–	w2óW*\—//§ûÀ§\»\“\‹ ñL˙ì +à\Â\")•Ü©#≈∞ ≥\‚\0\ÿ\¬_[\Î)ˆ\ÿ8’ññ*\œú\"8ZUt5%!¯4p¬ùûr:\Z¿©ã\ŒB°3\"^\÷\€dèe\\Vπ\Í„Ü°\„âıΩ\ƒ6\Ã\Â\ﬂG≥qb	2è˙KG\Ó\n≠\Ì1f\…√πú%©:\\Ñ9°\0èxéÎÆΩím£\ni+åÑ>f\≈Lﬂèg.<á	4^a\Áa!MÉ¨ Ø\‘mñ(8ó∫æVi°æ™¿\·˘\‡˙\ËK;O=˜/√Æ& (<%ñ\‹¿\»!]U\Ó	ìÚ\Ï@î\‹3b¿˛<@ °°%\ÂD˚p\‹ÑbÄp\»$\…U\ŸÜÉ\Á3[˙‹ÆG†\ZıØa¨kE£˘úZ\‡_B4\Ï~K\ÃÛ∫Å°\€0\Á\nÚo3≠\Ê*-°PπÑºñ:)j¯\n˙Yïö@Öcu*KU\‹£úG&˘¢¶*Oï\√gøf◊ç¸R)ògIqt\0±±Æ∂bÛ\'bcŒö&Üy!ì\Ê@e‘äö\Â\”,Mk\‘«ïLcâûX¿\¬\‹_\„d≠H¸M\Ê\›√Ä∫\Ã’£\ZπPR\÷®˘E£dJh•(´\ \’\◊\∆\¬1\nú4&[hï§0Õ≥§lÚ¢=≥˛°4Ç¨\ÿ\’FÜX\Í∏\“F\»qJY›ôCK<ÆÄO\œ˘ßpm¶8ÀµgIZ\÷˘?%\Ëz&ı∫Xú\Õ\»N\ÃcZ6>Üª\Í¯¶F\Ì*g/\'ÄˆÉ2°§.÷çy\œE\Óafz={<\∆\ÀoΩ\«[≠=\„\Ê¿f\Àj≤=\ƒ-†n\'\€C‹ªnÖµ\Ê\"Wll˝îºÆãdñ6¸∑+ˇ0\’BP\«cª∞\ﬁWH\—∫\÷?\Ïx\Ï@\“† 7∞£åÚ\‰*Ñ2¡\no¢Æ\◊\Â\›≤\Ã\ﬂ\Ôå◊®´ª\«v\ÂÅ3å\Ê\Œ,Çõ\–\Ê\ÿn˛,Bx°Éç$˜\\9∫\'b∞ß9Äce]\Ê\03\”\Î\Ÿ\„qô∏p\€=8{<61æ‹≤ölq®\€\…ˆ∞o\‡\Ã\÷zDçCE\—¿\‰{uÄRn\Ë∫ï≤{ˆaq\‰Ä;¡èò*ÆÇ¶\"Z\ËZBf©\ 7Y^@$\Ô:7çá4\‹á¨¯+\"a	îãÖNbyÖ%\—\–¯©#v˛†a5\\ïN^RBx˜ã2óX\»dQ%∑X<ê8™ªEí7ı\n\ÎyÖ_µñ	V˘Æ\’ÛÜ]ÑÜç∞ñ\ƒ\ŸX-ñí4$\„z˘¶`?raE [°∞]¢Æ\ÕÚQäIr7\›MÄLâ˘\"õ6ùA^∑ΩÉåçöª\÷sKÇ\ÿ›Æ†ö∑jÜz\√-\÷˙fK\À\“!´öÖ¯\Êˆ(\\®˘˛:˝}3∆çfÆûê^Å˜ÑÛ\ŒT\—xìú\Âu\›tÖâ6ÄªZù\Ï\Îj‡¶≥\“¯\ÁvÕº˙\¬wõr\∆Ws›õ\ÏˇFãé¨\ƒc\¬¶\\Fj\”\»ÙÉwcxÛ.\ZûO∫SÑ‹Ç}a∫É\È≥˛mû\ËFw ≤∏ë\–?€ø\Ï˙\n\∆\‡˚Å%1øÆoï\Œ¨¶pZiç\‡Ò\Ôh\"âg}{“ø<Ü\”1ıq#∏é;(s}çûdûÄôEåì∑ÉcM&\—\Èx4ö\Ï˘∫\Î_%ì}\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220501011247+02\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220501011247+02\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001523 00000 n \n0000001817 00000 n \n0000001905 00000 n \n0000001998 00000 n \n0000000015 00000 n \n0000002098 00000 n \n0000001782 00000 n \n0000002161 00000 n \n0000002215 00000 n \n0000002247 00000 n \n0000002351 00000 n \ntrailer\n<</Info 11 0 R/ID [<a442087e4fd8c4c0f75386dd4a2b8324><f0171a67b3aebe43ea2a3e742d403ae8>]/Root 10 0 R/Size 12>>\nstartxref\n2561\n%%EOF\n',0,'Avril',0,0,'N¬∞ 13.21.19.05.14.01','20220430-1003',10200,12240,20,2040,'Acquitt√©',510,3),(15,'Accor Hotels','30/06/2022','30/06/2022','31/05/2022',30,'OK','\\2022\\05-Mai\\Facture Client\\Facture_Sbatec_Emagine_202205_1004.pdf','Facture_Sbatec_Emagine_202205_1004.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1440>>stream\nxúΩX\Àr\€6\›\Î+Ó¶ìd\∆a\‚\À;YëõxlGëò,\⁄tS∞\Ã\">\\/˚\r˝B?)À∞_\—Jî,;B‘°¶\„ë\0 \Œ\≈}û\À/Ωì®\«=à—¥7åz\Ô{\ŒÃØ˛ô\Ô@0à\ÊΩWß(Å\Ë∫˜¸EÙ\Ÿ\Ï\›l!\œ>C\œÒ˘ÚA\‘3íf9üıûON˙\—p\0É,-*]&\È\ÃI`ˆùc˝\r\«i#\÷n@J¯ú9åÆ\…6b\‡2ôÑÖ@°\Õ\r}\Ío[5<J)údïV∑2ü¬•,ì,ï˙\Í£\√\‹\–	w2óW*\—//§ûÀ§\»\“\‹ ñL˙ì +à\Â\")•Ü©#≈∞ ≥\‚\0\ÿ\¬_[\Î)ˆ\ÿ8’ññ*\œú\"8ZUt5%!¯4p¬ùûr:\Z¿©ã\ŒB°3\"^\÷\€dèe\\Vπ\Í„Ü°\„âıΩ\ƒ6\Ã\Â\ﬂG≥qb	2è˙KG\Ó\n≠\Ì1f\…√πú%©:\\Ñ9°\0èxéÎÆΩím£\ni+åÑ>f\≈Lﬂèg.<á	4^a\Áa!MÉ¨ Ø\‘mñ(8ó∫æVi°æ™¿\·˘\‡˙\ËK;O=˜/√Æ& (<%ñ\‹¿\»!]U\Ó	ìÚ\Ï@î\‹3b¿˛<@ °°%\ÂD˚p\‹ÑbÄp\»$\…U\ŸÜÉ\Á3[˙‹ÆG†\Ê≥W\ƒ}\≈c]3(\Z\Õ\Á\‘ˇ˙£a˜[`û\Ô\–\r›Ü9Wêõi5Wi	Ö\ %\‰µ\‘IQ\√W\–œ™\‘*\ƒ´SY™\‚˛\Â<2\…-0Uyö®>c¯5ªn\‰óJ¡<Kä£àçuµõ?s\÷41t†0\»ô48\0*£V\‘,üfiZ£>Æd\ZKÙ\ƒ\Ê˛\Z\'kE\‚o2\Ô\‘eñ®\’»Öí≤.@•\»/\ZE`` SB+EYUÆ˛{® ∏6éQ\‡§1\ŸB´$Öiû%eìêÌô≠•d≈Æ62\ƒR«ï6\Í@éS\ \Í\ŒZ\‚q|z\Œ?ΩÄk3\≈¿YÆ=K“≤\Œˇ)A\◊3©è\–\≈\‚læ@vb”≤Ò1\‹U\«75jW9{©ê8¥î	%u±n\Ã{.r3\”\Î\Ÿ\„1^Æx\Î=\ﬁj\Ì¡76[Vì\Ì!nu;\Ÿ\‚\ﬁu+¨5πbcÎßú\‡u]$≥¥\·ø]˘á©Ç:€ÖıæB¬à>–µ˛a\«c˙ê\0πÅeî\'wP!î	Vxu-∏.w\Ë>êef¯~gºFçX\›=∂+úa4wf‹Ñ6\«vÛ`\¬l$π\Á\⁄\»\—=˚É=\Õ+\Î2òô^\œè\À¿Ö\€\Ó¡\Ÿ„±âÒ\Âñ\’d{à[@\›N∂á}s\0g∂\÷#j*ä&ﬂ´4êBpC\◊\Ì®î›≥ˇ`ã#\‹	~\ƒTqı4\—B\◊2KUæ\…Ú\"y◊πiD8§\·8d\≈_	K†\\,t\À+,âÜ˛\√O±\√\r´\·™\ƒ\ÂÙ%%Dtø(sâÖLUrã\≈â£∫[$ySØ∞ûW¯Ukô`ï\ÔZΩ0o\ÿEh\ÿhkIüç\’b)IC2Æóo\nˆ#Q≤E:\€%\n\Ô\⁄\‹!•ò$wÛ\—\›\0»îXê/≤i\”\‰u\€;\»ÿ®πk=G\‡0∞$à\›\Ì\nj†y´f®7\‹b≠o∂¥,≤™Yàoæaè\‚¿Öö_\·Ø”ø\–7c\‹h\Ê\Í	\È%xO8\ÔLç7\…Y^\◊MW∏ëh∏´\’…æÆˆn:[!çn\◊Ã´/q∑)g|5◊Ω\…˛o¥H\‡∏¡:A<ˆ!la\ e§6çL0x7Ü7\Ô¢\·˘§ª1E\»-\ÿ¶;@/∫¿\‹`TwQ°(ã	˝Û∑˝ÀÆo`ºXÚÚ\Î˙V\Èl±¿b\nßï\÷ˇ~Äíx–∑\'˝\Àc8S7ÅÎ∏É2W\0Ò\—\’\ËA\"\‡˘\‡êY\ƒ8y;8Ü\—dùéG£…ûoª˛?˜í˘\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220531204250+02\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220531204250+02\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001523 00000 n \n0000001817 00000 n \n0000001905 00000 n \n0000001998 00000 n \n0000000015 00000 n \n0000002098 00000 n \n0000001782 00000 n \n0000002161 00000 n \n0000002215 00000 n \n0000002247 00000 n \n0000002351 00000 n \ntrailer\n<</Info 11 0 R/ID [<4fde182b53a811394e5d49966b85323d><c6b78357e774d7125dd8e107c366ccae>]/Root 10 0 R/Size 12>>\nstartxref\n2561\n%%EOF\n',0,'Mai',0,0,'N¬∞ 13.21.19.05.14.01','20220531-1004',10200,12240,20,2040,'Acquitt√©',510,3),(17,'Accor Hotels','30/07/2022','28/07/2022','30/06/2022',30,'OK','\\2022\\06-Juin\\Facture Client\\Facture_Sbatec_Emagine_202206_1005.pdf','Facture_Sbatec_Emagine_202206_1005.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1441>>stream\nxúΩX\Àr\€6\›\Î+Ó¶ìd\∆a\‚\À;YëõxlGëò,\⁄tS∞\Ã\">/˚\r˝B?)À∞_\—Jî,;B4CM\«#@úã˚<ó_z\'Qè{¢ioı\ﬁ˜úô_)¸3ﬂÅ`\Õ{ØN)P\—u\Ô˘ã\Ë≥Ÿª\ŸB û?|( \‡áû\„Û\ÂÉ®g$\Õr>\Î=üúÙ£\·\0YZT∫L“ô9í¿\Ï\«˛˛é\”F¨›Äî29s]#ím\ƒ¿e2ÅB;ö˙\‘ﬁ∂j6xîR8\…*≠ne>ÖKY&Y*ı\‘Gáπ°\Ó<,d.!0ÆT¢_^H=óIë•∏A ,7òÙ\'@V\ÀERJ\rSFäaïg\≈∞Öø∂\÷S\Ï±3p&™--Uû+8Ep¥™\Ëj8JBi\‡Ñ;=\Ât4ÄSùÖBgDº¨∑\» ∏¨r\’\∆\rC\«\Î{âmò\À:\"0éf\„\ƒd#Ù∑é(\‹Z\€cÃí?Üs9KRu∏\"sBÒ\◊]{%\€F0\“2V	|Ãäô*~\œ\\xhº\¬\Œ\√B&0öYï_©\€,Qp.\·u}≠\“B˝¯TÅ)\¬Û¡ı—óvûz:\Ó_Ü]M@PxJ,πÅ\—#B∫™\‹&\ÂŸÅ(π˜)A∞øêrhhI9\—\«>w° \‹2IrUvá\·\‡˘Ãñ>∑+\≈hÖF}EºWå0\÷5É¢\—|N-/°?\Zvø%\Ê˘\›¿\–mòs˘˜ôVsïñP®\\B^Kù5|˝¨JM†Bå±:ï•*\ÓèQ\Œ#ì|\—Sïßâ\ \·3Ü_≥\ÎF~©Ã≥§8:Ä\ÿXW[±˘±1gMC\nÉºêIsÅ†2jE\ÕÚiñ¶5\Í\„J¶±DO,`a\ÓØq≤V$˛&Û\Óa@]fâ\ÍQç\\()\ÎTä¸¢Q2%¥RîU\ÂÍøá\nÇkc\·N\Zì-¥JRò\ÊYR6	y—û\Ÿ\nˇP\ZAV\Ïj#C,u\\i£\‰8•¨\ÓÃ°%W¿ß\Á¸\”∏6Sú\Â⁄≥$-\Î¸\ﬂt=ì˙],\Œ\Êd\'\Ê1-\√]u|S£vï≥ó\nâ@˚AôPR\Î∆º\Á\"˜03Ωû=\„Âä∑\ﬁ\„≠\÷åqs`≥e5\Ÿ\‚P∑ì\Ì!\Ó]∑\¬Zsë+6∂~\ 	^\◊E2K˛€ïòj!®\„±]X\Ô+$å\Ë]\Îv<v†\ÈPê\ÿQFyrBô`Ö7Q◊Ç\Îrá\ÓYfÜ\Ôw\∆k‘à\’\›cªÚ¿Fsg¡Mhsl7ˇ!º\–¡Fí{Æç\›S¡ˆ{ö8V\÷e03Ωû=ó9Ä∑›É≥\«c\„\À-´\…ˆ∑Ä∫ùl˚\Ê\0\Œl≠G\‘8T\rLæWh Ö\‡ÜÆ\€Q)ª«Æˇ\0V\ÁwÇü1U˜04\—B\◊2KUæ\…Ú\"y◊πiD8§\·8d\≈\ﬂ	K†\\,t\À+,âØ\nøt\ƒ√ü4¨Ü´èìóî∑˚EôK,d≤®í[,H\’\›\"…õzÖıº¬ØZ\À´|\◊\ÍÖy\√.B\√FXKb¯l¨KI\ZíqΩ|S∞π∞à\"ê-ä\–	\ÿ.Qx\◊\Ê˘(\≈$πõèÓ¶Ä@¶ƒÇ|ëMõ\Œ Ø\€\ﬁA\∆F\Õ]\Î9áÅ%A\ÏnWP\Õ[5CΩ\·k}≥•e\ÈêU\ÕB|Û{.\‘¸\nù˛çæ\„F3WOH/Å¿{\¬yg™hºI\ŒÚ∫n∫¬çD¿]≠NˆuµØ∏\ÈlÖ4˛µ]3Øæ0\ƒ›¶úÒ\’\\˜&˚ø\—\"Å\„\ÎÒÿá∞Ö)óë\⁄42˝¡\‡\›ﬁºãÜ\Áì\Ó\∆!∑`_ò\Ó\0Ω\Ë¨\¬.\ƒ\Ë\Ó¢BY7˙\Áo˚ó]_¡|?∞$\Ê\◊ı≠\“\ŸbÅ\’N+≠<˛Û\0M$Ò,†oO˙ó\«p:¶>n$\◊qeÆ\0‚£Ø\—3ÇL¿Û¡!≥àqÚvp£\…$:èFì=_w˝qcìí\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220630215855+02\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220630215855+02\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001524 00000 n \n0000001818 00000 n \n0000001906 00000 n \n0000001999 00000 n \n0000000015 00000 n \n0000002099 00000 n \n0000001783 00000 n \n0000002162 00000 n \n0000002216 00000 n \n0000002248 00000 n \n0000002352 00000 n \ntrailer\n<</Info 11 0 R/ID [<e3e67e89b2b3d771da1beaa9e506b48f><f2526488520883a1f159d0127779db6b>]/Root 10 0 R/Size 12>>\nstartxref\n2562\n%%EOF\n',0,'Juin',0,0,'N¬∞ 13.21.19.05.14.01','20220630-1005',10710,12852,21,2142,'Acquitt√©',510,3),(18,'Accor Hotels','30/08/2022','31/08/2022','31/07/2022',30,'OK','\\2022\\07-Juillet\\Facture Client\\Facture_Sbatec_Emagine_202207_1006.pdf','Facture_Sbatec_Emagine_202207_1006.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1441>>stream\nxúµXMr\€6\ﬁ\Îo\”I2\„\–\0Ç§w≤\"7Òÿé\"1Y¥\È¶`ô)D*¸qΩ\ÏzÇ°G\ 2\Ï)˙@âíe[®f®éG,Ä¯\ﬁ\Ô˜¯µw\Zı\\M{√®˜°\«\‡\‹¸JÅ\‡ü˘8Éh\ﬁ;>£@	D7ΩóØ¢/f\ÔfÅx˛°ÄÄ\n\«wó2†\¬<Hö\Â|\÷{99\ÌG\√≤¥®tô§3s$Å\Ÿ3\«˛˙é\”F¨›Äî2]\Ê0∫F$€àÅ\« d>!Ñv47Ù©\√≈∂j6xîR8\Õ*≠\Ód>Ö+Y&Y*ı\‘Gáy°\Ó<,d!0ÆT¢__J=óIë•∏A¿-7òÙ\'AV\ÀERJ\rSFäaïg\≈∞πø∂\÷S\Ï±3p&™--Uû+\\ä\‡hU\ﬁ\’pîÑ\‡\”¿	wz\ \ŸhÄK=t\nùÒ≤bê=îqY\Â™;åÜé\‡\Î{Òmò´ø;\"0\Õ\ÊKê	F\Ë/Q\\è;hm¡ò%\Árñ§\Íp	Ñ\ÊÑéÁ≠Ωím£ri+åÑ>e\≈L\œ«≥ÀÖ\√8öØ∞Û∞êqå¶AV\Â\◊\Í.K\\HxSﬂ®¥Pœü\ 1E<}i\Á©g\„˛\’`\ÿ\’Ößƒí¸#B∫j\‹\'\Êd;é˚\ÕÛ	b˝yÄÑCCK¬â>ı\·§+\≈p- ì$WewÑ\œl\…sªNÅVòÕéâ\Ãc]Û\'V)ﬂ•¯\◊\–\rªﬂí\0æC70t\ÊBA˛c¶\’\\•%*óê\◊R\'E\r\ﬂAø®R¶c§Ne©äo\'(\ÁëIΩhÅ©\ \”D\ÂÉØ\Ÿu+øV\n\ÊYR@l¨™≠\ÿ\Ó±1cMC\nÉºêIsÅ†2jE\ÕÚiñ¶5\Í\„Z¶±DO,`a\ÓØq≤V$˛&Û\Óa@=f	\ÍQçL()\ÎTä\Ï¢QÚ$¥RîU\ÂÍøá\nÇc\·N\Zì-¥JRò\ÊYR6\Èx—û\Ÿ\nˇP\ZNV\‹j#C,u\\i£d8•¨\ÓÕ°%W¿\Áó\Ó\ÁWpc¶8ÀµIZ\÷˘?%\Ëz&ı∫Xú\Õ\»M\ÃcZ6>Üª\Í¯∂F\Ì*g/\'ÄˆÉ2°§Vçy\œC\Êafz={<\∆\À±\ﬁ#Vk∆∏9∞Ÿ≤ölq®\€\…ˆ˜nZa≠π\»\„[?eo\Í\"ô•\r˚\Ì\ >–Ñßé`ª∞>TH\—∫V?\Ïw\Ï@\”† 3∞£åÚ\‰*Ñ2¡\no£Æı\÷s∫dô∂\ﬂØQ#w¡v\ÂÅsåÊÆó°!.6õˇ?â\‡æ\Ô\\\·Ÿò\Â{C=M\0.ñ\’e03Ωû=ó	¿\Â^ªgè\«&¿ó[Vì\Ì!nu;\ŸˆM\0.≥uQ\„MQ40\…^†w\‰5çÅï\√*|\0ìªÅkLn\'©∏zääh°g	ó•&\ﬂfyëº\Ô\‹.\"ú\‡68d\ƒ\ﬂ	Àü\\,t\Àk,áØ\n?u\ƒ\√ˇhU\rO%\»2_SBD˜ã2èXàdQ%wX8ê4™˚Eí7µ\nkyÖ_µñ	V¯Æï3î]ÑÜâ∞ñ\ƒp\ŸX-ñí4\„f˘é`?baÖ#S\‰°∞]¢∏]˚:\‰¢3\‰n.∫õ˛\0ôÚe6m∫Çºn˚5w≠\Âñ±ªUA\r4\Ô\”\ÌÜ;¨ÛÕññ°CV5Ò\Ì\ÏO∏TÛk¸u˙˙få\Õ\\=!ºÒÑ\Ô\ŒT\—xìú\Âu\›tÑâ6ÄªZù\Ï\Îj\‡¶Û\“¯\ÁvÕºÙ\¬˜öj\ÊÆ\Ê∫7\Ÿˇ]	/X\'à\«>Ñ\ÌKπå‘¶â\È\Ô\«ˆ}4ºòt7&]ˆ•\È–ãŒ´DkµT\ﬂeÖ\‚,n%Ù/\ﬁıØ∫æ1\"¯Å%7ø©\Ôî\Œ¨ßpViç\‡Ò\Ô\Ë!â∞Äæ;\Ì_ù¿Ÿò˙∏ëp\\\«îyàè\ÓF\œ	Ve\· àCf\„Ù\›\‡FìIt6ç&{æ\Î˙ÙìR\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220716234511+02\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220716234511+02\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001524 00000 n \n0000001818 00000 n \n0000001906 00000 n \n0000001999 00000 n \n0000000015 00000 n \n0000002099 00000 n \n0000001783 00000 n \n0000002162 00000 n \n0000002216 00000 n \n0000002248 00000 n \n0000002352 00000 n \ntrailer\n<</Info 11 0 R/ID [<a4929751516723cdc682ffa7071a4c92><4f4b0e08efb0a2087e9d707abab56742>]/Root 10 0 R/Size 12>>\nstartxref\n2562\n%%EOF\n',0,'Juillet',0,0,'N¬∞ 13.21.19.05.14.01','20220731-1006',3570,4284,7,714,'Acquitt√©',510,3),(19,'Accor Hotels','30/09/2022','13/10/2022','31/08/2022',30,'OK','\\2022\\08-Ao√ªt\\Facture Client\\Facture_Sbatec_Emagine_202208_1007.pdf','Facture_Sbatec_Emagine_202208_1007.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1442>>stream\nxúµXMr\€6\ﬁ\Îo\”I2c3\0HÇ§w≤\"7Òÿé\"1Y¥\È¶`ô)D*¸qº\ÏzÇ°G\ 2\Ï∫\È%Jñ°öÅ:	∞\0\‚{xø\ﬂ\„\Á\ﬁi\‹s9ÑÑC<\Ì\r\„ﬁªÉs˝+Ç˙;Ù\ƒÛ\ﬁ\À3\nî@|\”{˛\"˛§˜n∂H\Ê\n	ww˘ \ ıÉ§].fΩ\Áì\”~<¿ \œ\ ZUi6\”Gò˝\‡\ÿ_\√q⁄äµêRÅ\ÀF◊àd1ÙD,\0\¬\" ÑPK@}√Ä:\ﬂV\ÕèR\nßy≠\‰ù(¶p%™4œÑzÄ˙\Ë0?r¢ùáE\Ã\'∆µL\’Ò•PsëñyvÄÑû\·ì˛\‰=à\Z±H+°`*AK1¨ãº<\0∂¨≠ı{\ÏúâÉj\À*YBó\"8Z’≥5%4t¢ùûr6\ZÜ\‡RùÖÇ5\"^ño≤«Ä\"©\ÍB\⁄\√¯Q\‰po}/o\Ê\Í/K\Ê¢\Ÿ\\b2\Œ˝\≈\≈ı=≠\Õ3\‰è\·\\\Ã\“L.ÅxÑ9ëúp\«˜\◊^…∂Q=)ëHåÑ>\‰\ÂLñ?ég\◊\„Û\–xÖùáE\Ã\√h\Z\‰uq-\ÔÚT¬ÖÄWÕç\ÃJ˘\„S=L<\0?@_\⁄y\ÍŸ∏5⁄öÄ†îrÉ{Dà≠\∆\‚`N6\„–ØæK\Îè$\ZN¸°\'∂ \√\√5ÄL\“BVˆ0.Äôí\Ávù8%1õΩ$\·KF≥ÕüX•ó\Z\‡è°?\Z\⁄ﬂí\0\„ÅC70t\ÊBBÒ}¶\‰\\fî≤P4B•e\ﬂ@=´3¶ê`§NE%ÀØ\'(\ÁëNΩhÅ©,≤T	ÉØ\›u+>\◊\ÊyZ@l¨™ù\ÿ\Ó±1cMSMJçºi{Å†2jDÕãiûe\r\Í\„Zdâ@O,a°\ÔØp≤V$˛&\n˚0†>3ı®A&îVM	2Cv\—*yZ)\Œ\Îjı\ﬂC¡ç∂pÇß≠\…J¶Lã<≠\⁄tº\Ë\Œ\ÏÑ(çGV\‹j#C\"TR+≠d8ï®\Ôı°W\¬\«\Á\Ó\«p£ß8ÀµgiV5\≈\ﬂ®f&\‘∫Xí\œ\»MÙcJ¥>Üªö\‰∂A\ÌJg/\'Ñ\ÓÉ2°§>Vçy\œG\Ê°gj={<&\Àæ\ﬁ\√Wk∆§=∞›≤ölI®∫\…ˆêÙn:açπ\»˜6∂~\ ^5e:\ÀZˆk\À>–ÑæG\ŒvaΩ´ë.¢\ÿV?\Ïw\Ã@\Ô≥† 30£åäÙjÑ\“¡\nØc\€z\Îª\›≤\ 5€∑\∆k’à≈ù≥]y\‡£\ŸˆR<\“$\¬\≈fÛˇ\'^8!ó˚&fD¯\ﬁPOÄãeuô\0ÙL≠gè\«ep=ø€É≥\«c\‡\À-´\…ˆêtÄ™õl˚&\0óô∫é∏ı¶8\Ëd/\–;z>’çÅï~\r\›˝\Ì`p\‚\–\’&7ìT\\=EE¥\»7Ñ\ÀRìØÛ¢ÑX\‹[∑ã\«=2\‚oàÑ\ÂO,*M\ƒ5ñCÜWÖü,±£\Ë?ZU\ÕS	ˆ¸«îê¿˛¢\Ã\'\"Y\÷\È$çÚ~ëm≠\¬Z^\„W£Däﬁ∂raÜ2ã\–2\—÷íh.õ\»\≈Ríñ`\‹,\ﬂ\ÏG,¢x\»Ω\»	\Ÿ.Q\\€æπ(\≈πõã\Ó¶@¶ƒÄ|ôO€Æ†h∫æA$ZÕ∂µÅ£–ê v∑*®Åˆ}ö¶\›páuæ\›\“1t\»\Îv!π˝é˝âór~çøNˇD\ﬂLp£û\À\'Ñó@»ü›ô,[o≥¢i⁄ép#\—pWßì}]\Ìn:_!ç\Ó\÷ÙK/qø≠f\ÓjÆzì˝\ﬂeë\–Ò\√uÇx\ÏCÿæT\ÀHmõò˛`vØ\ﬂ\∆√ãâΩ1Ω\»5`_\Í\Œ`˙¨üˇ≥T\›eç¢,nÙ/\ﬁÙØlﬂΩh¯ 4\‰\ÂWÕùT˘bÅµ\Œj•<˘˝\0˝#\·\–7ß˝´8\”\07\◊qeæ$@W£\Á+2∑N\’¯1Éßo\'0öL\‚≥Òh4\ŸÛ=◊øebí*\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220903111404+02\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220903111404+02\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001525 00000 n \n0000001819 00000 n \n0000001907 00000 n \n0000002000 00000 n \n0000000015 00000 n \n0000002100 00000 n \n0000001784 00000 n \n0000002163 00000 n \n0000002217 00000 n \n0000002249 00000 n \n0000002353 00000 n \ntrailer\n<</Info 11 0 R/ID [<a2e6b1e0fd2856d4b02995b3c57bddbd><f29864180b5ebf57b60e025014550cee>]/Root 10 0 R/Size 12>>\nstartxref\n2563\n%%EOF\n',0,'Ao√ªt',0,0,'N¬∞ 13.21.19.05.14.01','20220831-1007',1530,1836,3,306,'Acquitt√©',510,3),(20,'Accor Hotels','30/10/2022','29/10/2022','30/09/2022',30,'OK','\\2022\\09-Septembre\\Facture Client\\Facture_Sbatec_Emagine_202209_1008.pdf','Facture_Sbatec_Emagine_202209_1008.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1442>>stream\nxúΩX\Àr\€6\›\Î+Ó¶ìd\∆a\‚\À;YëõxlGëò,\⁄tS∞\Ã\">/˚\r˝B?)À∞_\—Jî,;B4CM\«#@úã˚<ó_z\'Qè{¢ioı\ﬁ˜úô_)¸3ﬂÅ`\Õ{ØN)P\—u\Ô˘ã\Ë≥Ÿª\ŸB û?|( \‡áû\„Û\ÂÉ®g$\Õr>\Î=üúÙ£\·\0YZT∫L“ô9í¿\Ï\«˛˛é\”F¨›Äî29s]#ím\ƒ¿e2ÅB;ö˙\‘ﬁ∂j6xîR8\…*≠ne>ÖKY&Y*ı\‘Gáπ°\Ó<,d.!0ÆT¢_^H=óIë•∏A ,7òÙ\'@V\ÀERJ\rSFäaïg\≈∞Öø∂\÷S\Ï±3p&™--Uû+8Ep¥™\Ëj8JBi\‡Ñ;=\Ât4ÄSùÖBgDº¨∑\» ∏¨r\’\∆\rC\«\Î{âmò\À:\"0éf\„\ƒd#Ù∑é(\‹Z\€cÃí?Üs9KRu∏\"sBÒ\◊]{%\€F0\“2V	|Ãäô*~\œ\\xhº\¬\Œ\√B&0öYï_©\€,Qp.\·u}≠\“B˝¯TÅ)\¬Û¡ı—óvûz:\Ó_Ü]M@PxJ,πÅ±#B∫™\‹&\ÂŸÅ(Ωgå \ÿ_H94¥§ú\ËcéªÇPnô$π*ª\√p|fKü€ï\‚¥B£æ\"\·+F\ÎöA\—h>ß¯ó\–\rªﬂí\0Û|án`\Ë6ÃπÇ¸˚L´πJK(T.!Ø•Nä\ZæÅ~V•&P!\∆Xù\ R˜\«(\ÁëIæhÅ©\ \”D\Â√Ø\Ÿu#øT\n\ÊYR@l¨´≠\ÿ¸âÿò≥¶â°ÖA^»§π¿Pµ¢f˘4K\”\Zıq%\”X¢\'∞0˜\◊8Y+ìy˜0†.≥Dı®F.îîu*E~\—(ôZ) ™rı\ﬂC¡µ±på\'ç\…Z%)LÛ,)õÑºh\œlÖ(ç +vµë!ñ:Æ¥QrúRVw\Ê\–è+\‡\”s˛\È\\õ)\Œr\ÌYíñu˛o	∫ûI}Ñ.gÛ≤Ûòñçè\·Æ:æ©Qª\ \ŸKÖ\ƒ	†˝†L(©ãuc\ﬁsë{òô^\œèÒr\≈[\ÔÒVk∆∏9∞Ÿ≤ölq®\€\…ˆ˜Æ[a≠π\»[?\ÂØ\Î\"ô•\rˇ\Ì\ ?Lµ\‘Ò\ÿ.¨˜FÙÅÆı;;–áÙ\0(\»\r\Ï(£<πÉ\n°L∞¬õ®k¡uπC˜Å,3\√˜;\„5j\ƒ\Í\Ó±]y\‡£π3ã\‡&¥9∂õˇã^\Ë`#\…=\◊Fé\Óô{É=\Õ+\Î2òô^\œè\À¿Ö\€\Ó¡\Ÿ„±âÒ\Âñ\’d{à[@\›N∂á}s\0g∂\÷#j*ä&ﬂ´4êBpC\◊\Ì®î\ﬂ#\«<Ä\’y¿ù\‡gLWAS-t-!≥T\Âõ,/ íwùõFÑC\ZnÅCV¸\rë∞\ \≈B\'±º¬íhb	~\ÈàÜ?iX\rW%!\'/)!A˜ã2óX\»dQ%∑X<ê8™ªEí7ı\n\ÎyÖ_µñ	V˘Æ\’ÛÜ]ÑÜç∞ñ\ƒ\ŸX-ñí4$\„z˘¶`?raE [°∞]¢Æ\ÕÚQäIr7\›MÄLâ˘\"õ6ùA^∑ΩÉåçöª\÷sKÇ\ÿ›Æ†ö∑jÜz\√-\÷˙fK\À\“!´öÖ¯\Ê;ˆ(\\®˘˛:˝}3∆çfÆûê^Å˜ÑÛ\ŒT\—xìú\Âu\›tÖâ6ÄªZù\Ï\Îj_q\”\Ÿ\ni¸kªf^}aàªM9„´π\ÓMˆ£E\«\r\÷	\‚±aS.#µid˙É¡ª1ºy\r\œ\'›ç)Bn¡æ0\›z\—µ¥4àQ\‡EÖ-n$Ù\œ\ﬂˆ/ªæá1B¯Å%;øÆoï\Œ,©pZiç\‡Òü\Ë$âg}{“ø<Ü\”1ıq#∏é;(séû§ûÄ0ôEåì∑ÉcM&\—\Èx4ö\Ï˘\Œ\Î?,¡ï™\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20220929190642+02\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20220929190642+02\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001525 00000 n \n0000001819 00000 n \n0000001907 00000 n \n0000002000 00000 n \n0000000015 00000 n \n0000002100 00000 n \n0000001784 00000 n \n0000002163 00000 n \n0000002217 00000 n \n0000002249 00000 n \n0000002353 00000 n \ntrailer\n<</Info 11 0 R/ID [<0499e7857cc7a3c286ddfe8bb0342537><960399c691cf64c4b935a7c9095ba26d>]/Root 10 0 R/Size 12>>\nstartxref\n2563\n%%EOF\n',0,'Septembre',0,0,'N¬∞ 13.21.19.05.14.01','20220930-1008',11220,13464,22,2244,'Acquitt√©',510,3),(21,'Ekino','30/12/2022',NULL,'30/11/2022',30,'KO','\\2022\\11-Novembre\\Facture Client\\Facture_Sbatec_Easy_202211_1000.pdf','Facture_Sbatec_Easy_202211_1000.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1418>>stream\nxúµXMr\€6\ﬁ\Îo\”I2\„0\0˛y\';vc\'Vâ\È¢M0\ÀL!R\·è\Î\ÓzÜû GËë≤{ä>P¢d\…¢\‘ÒHÄ\ﬂ\√˚˝?ˆN\‚û\ÎCH|à\'Ω≥∏˜∂\«\‡RˇJÅ\‡ü˛9Éx\÷{qNÅàozOü\≈Ù\ﬁı\…\Ï\·C!Å Úù¿]<»Ä˙˙A\“.\”\ﬁ\”ÒI?>;Ö\”<+kU•\ŸTI`˙ïc˝\r\«I+\÷n@J.s]!íM\ƒ\–c±\0ãÄB-ı\r\ÍpS5k<J)ú‰µíw¢ò¿@Tiû	ı\0u\Î0/r¢ùáE\Ã#FµL\’Û+°f\"-Û\Ï\07π\·\„˛¯à\Z1O+°`\"AKqVyy\0l¨¨ı{\‰ú:c’ñU≤($Ñ.Ep¥*∑5%4t¢ùûr>\ZÜ\‡RùÖÇ5\"^\÷\ﬂ\0d€Ä\"©\ÍB\⁄\√xQ\‰¯|u/æ	3¯\«Åπh6óÇ\ÃgÑ˛bâ\¬)uh>cÜ¸q&\ ?a(ä*ìÖ-\Ã	>Ò\œ[˘#€äfäZB,\“\Í:Ø´Øá1\Á‹â¥JæÛ§\0c(“¢ß\ÂéS0¯xJÄ.≥ÛîÛQpzfysóp(1%1~Dà≠~\‚`\Ó5ü0\'#\÷_H,42$ñ¯\Á>€ÇP\◊\02NY\Ÿ√∏\‡Ãî$7\Î¡(â6}A\ÈF≥ÕìXçó\Z\‡üCxfK\Ã∫Ü°õ0o$_¶J\ŒdVA)E#TZ6‘ì:ì∫0%≤Ñâ®d˘\È\Â<\“)-0ëEñ\ >\‰u\—\Ó∫1ägyZ@l¨ûù\ÿ\Ó#±ë\⁄LR]ÙKç<i{Å†2jDÕãIûe\r\Í\„Zdâ@O,aÆ\ÔØp≤R$˛&l\'Üıò!®á\r2û¥jJê≤àV»á\–J1¶\—\Â7\⁄\¬	\nú∂&õ+ôf0)ÚTõ\≈\Ó\Œ\ÏÑ(\r\'Kµñ!*©ïV2ôJ\‘˜˙\–\nè+\·˝S˜˝3∏\—Sú\≈⁄ì4´ö\‚\ﬂ\nT3\Í],\…gs\‰ ˙1%Z\√]Mr€†v•≥ó\nâB˜AôPRèqòı<¨*z¶V≥\Ì1Y¨¯´=˛r\Ì¡ò¥∂[ñì\Õ!\È\0U7\Ÿí\ﬁM\'¨1y|m\Î«ïˇeS¶”¨eπ∂,M\Ëq\Í¯l\÷\€\Zi!˙Äm˘√æ\∆Ù.;\0\nR3 ∞H\Ô°F(¨*∂≠∑û\Î\–} ´\\≥zkºVçX\‹}∂+\\b4\€^ è4âp±©¸ˇID\Ê{&jÙâ\ÿ\„\‡ba]§\0=S´\Ÿˆ∏H.˜∫=8\€\€_lYN6á§T\›ds\ÿ7∏\Ã\‘_ƒ≠?\≈Ò©N˜Ú\0]\"˜®nÃ®\·w\Ÿ¡\‡∆°\ÎÑ˛7h™wíähëgòÖ&_\ÂEâ«Ωucàp>7¡!\'˛åHX\0\≈|Æ\“D\\cAdxU¯¡;äæ—îj¶J©KûSb\›i\‡EôGT≤¨\”;,H\Â˝<-\⁄jÖ’º∆ØFâkºm\Ì¬¥a°\Â¢%¨$\—l6ëÛÖ$-≈∏Yº\rÿèZD\·\»y\‰Ñló(ÆµæÒs\‰n6∫õ\0\0ôÚU>i˚Ç¢\È:ëh5\€VséBCÇ\ÿ›¨†\⁄7göx\√V˙vK\«\—!Ø€Ö\‰ˆv(\\\…\Ÿ5˛:˘}3¡çz.Q^°ˇàÒNe\ŸzìòM\”ˆÑkâ\÷Äª:ù\Ï\Îj\‡¶\À%\“\Ë\«nMø\ﬁ\¬˜\⁄j\Ê.\Á™7\ﬁˇ≠	/\\%àm\¬¶ZDj\€∆úΩæ¸doEπ\–+\›†˚ÚªÖ!¥\‚Æjd~+†ˇ\Ê¢?∞}˘¢eBCV~\Ÿ\‹Iï\œ\ÁXI\·ºV\n¡ì\ﬂ\–?\ﬂ\0zq\“\√˘à∏ëp\\\«îyHÄéF/	\÷c?8@¯F\Ã \∆\…\≈\È1\«\„¯|4é˜|\—ı\‰â\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20221130233323+01\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20221130233323+01\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001501 00000 n \n0000001795 00000 n \n0000001883 00000 n \n0000001976 00000 n \n0000000015 00000 n \n0000002076 00000 n \n0000001760 00000 n \n0000002139 00000 n \n0000002193 00000 n \n0000002225 00000 n \n0000002329 00000 n \ntrailer\n<</Info 11 0 R/ID [<e9dfdd7053fcad222954be9f9f93d291><fed543b7a5d818ec846c6562abd2932b>]/Root 10 0 R/Size 12>>\nstartxref\n2539\n%%EOF\n',0,'Novembre',0,0,'N¬∞ 2022.11.07.00186','20221130-1000',7000,8400,14,1400,'N. Acquitt√©',500,4),(22,'Ekino','30/01/2023',NULL,'31/12/2022',30,'KO','\\2022\\12-D√©cembre\\Facture Client\\Facture_Sbatec_Easy_202212_1001.pdf','Facture_Sbatec_Easy_202212_1001.pdf',_binary '%PDF-1.5\n%\‚\„\œ\”\n5 0 obj\n<</Filter/FlateDecode/Length 1422>>stream\nxúµXMr\€6\ﬁ\Îo\”I2c3\0˛y\';rc7vâ\È¢M0\ÀL!R\·è\Î\ÓzÜû GËë≤{ä>P¢d\…™\‘ÒHÄ\ﬂ\√˚˝?ˆN\„û\ÎCH|à\'ΩA\‹{\€cp©•@OáúA<\ÎΩ<ß@	ƒ∑Ω\Á/\‚z\ÔzÅdˆ¯°ê@˘N\‡.d@}˝ ióãi\Ô˘¯¥\Œ\‡,\œ\ ZUi6\’Gò~\Â\ÿ_~\≈q“äµêRÅ\ÀFWàd1ÙD,\0\¬\" ÑPK@}√Ä:\‹\ﬂT\Õ\ZèR\nßy≠\‰Ω(&p-™4œÑzÑ∫uò9\—\Œ\√\"\Ê£Z¶\Í¯J®ôH\À<;¿\rBn∏¡∏?~¢ÜD\Ã\”J(òH\–R\Í\"/ÄÕÉïµûbèú3g\Ï†⁄≤JÖÑ–•éVÂ∂Ü£$ÇÄÜN¥\”S\ŒG@\√\\Í°≥P∞F\ƒ\À˙ÄlP$U]H{/äüØ\Ó\≈7aÆˇ∂D`.ö\Õ%Ü Û°?[¢pJ\ZÇœò!D˘EQe≤∞\≈c°É9¡\'æ\„y+d[\—\ÏAQKàEZ\›\‰uıı0\Êú;QÄV@\…wû`EZÙ¥\‹q\nfO	\–evûr>\Í_ü\r,o\Ó%Ü¿è±Uo@LΩfˆ	S2b˝yÄºB#C^â\Í√â-\≈(p\r „¥êï=å~¿L9r≥Åíò¥^Rˆí\∆l\”$£¿•¯c\Ëˆ∑$¿¸¿°k∫	ÛFBÒe™\‰Lfî≤P4B•eüA=´3©\ÎR\"KòàJñüNP\Œ#ùa\—Yd©,\‡C^\ÌÆ;ÒÉxñß\Â\—\ƒ\∆\‚Ÿâ\Ì>ô\Õ$\’5ø\‘\»së∂8\0*£F‘ºò\‰Y÷†>nDñÙ\ƒ\Ê˙˛\n\'+E\‚o\¬6obPèÇz\ÿ \·I´¶ô!âhÅÅÅt≠c]˛˜XAp´-ú†¿ik≤πíiì\"Oµ9Q\Ï\Ó\ÃN¯\«\“p≤§Pk°íZiu ë©D˝†≠∏\ﬁ?wﬂøÄ[=\≈¿Y¨=K≥™)˛©@5S°é\–≈í|6G\n¢S¢ı1\‹\’$w\rjW:{©ê8!tî	%ıáY\œ√¢¢gj5\€ì≈äø\⁄\„/\◊çI{`ªe9\ŸíPuì\Õ!\È\›v\¬\Zsë\«◊∂~Z¯_5e:\ÕZíkK2–Ñßé\œvaΩ≠ë¢\ÿV?lk\Ã@\Ô≤† 0£ãÙjÑ\“¡\nØc\€z\Îπ\›≤\ 5©∑\∆k’à\≈\›gªÚ¿%F≥\Ì•¸Hì{\ ˇüD pB\\\ﬂ31£oÄzö\0\\,´ã†gj5\€	¿\Â^∑g\€c\‡ã-\À\…\ÊêtÄ™õl˚&\0óôöã∏ı¶8>\”\…^†E\‰\’¸ﬂå\ >}ãN∫\⁄\‰fí\ÍÜ¢\"Z\‰\¬e°\…\◊yQbªÒ`\›\"ú\œMp»à?#ñ?1ü´47X^æ≥ƒé¢ˇ\ËH5O•Ã•\«Ù\0/d∞tx\ƒ@$\À:Ω\«¬Å§Q>\Ã”¢≠UX\Àk¸jîH±\¬\€V.\ÃPfZ&Z\¬J\Õe9_H\“å\€≈´Ä˝àÖAéLëGN\»vâ\‚\⁄ˆu\»E)f\»\›\\t7˝;\02%\‰´|\“vE\”ı\r\"\—j∂≠\ÂÖÜ±ªUA\r¥Ø\Õ4\ÌÜ{¨ÛÌñé°C^∑\…\›\ÏO∏í≥¸uÚ˙fÇı\\>!ºBˇ	ﬂù ≤ı&1-ö¶\Ì\◊≠!\0wu:\Ÿ\◊\’~\«MóK§\—˜›ö~∑Ö!\Óµ\’\Ã]\ŒUoºˇ++:^∏J\€>Ñ\ÌKµà‘∂â¸pq˝£Ωy\‰\Z@ØtKÄ\ÓÛ\nc≥5ÑV\‹UçÇ\Ã\ÔÙ\ﬂ\\ÙØmﬂºhÇ–êï_5˜R\ÂÛ9VR8ØïB\‰∑tè\ƒ7Ä^úˆØO\‡|D\‹H8Æ\„\ <$@G£ó\Î± |#f\„Ù\‚\ÏÜ\„q|>\Z\«{æ\Â˙\ \\àm\nendstream\nendobj\n1 0 obj\n<</Tabs/S/Group<</S/Transparency/Type/Group/CS/DeviceRGB>>/Contents 5 0 R/Type/Page/Resources<</ColorSpace<</CS/DeviceRGB>>/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]/Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R>>>>/Parent 6 0 R/MediaBox[0 0 595 842]>>\nendobj\n7 0 obj\n[1 0 R/XYZ 0 852 0]\nendobj\n2 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\nendobj\n3 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\nendobj\n4 0 obj\n<</Subtype/Type1/Type/Font/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\nendobj\n6 0 obj\n<</Kids[1 0 R]/Type/Pages/Count 1/ITXT(2.1.7)>>\nendobj\n8 0 obj\n<</Names[(JR_PAGE_ANCHOR_0_1) 7 0 R]>>\nendobj\n9 0 obj\n<</Dests 8 0 R>>\nendobj\n10 0 obj\n<</Names 9 0 R/Type/Catalog/Pages 6 0 R/ViewerPreferences<</PrintScaling/AppDefault>>>>\nendobj\n11 0 obj\n<</ModDate(D:20221207173225+01\'00\')/Creator(JasperReports Library version 6.16.0-48579d909b7943b64690c65c71e07e0b80981928)/CreationDate(D:20221207173225+01\'00\')/Producer(iText 2.1.7 by 1T3XT)>>\nendobj\nxref\n0 12\n0000000000 65535 f \n0000001505 00000 n \n0000001799 00000 n \n0000001887 00000 n \n0000001980 00000 n \n0000000015 00000 n \n0000002080 00000 n \n0000001764 00000 n \n0000002143 00000 n \n0000002197 00000 n \n0000002229 00000 n \n0000002333 00000 n \ntrailer\n<</Info 11 0 R/ID [<9331c143368ec6ed36aa2d57eac90b59><6a2624942dfd4eec24f4ffe0ec6387c3>]/Root 10 0 R/Size 12>>\nstartxref\n2543\n%%EOF\n',0,'D√©cembre',0,0,'N¬∞ 2022.11.07.00186','20221231-1001',2000,2400,4,400,'N. Acquitt√©',500,4);
/*!40000 ALTER TABLE `t_facture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_prestation`
--

DROP TABLE IF EXISTS `t_prestation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_prestation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `client_prestation` varchar(255) NOT NULL DEFAULT '0',
  `delai_paiement` bigint NOT NULL DEFAULT '0',
  `designation` varchar(255) NOT NULL DEFAULT '0',
  `numero_commande` varchar(255) NOT NULL DEFAULT '0',
  `quantite` float NOT NULL DEFAULT '0',
  `tarifht` float NOT NULL DEFAULT '0',
  `date_debut` varchar(255) DEFAULT NULL,
  `date_fin` varchar(255) DEFAULT NULL,
  `client_id` bigint DEFAULT NULL,
  `consultant_id` bigint DEFAULT NULL,
  `facture_id` bigint DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKen05trsnmiy6d1cxkc0tujode` (`client_id`),
  KEY `FKgawvw45n0svkjtceple40opnr` (`consultant_id`),
  KEY `FKo3uk4j1m8vyr4udcugf3rhu0n` (`company_id`),
  CONSTRAINT `FKen05trsnmiy6d1cxkc0tujode` FOREIGN KEY (`client_id`) REFERENCES `t_client` (`id`),
  CONSTRAINT `FKgawvw45n0svkjtceple40opnr` FOREIGN KEY (`consultant_id`) REFERENCES `t_consultant` (`id`),
  CONSTRAINT `FKo3uk4j1m8vyr4udcugf3rhu0n` FOREIGN KEY (`company_id`) REFERENCES `t_company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_prestation`
--

LOCK TABLES `t_prestation` WRITE;
/*!40000 ALTER TABLE `t_prestation` DISABLE KEYS */;
INSERT INTO `t_prestation` VALUES 	(1,'Odyssey Consulting',30,'La Prestation est r√©alis√©e pour le compte de','xxxxxxxxxxxxxxxxxx',0,470,'01/03/2021','30/06/2021',1,1,NULL,1),
									(2,'Accor Hotels',30,'La Prestation est r√©alis√©e pour le compte de','N¬∞13.21.19.05.14.01',0,490,'08/07/2021','31/12/2021',2,1,NULL,1),
									(3,'Accor Hotels',30,'La Prestation est r√©alis√©e pour le compte de','N¬∞ 13.21.19.05.14.01',0,510,'01/01/2022','30/09/2022',2,1,NULL,1),
									(4,'Ekino',30,'La Prestation est r√©alis√©e pour le compte de','N¬∞ 2022.11.07.00186',0,500,'09/11/2022','01/01/2024',3,1,NULL,1);
/*!40000 ALTER TABLE `t_prestation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9sof6539ywhxemf6ojn0fqera` (`user_id`),
  CONSTRAINT `FK9sof6539ywhxemf6ojn0fqera` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_ref`
--

DROP TABLE IF EXISTS `t_role_ref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role_ref` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_ref`
--

LOCK TABLES `t_role_ref` WRITE;
/*!40000 ALTER TABLE `t_role_ref` DISABLE KEYS */;
INSERT INTO `t_role_ref` VALUES (1,'ADMIN','ROLE_ADMIN','',''),(2,'CONSULT','ROLE_ADMIN','',''),(3,'READ','ROLE_ADMIN','',''),(4,'WRITE','ROLE_ADMIN','','');
/*!40000 ALTER TABLE `t_role_ref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_tva`
--

DROP TABLE IF EXISTS `t_tva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_tva` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_payment` varchar(255) NOT NULL,
  `exercise` varchar(255) NOT NULL,
  `montant_payment` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_tva`
--

LOCK TABLES `t_tva` WRITE;
/*!40000 ALTER TABLE `t_tva` DISABLE KEYS */;
INSERT INTO `t_tva` VALUES (1,'28/12/2021','2021',2588),(2,'29/07/2021','2021',3533),(5,'05/05/2022','2021',9558),(8,'22/06/2022','2022',10479),(9,'29/07/2022','2022',684),(10,'23/11/2022','2022',2490);
/*!40000 ALTER TABLE `t_tva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL DEFAULT '0',
  `first_name` varchar(45) NOT NULL DEFAULT '0',
  `last_name` varchar(45) NOT NULL DEFAULT '0',
  `password` varchar(45) NOT NULL DEFAULT '0',
  `activated` tinyint NOT NULL DEFAULT '1',
  `company_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjoyvjcw2ynic8bvdjm1elpf16` (`company_id`),
  CONSTRAINT `FKjoyvjcw2ynic8bvdjm1elpf16` FOREIGN KEY (`company_id`) REFERENCES `t_company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'allouchi@hotmail.fr','Mustapha','Aliane','123456',1,1),(2,'khalid@hotmail.fr','Khalid','Aliane','123456',1,2),(3,'salma@hotmail.fr','Salma','Aliane','123456',1,1),(4,'btissame@hotmail.fr','Btissame','Aliane','123456',1,1);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-24  2:17:50
