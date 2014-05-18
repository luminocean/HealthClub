CREATE DATABASE  IF NOT EXISTS `health_club` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `health_club`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: health_club
-- ------------------------------------------------------
-- Server version	5.5.33-log

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
-- Table structure for table `coach`
--

DROP TABLE IF EXISTS `coach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coach` (
  `coach_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`coach_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coach`
--

LOCK TABLES `coach` WRITE;
/*!40000 ALTER TABLE `coach` DISABLE KEYS */;
INSERT INTO `coach` VALUES (1,'Tom'),(2,'Rachel'),(3,'Robin'),(4,'Monica'),(5,'Cindy'),(6,'Carven');
/*!40000 ALTER TABLE `coach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `detail` text,
  `closed` bit(1) DEFAULT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'肌肉撕裂之日！','专业健身教练带来的爆发式肌肉训练课程！逃跑是不允许的！','\0'),(2,' 我们不用很麻烦很累就可以放松','零基础瑜伽课程 妈妈再也不用担心我','\0'),(3,'我想起了那天夕阳下的奔跑','5000米长跑训练 附赠人身意外保险','\0'),(4,' 放弃的话比赛就结束了哦','为期三周的专业实战训练课程 ','\0'),(5,' 体重大作战','以科学健康的方式达到你理想的体重（一点也不轻松）','');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `occasion`
--

DROP TABLE IF EXISTS `occasion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `occasion` (
  `occasion_id` int(11) NOT NULL AUTO_INCREMENT,
  `time` datetime NOT NULL,
  `event_id` int(11) NOT NULL,
  `place_id` int(11) NOT NULL,
  `coach_id` int(11) NOT NULL,
  PRIMARY KEY (`occasion_id`),
  KEY `fk_event_occasion_event1_idx` (`event_id`),
  KEY `FK2E27EAE74AD13535` (`place_id`),
  KEY `FK2E27EAE7A2602855` (`coach_id`),
  CONSTRAINT `FK2E27EAE74AD13535` FOREIGN KEY (`place_id`) REFERENCES `place` (`place_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK2E27EAE7A2602855` FOREIGN KEY (`coach_id`) REFERENCES `coach` (`coach_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_event_occasion_event1` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `occasion`
--

LOCK TABLES `occasion` WRITE;
/*!40000 ALTER TABLE `occasion` DISABLE KEYS */;
INSERT INTO `occasion` VALUES (1,'2014-04-23 14:05:10',1,1,1),(2,'2014-04-26 16:05:10',1,1,2),(3,'2014-05-04 14:05:10',1,2,3),(4,'2014-04-23 14:05:10',2,3,4),(5,'2014-04-26 16:05:10',2,4,5),(6,'2014-05-04 14:05:10',2,1,6),(7,'2014-04-23 14:05:10',3,6,5),(8,'2014-04-26 16:05:10',3,4,4),(9,'2014-05-04 14:05:10',3,5,3),(10,'2014-04-23 14:05:10',4,8,2),(11,'2014-04-26 16:05:10',4,8,1),(12,'2014-05-04 14:05:10',4,4,1),(13,'2014-02-26 16:05:10',5,6,4),(14,'2014-01-04 14:05:10',5,1,2);
/*!40000 ALTER TABLE `occasion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD11C320671F31D9F` (`user_id`),
  CONSTRAINT `FKD11C320671F31D9F` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (4,200,'2014-03-04 19:13:52',14),(5,100,'2014-03-05 15:11:10',14),(6,100,'2014-03-05 15:53:11',14),(7,100,'2014-03-05 15:54:19',16),(8,100,'2014-03-05 15:59:22',18),(9,100,'2014-03-05 16:03:13',14),(10,65,'2014-03-05 16:13:06',18),(11,185,'2014-03-05 16:16:31',18),(12,195,'2014-03-05 16:19:15',18),(13,165,'2014-03-05 16:26:24',18);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `place`
--

DROP TABLE IF EXISTS `place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `place` (
  `place_id` int(11) NOT NULL AUTO_INCREMENT,
  `place` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`place_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place`
--

LOCK TABLES `place` WRITE;
/*!40000 ALTER TABLE `place` DISABLE KEYS */;
INSERT INTO `place` VALUES (1,'A101'),(2,'A102'),(3,'A103'),(4,'A104'),(5,'B202'),(6,'B203'),(7,'B204'),(8,'B205');
/*!40000 ALTER TABLE `place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserve`
--

DROP TABLE IF EXISTS `reserve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserve` (
  `event_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`event_id`,`user_id`),
  KEY `fk_event_has_user_user1_idx` (`user_id`),
  KEY `fk_event_has_user_event1_idx` (`event_id`),
  CONSTRAINT `fk_event_has_user_event1` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_has_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserve`
--

LOCK TABLES `reserve` WRITE;
/*!40000 ALTER TABLE `reserve` DISABLE KEYS */;
INSERT INTO `reserve` VALUES (1,13),(2,14),(5,14),(2,18);
/*!40000 ALTER TABLE `reserve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `event_id` int(11) NOT NULL,
  `tag` varchar(45) NOT NULL,
  PRIMARY KEY (`event_id`,`tag`),
  CONSTRAINT `fk_tag_event1` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'健身'),(2,'瑜伽'),(3,'有氧'),(3,'跑步'),(4,'篮球'),(5,'健身'),(5,'有氧');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `state` int(11) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `email_UNIQUE` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (11,'haha@ha.com','haha','haha',0,'TFARGB3',0),(12,'kang@k.com','Kang','kang',0,'HKEW41Z',1),(13,'single@single.com','Single','s',0,'QUPZK5I',0),(14,'allen@allen.com','Allen','a',0,'24XFTNP',0),(16,'smith@smith.com','smith','s',0,'V76RB6G',1),(18,'family@family.com','Family','f',0,'4HMQLR0',1),(19,'microcosmos17@163.com','NAME','123',1,'3KLNRHY',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_detail`
--

DROP TABLE IF EXISTS `user_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_detail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `living_place` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `FKDCA35F4571F31D9F` (`user_id`),
  CONSTRAINT `FKDCA35F4571F31D9F` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_detail`
--

LOCK TABLES `user_detail` WRITE;
/*!40000 ALTER TABLE `user_detail` DISABLE KEYS */;
INSERT INTO `user_detail` VALUES (1,14,'haha','ha',11,0),(2,12,'sssTown','sss',12,0),(3,15,'ggeVally','gge',12,1),(4,12,'LongBeach','long',12,0),(5,30,'青森','森够',13,1),(6,25,'特罗斯特区','艾伦',14,0),(7,31,'LA','Ruby',16,1),(8,30,'LA','Sandy',16,1),(9,34,'HE','Sandy',18,0),(10,7,'HE','dota',18,0),(11,16,'HE','dora',18,1),(12,36,'HE','kara',18,0),(13,16,'EARTH','NAME',19,1);
/*!40000 ALTER TABLE `user_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-13 12:27:32
