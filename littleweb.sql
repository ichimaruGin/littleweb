-- MySQL dump 10.13  Distrib 5.6.10, for Win64 (x86_64)
--
-- Host: localhost    Database: littleweb
-- ------------------------------------------------------
-- Server version	5.6.10

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `location` varchar(255) NOT NULL,
  `latitude` varchar(20) NOT NULL,
  `longitude` varchar(20) NOT NULL,
  `function` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_departmentname` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'co1','aclbaysbliq','120.123456','30.21421','ev_seller'),(2,'co2','aclbaysbliq','120.123456','30.21421','ev_seller'),(3,'co3','asjcaubcaiaern','0','0','ev_seller'),(4,'浙江菜鸟集团','asjcaubcaiaern','0','0','ev_manager'),(5,'绍兴纺机集团','绍兴市绍兴县绍兴纺机集团','30.094546','120.536599','ev_manager');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `error`
--

DROP TABLE IF EXISTS `error`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `error` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `terminal_id` varchar(10) NOT NULL,
  `error_id` int(11) NOT NULL,
  `upload_time` bigint(64) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_l841vnnct6hxypnm4r5pd3xkr` (`error_id`),
  CONSTRAINT `FK_l841vnnct6hxypnm4r5pd3xkr` FOREIGN KEY (`error_id`) REFERENCES `error_referrence` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `error`
--

LOCK TABLES `error` WRITE;
/*!40000 ALTER TABLE `error` DISABLE KEYS */;
/*!40000 ALTER TABLE `error` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `error_referrence`
--

DROP TABLE IF EXISTS `error_referrence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `error_referrence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `error_code` varchar(255) NOT NULL,
  `error_detail` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4xh0g0amk2wqbb6wiijm5oucg` (`error_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `error_referrence`
--

LOCK TABLES `error_referrence` WRITE;
/*!40000 ALTER TABLE `error_referrence` DISABLE KEYS */;
/*!40000 ALTER TABLE `error_referrence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rent_info`
--

DROP TABLE IF EXISTS `rent_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rent_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `terminal_id` varchar(10) NOT NULL,
  `start_time` bigint(20) DEFAULT NULL,
  `deadline` bigint(20) NOT NULL,
  `lesseer_id` int(11) NOT NULL,
  `renter_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rent_info`
--

LOCK TABLES `rent_info` WRITE;
/*!40000 ALTER TABLE `rent_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `rent_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sell_info`
--

DROP TABLE IF EXISTS `sell_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sell_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `terminal_id` varchar(10) NOT NULL,
  `sell_time` bigint(20) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `buyer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sell_info`
--

LOCK TABLES `sell_info` WRITE;
/*!40000 ALTER TABLE `sell_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `sell_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(20) NOT NULL,
  `user_level` varchar(10) NOT NULL,
  `department_id` int(11) DEFAULT NULL,
  `register_time` int(11) NOT NULL,
  `is_valid` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_j8pqu7twm7ekoe3orxctxr9f4` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ichimaruGin','1234rewQ','admin',0,1427358140,1),(2,'ev_seller','123456','department',1,1427358140,1),(3,'tiantian','1234563654756','personal',0,1427457899,0),(4,'user三大神','pass','personal',1,1427872097,0),(6,'user','pass','personal',0,1427872188,0),(7,'userds','pass','department',0,1427872625,0),(8,'user1','pass','department',0,1427873200,0),(9,'user2','pass','department',0,1427873206,0),(10,'user512','pass','personal',0,1427873656,1),(11,'user6','pass','personal',0,1427873660,1),(12,'user844','pass','personal',0,1428031490,0),(13,'大神大神','pass12','personal',0,1428497342,0),(14,'user980','pass','personal',0,1428903516,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `terminal_id` varchar(10) NOT NULL,
  `type` varchar(255) NOT NULL,
  `terminal_license` varchar(255) NOT NULL,
  `belong_department_id` int(11) NOT NULL,
  `origin_department_id` int(11) NOT NULL,
  `current_status` varchar(20) NOT NULL,
  `recent_rent_id` int(11) NOT NULL,
  `recent_sell_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4dudpbo4yrv68hqslmkmc8ss7` (`terminal_id`),
  UNIQUE KEY `UK_2uj7tk59hctnh571mxyueq35a` (`terminal_license`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,'zj05710000','B','浙A08080',1,1,'none',0,0),(2,'zj05710001','A','浙A01010',2,2,'none',0,0),(3,'zj05710002','A','浙A00001',1,1,'none',0,0),(4,'zj05710003','A','浙A00002',1,1,'none',0,0),(5,'zj05710004','A','浙A00003',1,1,'none',0,0),(6,'zj05710005','A','浙A00004',1,1,'none',0,0),(7,'zj05710006','A','浙A00005',1,1,'none',0,0);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-13 22:22:39
