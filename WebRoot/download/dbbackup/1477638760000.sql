-- MySQL dump 10.13  Distrib 5.5.47, for Win32 (x86)
--
-- Host: localhost    Database: libdb
-- ------------------------------------------------------
-- Server version	5.5.47

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `type` int(10) NOT NULL DEFAULT '1',
  `telephone` varchar(255) NOT NULL,
  `create_time` varchar(255) NOT NULL,
  `status` int(10) NOT NULL,
  `area_id` int(255) NOT NULL,
  `auth` int(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `adminname` (`name`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'whg_admin','6E27C2CB46F6A4A06B9F3A40FCE42C84','123973173@qq.com',0,'15757575757','2016-10-12 15:13:12',1,0,0),(2,'hjadmintest','E8D315D81A2866D6BE5C9D598906F984','123973173@qq.com',1,'15757575757','2016-10-16 20:31:35',1,0,1),(3,'test2','F040A579C756C49FCF84588246A9D102','123973173@qq.com',0,'15757575757','2016-10-16 20:33:10',1,0,0),(4,'test3','ECBD164E8F0CCA20AAC3D89481F3D5CD','1111@qq.com',0,'15761211111','2016-10-17 08:12:26',1,0,1),(5,'test11','33E59BE11652DE5FF2DFA83FEF7AA0C0','123973173@qq.com',1,'15757575757','2016-10-17 08:31:54',1,1,1);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area` (
  `area_id` int(11) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(50) NOT NULL,
  `area_type` int(20) NOT NULL COMMENT '使用0代表市,1代表县区',
  PRIMARY KEY (`area_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (1,'东营市',0),(2,'东营区',1),(3,' 河口区',1),(4,'广饶县',1),(5,' 垦利县',1),(6,'利津县',1);
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `upload_size` int(255) NOT NULL,
  `examine_month` int(255) NOT NULL,
  `examine_times` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `declare_type`
--

DROP TABLE IF EXISTS `declare_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `declare_type` (
  `dec_id` int(11) NOT NULL AUTO_INCREMENT,
  `decname` varchar(20) NOT NULL,
  PRIMARY KEY (`dec_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `declare_type`
--

LOCK TABLES `declare_type` WRITE;
/*!40000 ALTER TABLE `declare_type` DISABLE KEYS */;
INSERT INTO `declare_type` VALUES (1,'音乐'),(2,'舞蹈'),(3,'戏剧曲艺'),(4,'朗诵主持'),(5,' 文学创作'),(6,' 书画'),(7,' 摄影'),(8,' 杂技'),(9,' 民间文艺'),(10,'电影电视');
/*!40000 ALTER TABLE `declare_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `degree`
--

DROP TABLE IF EXISTS `degree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `degree` (
  `degree_id` int(11) NOT NULL AUTO_INCREMENT,
  `degreename` varchar(20) NOT NULL,
  PRIMARY KEY (`degree_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `degree`
--

LOCK TABLES `degree` WRITE;
/*!40000 ALTER TABLE `degree` DISABLE KEYS */;
INSERT INTO `degree` VALUES (1,'学士'),(2,'硕士'),(3,' 博士');
/*!40000 ALTER TABLE `degree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu`
--

DROP TABLE IF EXISTS `edu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu` (
  `edu_id` int(11) NOT NULL AUTO_INCREMENT,
  `eduname` varchar(20) NOT NULL,
  PRIMARY KEY (`edu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu`
--

LOCK TABLES `edu` WRITE;
/*!40000 ALTER TABLE `edu` DISABLE KEYS */;
INSERT INTO `edu` VALUES (1,' 小学'),(2,' 初中'),(3,' 中专'),(4,' 高中'),(5,' 专科'),(6,' 本科'),(7,' 硕士研究生'),(8,' 博士研究生');
/*!40000 ALTER TABLE `edu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) NOT NULL,
  `uname` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (1,'0:0:0:0:0:0:0:1','admin','2016-10-16 20:29:23'),(2,'127.0.0.1','admin','2016-10-16 20:30:23'),(3,'192.168.1.100','admin','2016-10-16 20:31:01'),(4,'192.168.1.100','admin','2016-10-16 20:32:46'),(5,'192.168.1.100','admin','2016-10-16 20:36:04'),(6,'192.168.1.100','admin','2016-10-16 22:24:11'),(7,'192.168.1.100','admin','2016-10-16 22:54:25'),(8,'192.168.1.100','admin','2016-10-16 23:19:42'),(9,'0:0:0:0:0:0:0:1','admin','2016-10-17 08:11:49'),(10,'0:0:0:0:0:0:0:1','admin','2016-10-17 08:31:29'),(11,'0:0:0:0:0:0:0:1','test11','2016-10-17 08:32:09'),(12,'0:0:0:0:0:0:0:1','test11','2016-10-17 08:34:17'),(13,'0:0:0:0:0:0:0:1','test11','2016-10-17 08:34:55'),(14,'0:0:0:0:0:0:0:1','admin','2016-10-17 09:04:46'),(15,'0:0:0:0:0:0:0:1','admin','2016-10-17 09:57:43'),(16,'0:0:0:0:0:0:0:1','admin','2016-10-17 10:21:57'),(17,'0:0:0:0:0:0:0:1','test11','2016-10-17 10:23:25'),(18,'0:0:0:0:0:0:0:1','admin','2016-10-17 11:23:03'),(19,'0:0:0:0:0:0:0:1','admin','2016-10-17 13:46:26'),(20,'0:0:0:0:0:0:0:1','admin','2016-10-17 14:08:42'),(21,'0:0:0:0:0:0:0:1','admin','2016-10-17 14:49:19'),(22,'0:0:0:0:0:0:0:1','admin','2016-10-17 15:32:49'),(23,'0:0:0:0:0:0:0:1','admin','2016-10-17 15:44:54'),(24,'0:0:0:0:0:0:0:1','admin','2016-10-17 16:17:13'),(25,'0:0:0:0:0:0:0:1','admin','2016-10-17 16:32:29'),(26,'0:0:0:0:0:0:0:1','admin','2016-10-18 16:47:59'),(27,'0:0:0:0:0:0:0:1','test11','2016-10-18 17:27:57'),(28,'0:0:0:0:0:0:0:1','admin','2016-10-18 19:08:44'),(29,'0:0:0:0:0:0:0:1','admin','2016-10-18 19:13:21'),(30,'0:0:0:0:0:0:0:1','admin','2016-10-18 20:14:01'),(31,'0:0:0:0:0:0:0:1','admin','2016-10-18 20:28:00'),(32,'0:0:0:0:0:0:0:1','admin','2016-10-18 20:32:20'),(33,'0:0:0:0:0:0:0:1','admin','2016-10-18 21:03:52'),(34,'0:0:0:0:0:0:0:1','admin','2016-10-18 21:04:05'),(35,'0:0:0:0:0:0:0:1','admin','2016-10-18 22:12:52'),(36,'0:0:0:0:0:0:0:1','admin','2016-10-18 22:20:30'),(37,'0:0:0:0:0:0:0:1','admin','2016-10-18 23:00:40'),(38,'0:0:0:0:0:0:0:1','admin','2016-10-19 08:03:08'),(39,'0:0:0:0:0:0:0:1','admin','2016-10-19 10:33:24'),(40,'0:0:0:0:0:0:0:1','admin','2016-10-19 10:48:44'),(41,'0:0:0:0:0:0:0:1','admin','2016-10-19 10:59:53'),(42,'0:0:0:0:0:0:0:1','admin','2016-10-19 11:02:46'),(43,'0:0:0:0:0:0:0:1','admin','2016-10-19 11:13:07'),(44,'0:0:0:0:0:0:0:1','admin','2016-10-19 12:46:56'),(45,'0:0:0:0:0:0:0:1','admin','2016-10-19 12:57:52'),(46,'0:0:0:0:0:0:0:1','admin','2016-10-19 13:56:47'),(47,'0:0:0:0:0:0:0:1','test11','2016-10-19 14:01:25'),(48,'0:0:0:0:0:0:0:1','admin','2016-10-19 14:07:17'),(49,'0:0:0:0:0:0:0:1','admin','2016-10-19 14:32:34'),(50,'0:0:0:0:0:0:0:1','admin','2016-10-20 16:53:58'),(51,'0:0:0:0:0:0:0:1','admin','2016-10-20 17:10:27'),(52,'0:0:0:0:0:0:0:1','admin','2016-10-20 17:26:29'),(53,'0:0:0:0:0:0:0:1','admin','2016-10-20 19:02:09'),(54,'0:0:0:0:0:0:0:1','admin','2016-10-20 19:38:17'),(55,'0:0:0:0:0:0:0:1','admin','2016-10-20 19:48:52'),(56,'0:0:0:0:0:0:0:1','admin','2016-10-20 19:59:19'),(57,'0:0:0:0:0:0:0:1','admin','2016-10-20 20:10:59'),(58,'0:0:0:0:0:0:0:1','admin','2016-10-20 20:21:26'),(59,'0:0:0:0:0:0:0:1','admin','2016-10-20 20:40:01'),(60,'0:0:0:0:0:0:0:1','admin','2016-10-20 20:50:33'),(61,'0:0:0:0:0:0:0:1','admin','2016-10-20 21:00:57'),(62,'0:0:0:0:0:0:0:1','admin','2016-10-20 21:16:06'),(63,'0:0:0:0:0:0:0:1','admin','2016-10-20 21:24:59'),(64,'0:0:0:0:0:0:0:1','admin','2016-10-21 08:22:03'),(65,'0:0:0:0:0:0:0:1','admin','2016-10-21 08:35:42'),(66,'0:0:0:0:0:0:0:1','admin','2016-10-21 09:13:04'),(67,'0:0:0:0:0:0:0:1','admin','2016-10-21 09:35:17'),(68,'0:0:0:0:0:0:0:1','admin','2016-10-21 09:52:56'),(69,'0:0:0:0:0:0:0:1','admin','2016-10-21 10:03:12'),(70,'0:0:0:0:0:0:0:1','admin','2016-10-21 10:13:58'),(71,'0:0:0:0:0:0:0:1','admin','2016-10-21 10:36:37'),(72,'0:0:0:0:0:0:0:1','admin','2016-10-21 11:00:26'),(73,'0:0:0:0:0:0:0:1','admin','2016-10-21 11:32:23'),(74,'0:0:0:0:0:0:0:1','admin','2016-10-21 14:09:23'),(75,'0:0:0:0:0:0:0:1','admin','2016-10-21 14:46:24'),(76,'0:0:0:0:0:0:0:1','admin','2016-10-23 09:24:59'),(77,'0:0:0:0:0:0:0:1','admin','2016-10-23 15:56:26'),(78,'0:0:0:0:0:0:0:1','admin','2016-10-23 20:36:47'),(79,'0:0:0:0:0:0:0:1','admin','2016-10-23 20:52:49'),(80,'0:0:0:0:0:0:0:1','admin','2016-10-23 22:07:32'),(81,'0:0:0:0:0:0:0:1','admin','2016-10-23 22:17:54'),(82,'0:0:0:0:0:0:0:1','admin','2016-10-23 22:32:18'),(83,'0:0:0:0:0:0:0:1','admin','2016-10-23 23:32:01'),(84,'0:0:0:0:0:0:0:1','admin','2016-10-24 00:13:27'),(85,'0:0:0:0:0:0:0:1','admin','2016-10-24 08:48:19'),(86,'127.0.0.1','admin','2016-10-24 14:17:48'),(87,'0:0:0:0:0:0:0:1','admin','2016-10-24 16:30:14'),(88,'0:0:0:0:0:0:0:1','whg_admin','2016-10-26 09:57:47'),(89,'0:0:0:0:0:0:0:1','whg_admin','2016-10-26 10:56:29'),(90,'0:0:0:0:0:0:0:1','whg_admin','2016-10-27 00:36:21'),(91,'0:0:0:0:0:0:0:1','test11','2016-10-27 08:18:38'),(92,'0:0:0:0:0:0:0:1','whg_admin','2016-10-27 08:45:16'),(93,'0:0:0:0:0:0:0:1','test11','2016-10-27 09:25:20'),(94,'0:0:0:0:0:0:0:1','whg_admin','2016-10-27 09:29:33'),(95,'0:0:0:0:0:0:0:1','test3','2016-10-27 09:31:52'),(96,'0:0:0:0:0:0:0:1','whg_admin','2016-10-27 09:34:17'),(97,'0:0:0:0:0:0:0:1','whg_admin','2016-10-27 09:35:05'),(98,'0:0:0:0:0:0:0:1','hjadmintest','2016-10-27 09:38:49'),(99,'0:0:0:0:0:0:0:1','whg_admin','2016-10-27 15:22:22'),(100,'0:0:0:0:0:0:0:1','whg_admin','2016-10-27 20:54:42'),(101,'0:0:0:0:0:0:0:1','whg_admin','2016-10-27 21:25:50'),(102,'0:0:0:0:0:0:0:1','whg_admin','2016-10-27 23:06:29'),(103,'0:0:0:0:0:0:0:1','whg_admin','2016-10-28 08:10:18'),(104,'0:0:0:0:0:0:0:1','whg_admin','2016-10-28 10:05:35'),(105,'0:0:0:0:0:0:0:1','whg_admin','2016-10-28 14:45:02'),(106,'0:0:0:0:0:0:0:1','whg_admin','2016-10-28 14:53:29');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msg`
--

DROP TABLE IF EXISTS `msg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msg` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `user_id` int(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `admin_id` int(255) NOT NULL,
  `admin_name` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msg`
--

LOCK TABLES `msg` WRITE;
/*!40000 ALTER TABLE `msg` DISABLE KEYS */;
/*!40000 ALTER TABLE `msg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mz`
--

DROP TABLE IF EXISTS `mz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mz` (
  `mz_id` int(11) NOT NULL AUTO_INCREMENT,
  `mzname` varchar(20) NOT NULL,
  PRIMARY KEY (`mz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mz`
--

LOCK TABLES `mz` WRITE;
/*!40000 ALTER TABLE `mz` DISABLE KEYS */;
INSERT INTO `mz` VALUES (1,'汉族'),(2,'蒙古族'),(3,'回族'),(4,'藏族'),(5,'维吾尔族'),(6,'苗族'),(7,'彝族'),(8,'壮族'),(9,'布依族'),(10,'朝鲜族'),(11,'满族'),(12,'侗族'),(13,'瑶族'),(14,'白族'),(15,'土家族'),(16,'哈尼族'),(17,'哈萨克族'),(18,'傣族'),(19,'黎族'),(20,'傈僳族'),(21,'佤族'),(22,'畲族'),(23,'高山族'),(24,'拉祜族'),(25,'水族'),(26,'东乡族'),(27,'纳西族'),(28,'景颇族'),(29,'柯尔克孜族'),(30,'土族'),(31,'达斡尔族'),(32,'仫佬族'),(33,'羌族'),(34,' 布朗族'),(35,' 撒拉族'),(36,' 毛难族'),(37,' 仡佬族'),(38,' 锡伯族'),(39,' 阿昌族'),(40,' 普米族'),(41,' 塔吉克族'),(42,' 怒族'),(43,' 乌孜别克族'),(44,' 俄罗斯族'),(45,' 鄂温克族'),(46,' 崩龙族'),(47,' 保安族'),(48,' 裕固族'),(49,' 京族'),(50,' 塔塔尔族'),(51,' 独龙族'),(52,' 鄂伦春族'),(53,' 赫哲族'),(54,' 门巴族'),(55,' 珞巴族'),(56,' 基诺族'),(57,' 其他');
/*!40000 ALTER TABLE `mz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system`
--

DROP TABLE IF EXISTS `system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `keywords` text,
  `description` text,
  `copyright` varchar(255) DEFAULT NULL,
  `record` varchar(255) DEFAULT NULL,
  `statistics_code` text,
  `open` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system`
--

LOCK TABLES `system` WRITE;
/*!40000 ALTER TABLE `system` DISABLE KEYS */;
INSERT INTO `system` VALUES (1,'东营市社会文艺人才信息资源库','关键词设置2','网站描述设置','东营市文化馆版权所有','鲁ICP备00000000001号',' 统计代码设置',0);
/*!40000 ALTER TABLE `system` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upload_audio`
--

DROP TABLE IF EXISTS `upload_audio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `upload_audio` (
  `id` int(255) NOT NULL,
  `user_id` int(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `create_time` varchar(255) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `size` int(255) NOT NULL DEFAULT '100',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upload_audio`
--

LOCK TABLES `upload_audio` WRITE;
/*!40000 ALTER TABLE `upload_audio` DISABLE KEYS */;
/*!40000 ALTER TABLE `upload_audio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upload_photo`
--

DROP TABLE IF EXISTS `upload_photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `upload_photo` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `user_id` int(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `create_time` varchar(255) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `size` int(255) NOT NULL DEFAULT '100',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upload_photo`
--

LOCK TABLES `upload_photo` WRITE;
/*!40000 ALTER TABLE `upload_photo` DISABLE KEYS */;
INSERT INTO `upload_photo` VALUES (2,47,'1111111','11111','11111',100),(1,47,'22222','22222','22222',100),(3,47,'201302011702405145293.jpg','2016-10-19 12:26:15','庞勇',100),(4,47,'649830154.jpg','2016-10-19 12:28:00','庞勇',100),(5,47,'60-130220143607.jpg','2016-10-24 00:33:34','庞勇',100),(6,47,'60-1302201436072.jpg','2016-10-24 09:11:13','庞勇',100),(7,47,'file','2016-10-24 10:53:30','庞勇',100),(8,47,'60-130220143607.jpg','2016-10-24 11:08:19','庞勇',100),(9,47,'60-130220143607.jpg','2016-10-24 11:09:05','庞勇',100),(10,47,'jbg.jpg','2016-10-24 11:12:13','庞勇',100),(11,47,'ec8d5256bc06f18449980ed7cca921f6.png','2016-10-24 11:30:14','庞勇',100);
/*!40000 ALTER TABLE `upload_photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upload_video`
--

DROP TABLE IF EXISTS `upload_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `upload_video` (
  `id` int(255) NOT NULL,
  `user_id` int(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `create_time` varchar(255) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `size` int(255) NOT NULL DEFAULT '100',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upload_video`
--

LOCK TABLES `upload_video` WRITE;
/*!40000 ALTER TABLE `upload_video` DISABLE KEYS */;
/*!40000 ALTER TABLE `upload_video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uploads`
--

DROP TABLE IF EXISTS `uploads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uploads` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `user_id` int(255) NOT NULL,
  `type` varchar(100) NOT NULL,
  `path` varchar(255) NOT NULL,
  `create_time` varchar(255) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uploads`
--

LOCK TABLES `uploads` WRITE;
/*!40000 ALTER TABLE `uploads` DISABLE KEYS */;
INSERT INTO `uploads` VALUES (1,37,'1','20160822020450_207881.png','2016-10-16 12:37:05',NULL),(2,37,'1','20160822020450_207882.png','2016-10-16 13:36:51',NULL),(3,37,'1','20160822020450_207884.png','2016-10-17 13:47:06',NULL),(4,37,'1','20160822020450_207885.png','2016-10-17 16:37:35',NULL),(5,47,'1','20130201170240514529.jpg','2016-10-19 12:18:50',NULL),(6,47,'1','201302011702405145291.jpg','2016-10-19 12:20:17',NULL),(7,47,'1','201302011702405145292.jpg','2016-10-19 12:20:38',NULL);
/*!40000 ALTER TABLE `uploads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) NOT NULL,
  `true_name` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `dec_id` int(11) unsigned zerofill DEFAULT NULL,
  `media_path` varchar(255) DEFAULT NULL,
  `join_work` varchar(30) DEFAULT NULL,
  `usersex` enum('1','0') DEFAULT '1',
  `mz_id` int(10) unsigned zerofill DEFAULT NULL,
  `zzmm_id` int(10) unsigned zerofill DEFAULT NULL,
  `degree_id` int(11) unsigned zerofill DEFAULT NULL,
  `edu_id` int(11) unsigned zerofill DEFAULT NULL,
  `card` varchar(255) DEFAULT NULL,
  `area_id` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `edu__full_time` int(10) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `specialty` varchar(255) DEFAULT NULL,
  `ysjj` varchar(255) DEFAULT NULL,
  `health` varchar(255) DEFAULT NULL,
  `reg_date` varchar(255) NOT NULL,
  `status` int(10) DEFAULT NULL,
  `birth` varchar(255) DEFAULT NULL,
  `photo_path` varchar(255) DEFAULT NULL,
  `technical_position` varchar(255) DEFAULT NULL,
  `degree_full_time` int(10) unsigned zerofill DEFAULT NULL,
  `company_tel` varchar(255) DEFAULT NULL,
  `socio_part_time` varchar(255) DEFAULT NULL,
  `telephone` varchar(100) DEFAULT NULL,
  `awards` varchar(255) DEFAULT NULL,
  `opinion` varchar(255) DEFAULT NULL,
  `business_achievement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`uname`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (31,'py','张三','5D23494D3D93A7EF5BDA2FCE1091C7A41',00000000001,NULL,'2016-10-20','1',0000000018,0000000001,00000000001,00000000000,'372321199999999999','1','山东省东营市',0,'山东汇佳','唱歌舞蹈','中央戏剧表演','健康','2016-10-08 16:05:55',1,'2016-10-04','defaultheadpic.jpg','志愿者',0000000001,'8086998','社会兼职','15762188888','表演一等奖','希望申请通过','销售实习'),(33,'pangyong','张三','7B7047748C146671550A41036E2333EC1',00000000002,NULL,'2016-10-20','0',0000000006,0000000004,00000000003,00000000000,'372321199999999999','0','山东省东营市',0,'山东汇佳','唱歌舞蹈','中央戏剧表演','健康','2016-10-11 08:24:51',1,'2016-10-04','defaultheadpic.jpg','红年志愿者',0000000001,'8086998','社会兼职','15762188888',' 表演二等奖','希望申请通过','销售实习'),(35,'汇佳软件','张三3','9A42B2FDE562AD64942EE3058498251D1',00000000001,NULL,'2016-10-20','0',0000000001,0000000001,00000000001,00000000000,'372321199999999999','1','山东省东营市',0,'山东汇佳','唱歌舞蹈','艺术简介艺术简介','健康','2016-10-11 08:29:21',1,'2016-10-04','defaultheadpic.jpg','中年志愿者',0000000001,'8086998','社会兼职','15762188888','表演三等奖','希望申请通过','销售实习'),(36,'山东汇佳软件','张三4','3501B4E771BA5C7A744E8B16E8CEF916E1',00000000001,NULL,'2016-10-20','1',0000000001,0000000001,00000000001,00000000000,'372321199999999999','1','山东省东营市',0,'山东汇佳','唱歌舞蹈','艺术简介艺术简介','健康','2016-10-11 15:45:06',1,'2016-10-04','defaultheadpic.jpg','志愿者',0000000001,'8086998','社会兼职','15762188888',' 表演四等奖','希望申请通过','销售实习'),(37,'张三5','张三5','4501195721962DCDADBFC33F8C0DD6E31',00000000001,NULL,'2016-10-20','1',0000000001,0000000001,00000000001,00000000000,'372321199999999999','1','山东省东营市',0,'山东汇佳','唱歌舞蹈',' 中国表演大学','健康','2016-10-12 15:13:12',0,'2016-10-04','c908593ded2e3450cd6656e64b95526c.png','青年志愿',0000000001,'8086998','社会兼职','15762188888',' 表演五等奖','希望申请通过','销售实习'),(38,'庞庞','张三6','9D46A6083C6EBDDCFB898DAB58EAFD7F1',00000000001,NULL,'2016-10-20','0',0000000001,0000000001,00000000001,00000000000,'372321199999999999','1','山东省东营市',0,'山东汇佳','唱歌舞蹈','艺术简介艺术简介','健康','2016-10-13 22:47:39',0,'2016-10-04','defaultheadpic.jpg','青年者远着',0000000001,'8086998','社会兼职','15762188888',' 表演特等奖','希望申请通过','销售实习'),(39,'admin','张三7','6E0F084E2078D7E28093027420CDD7761',00000000001,NULL,'2016-10-20','1',0000000006,0000000004,00000000002,00000000000,'372321199999999999','0','山东省东营市',0,'山东汇佳','唱歌舞蹈',' 中国表演大学','健康','2016-10-14 09:49:17',1,'2016-10-04','defaultheadpic.jpg','志愿者',0000000001,'8086998','社会兼职','15762188888',' 表演奖','希望申请通过','销售实习'),(40,'庞永','张三8','DD015661785A22BDED41F84D206AF94E1',00000000003,NULL,'2016-10-20','0',0000000006,0000000004,00000000003,00000000000,'372321199999999999','0','山东省东营市',0,'山东汇佳','唱歌舞蹈','中央戏剧学院','健康','2016-10-14 09:49:59',1,'2016-10-04','defaultheadpic.jpg','志愿者',0000000001,'8086998','社会兼职','15762188888',' 参与奖','希望申请通过','销售实习'),(41,'hjsoft','李毅','D3D8CB79B6CF54CB37B49E9A33D64F7B1',00000000001,NULL,'2016-10-20','1',0000000001,0000000001,00000000001,00000000000,'372321199999999999','1','山东省滨州市',0,'山东汇佳','开发',' 中国表演大学','健康','2016-10-16 09:24:42',0,'2016-10-04','defaultheadpic.jpg','志愿者',0000000001,'8086998','社会兼职','15762188888',' 参与奖','希望申请通过','销售实习'),(42,'py1','李毅','C8CDCB4D499B4586FD0204CAF0F025501',00000000001,NULL,'2016-10-20','0',0000000001,0000000001,00000000001,00000000000,'372321199999999999','1','山东省滨州市',0,'山东汇佳','开发','艺术简介','健康','2016-10-16 13:55:02',0,'2016-10-04','defaultheadpic.jpg','志愿者',0000000001,'8088098','兼职','15762182222','获奖情况','申请意见','业务成就'),(43,'py2','庞庞','2CEAF68A3D72AC13E036C18334B0EFE61',00000000001,NULL,'2016-10-20','1',0000000001,0000000001,00000000001,00000000000,'372321199999999999','1','山东省滨州市',0,'山东汇佳','开发',' 中国表演大学','健康','2016-10-16 15:13:52',0,'2016-10-04','20160822020450_207883.png','计算机',0000000001,'8088098','兼职','15762182222','获奖情况','申请意见','业务成就'),(47,'huijia1','庞庞','3F88F8754440E99D904120FD9D706EB31',00000000001,NULL,'2016-10-20','1',0000000002,0000000004,00000000001,00000000001,'372321199999999999','2','山东省东营市',0,'山东汇佳','开发','中央戏剧表演','健康','2016-10-19 09:55:21',0,'2016-10-04','2.png','志愿者',0000000001,'8088098','社会兼职','15762188888','获奖情况','申请意见','销售实习'),(48,'huijia3','李慧','E2F866EC03D4BE035CED93CABC4CB1B31',00000000001,NULL,'2016-10-20','1',0000000003,0000000001,00000000001,00000000001,'372321199999999999','3','山东省东营市',0,'山东汇佳','开发','中央戏剧表演','健康','2016-10-19 10:22:20',0,'2016-10-04','21.png','志愿者',0000000001,'8088098','社会兼职','15762188888','获奖情况','申请意见','销售实习'),(49,'huijiaruanjian','李毅','61821777D6C4DCCFE12B17537B4CD67A1',00000000007,NULL,'2016-10-20','1',0000000004,0000000002,00000000001,00000000001,'372321199999999999','4','山东省东营市',0,'山东汇佳','开发','中央戏剧表演','健康','2016-10-19 13:54:05',0,'2016-10-04','1.jpg','志愿者',0000000001,'8088098','社会兼职','15762188888','获奖情况','申请意见','销售实习'),(51,'汇佳软件123','赵六','D3934F91D32DB7D01525110DAA700B021',00000000002,NULL,'2016-10-20','1',0000000005,0000000003,00000000001,00000000001,'372321199999999999','2','山东省东营市',0,'山东汇佳','开发','中央戏剧表演','健康','2016-10-21 14:19:14',0,'2016-10-04','defaultheadpic.jpg','志愿者',0000000001,'8088098','社会兼职','15762188888','获奖情况','申请意见','销售实习'),(56,'test111','王五','6BE59AE979C90B444984AD796D30EEDF1',00000000003,NULL,'2016-10-20','1',0000000006,0000000004,00000000001,00000000001,'372321199999999999','1','山东省东营市',0,'山东汇佳','开发','中央戏剧表演','健康','2016-10-23 21:59:55',1,'2016-10-04','test7.jpg','志愿者',0000000001,'8088098','社会兼职','15762188888','获奖情况','申请意见','销售实习'),(57,'test121','李四','12B406E2D13873E684EAD9E7B4832DC01',00000000004,NULL,'2016-10-20','1',0000000007,0000000003,00000000001,00000000001,'372321199999999999','3','山东省东营市',0,'山东汇佳','开发','中央戏剧表演','健康','2016-10-23 22:08:24',2,'2016-10-04','test8.jpg','志愿者',0000000001,'8088098','社会兼职','15762188888','获奖情况','申请意见','销售实习'),(58,'test122','张三','AE938DA2B3434577647CB4985A7FBCAA1',00000000005,NULL,'2016-10-20','1',0000000011,0000000003,00000000001,00000000001,'372321199999999999','4','山东省东营市',0,'山东汇佳','开发','中央戏剧表演','健康','2016-10-23 22:15:06',3,'2016-10-04','test9.jpg','志愿者',0000000001,'8088098','社会兼职','15762188888','获奖情况','申请意见','销售实习'),(60,'test222','庞庞','6B5ED554EBFF2FC7A0BAB042F4894D6C1',00000000006,NULL,'2016-10-20','0',0000000016,0000000001,00000000001,00000000001,'372321199999999999','2','山东省滨州市',1,'山东汇佳','开发','中央戏剧表演','健康','2016-10-24 09:27:38',1,'2016-10-04','test11.jpg','志愿者',0000000001,'8088098','社会兼职','15762182222','获奖情况','申请意见','销售实习'),(61,'whg_u_test','冯晓','541D8E7669A0D6433CB656564A9CABCA',00000000008,'1busn9y9ac92y1ap9j4khzaej8','2016-10-20','0',0000000001,0000000001,00000000001,00000000001,'372321199999999999','1','山东省东营市',1,'山东汇佳','开发','中央戏剧表演','健康','2016-10-26 17:27:55',0,'2016-10-04','defaultheadpic.jpg','志愿者',0000000001,'8088098','社会兼职','15762182222','获奖情况','申请意见','销售实习'),(64,'pytest',NULL,'C736A8DF053397048AC28B7E4BDBD973',00000000001,'287ssrv804griql9x79wcvyw','2016-10-20','1',0000000001,0000000001,00000000002,00000000002,'372321199999999999','2','山东省滨州市',1,'山东汇佳','开发','中央戏剧表演','健康','2016-10-27 15:06:39',0,'2016-10-04','defaultheadpic.jpg','志愿者',0000000001,'8088098','社会兼职','15762188888','获奖情况','申请意见','销售实习'),(65,'1wqass',NULL,'576161E8E1474F403F0D20D5D5A66C84',00000000001,NULL,'2016-10-20','0',0000000002,0000000001,00000000002,00000000001,'372321199999999999','1','山东省滨州市',1,'山东汇佳','开发','中央戏剧表演','健康','2016-10-27 23:13:00',1,'eport','test1.jpg','志愿者',0000000001,'8088098','社会兼职','15762188888','获奖情况','申请意见','销售实习'),(66,'testtest','李毅','65B1C226260E76882E0710061CD2328F',NULL,NULL,'2016-10-24','0',NULL,NULL,NULL,NULL,'372321199999999999',NULL,'山东省滨州市',NULL,'山东汇佳',NULL,'12333','健康','2016-10-28 14:45:57',1,'2016-10-05','test2.jpg',NULL,NULL,'8088098','12333','15762182222','12333','12333','12333'),(67,'1111111','李毅','83B48D27C60F75238C97CD8E5176FCF5',NULL,NULL,'2016-10-05','0',NULL,NULL,NULL,NULL,'372321199999999999',NULL,'山东省滨州市',NULL,'山东汇佳',NULL,'111111','健康','2016-10-28 14:56:54',1,'2016-10-04','test3.jpg',NULL,NULL,'8088098','111111','15762182222','111111','111111','111111'),(68,'qqq','zhaa','AD365847CC139760C8AF73F2947BE605',NULL,NULL,'2016-10-04','0',NULL,NULL,NULL,NULL,'372321199999999999',NULL,'山东省滨州市',NULL,'山东汇佳',NULL,'11111111111','健康','2016-10-28 15:10:31',1,'2016-10-04','test4.jpg',NULL,NULL,'8088098','11111111111','15762182222','11111111111','11111111111','11111111111');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zzmm`
--

DROP TABLE IF EXISTS `zzmm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zzmm` (
  `zzmm_id` int(11) NOT NULL AUTO_INCREMENT,
  `zzmmname` varchar(20) NOT NULL,
  PRIMARY KEY (`zzmm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zzmm`
--

LOCK TABLES `zzmm` WRITE;
/*!40000 ALTER TABLE `zzmm` DISABLE KEYS */;
INSERT INTO `zzmm` VALUES (1,'中共党员'),(2,' 中共预备党员'),(3,' 共青团员'),(4,' 民革党员'),(5,' 民盟盟员'),(6,' 民建会员'),(7,' 民进会员'),(8,' 农工党党员'),(9,' 致公党党员'),(10,' 九三学社社员'),(11,' 台盟盟员'),(12,' 无党派民族人士'),(13,' 普通公民(群众)');
/*!40000 ALTER TABLE `zzmm` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-28 15:12:42
