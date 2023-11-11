-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: localhost    Database: item
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `evaluations`
--

DROP TABLE IF EXISTS `evaluations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluations` (
  `Trade_ID` int NOT NULL,
  `Buyer_Evaluation` varchar(200) DEFAULT NULL,
  `Seller_Evaluation` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`Trade_ID`),
  CONSTRAINT `evaluations_ibfk_1` FOREIGN KEY (`Trade_ID`) REFERENCES `trades` (`Trade_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorites` (
  `Favorite_ID` int NOT NULL AUTO_INCREMENT,
  `User_ID` int NOT NULL,
  `Good_ID` int NOT NULL,
  `Insert_Time` datetime NOT NULL,
  PRIMARY KEY (`Favorite_ID`),
  KEY `User_ID` (`User_ID`),
  KEY `Good_ID` (`Good_ID`),
  CONSTRAINT `favorites_ibfk_1` FOREIGN KEY (`User_ID`) REFERENCES `users` (`User_ID`),
  CONSTRAINT `favorites_ibfk_2` FOREIGN KEY (`Good_ID`) REFERENCES `goods` (`Goods_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `Favorites_insert_trigger` BEFORE INSERT ON `favorites` FOR EACH ROW BEGIN
  DECLARE Favorite_Amount INT;
  
  -- 检查表中是否已有收藏记录
  SELECT COUNT(*) INTO Favorite_Amount FROM Favorites;
  
  -- 如果表为空，将Favorite_ID设为1000000001
  IF Favorite_Amount = 0 THEN
    SET NEW.Favorite_ID = 1000000001;
  END IF;
  
  -- 检查User_ID是否存在于Users中
  IF NOT EXISTS (SELECT 1 FROM Users WHERE User_ID = NEW.User_ID) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'User_ID在Users中不存在';
  END IF;
  
  -- 检查Good_ID是否存在于Goods中
  IF NOT EXISTS (SELECT 1 FROM Goods WHERE Goods_ID = NEW.Good_ID) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Good_ID在Goods中不存在';
  END IF;
  
  -- 设置插入时间为当前时间
  SET NEW.Insert_Time = NOW();
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods` (
  `Goods_ID` int NOT NULL AUTO_INCREMENT,
  `Goods_Name` varchar(20) NOT NULL,
  `Seller_ID` int NOT NULL,
  `Goods_Price` decimal(10,2) NOT NULL,
  `Goods_Description` varchar(100) NOT NULL,
  `Classification` enum('文娱用品','学习用品','生活用品','交通工具','奇奇怪怪') NOT NULL,
  `Release_Time` datetime DEFAULT NULL,
  PRIMARY KEY (`Goods_ID`),
  KEY `Seller_ID` (`Seller_ID`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`Seller_ID`) REFERENCES `users` (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `Goods_insert_trigger` BEFORE INSERT ON `goods` FOR EACH ROW BEGIN
  DECLARE Goods_In_Table INT;

  -- 检查表中是否已有商品
  SELECT COUNT(*) INTO Goods_In_Table FROM Goods;
  
  -- 如果表为空，将Goods_ID设为1000000001
  IF Goods_In_Table = 0 THEN
    SET NEW.Goods_ID = 1000000001;
  END IF;
  
  -- 检查Goods_Name长度是否符合要求
  IF LENGTH(NEW.Goods_Name) < 2 OR LENGTH(NEW.Goods_Name) > 20 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Goods_Name长度不符合要求';
  END IF;
  
  -- 检查Seller_ID是否在Users中存在
  IF NOT EXISTS (SELECT 1 FROM Users WHERE User_ID = NEW.Seller_ID) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Seller_ID在Users中不存在';
  END IF;
  
  -- 检查Goods_Price是否为数字且非负数
  IF NEW.Goods_Price < 0 OR NEW.Goods_Price IS NULL THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Goods_Price必须为非负数';
  END IF;
  
  -- 检查Goods_Description长度是否符合要求
  IF LENGTH(NEW.Goods_Description) < 2 OR LENGTH(NEW.Goods_Description) > 100 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Goods_Description长度不符合要求';
  END IF;
  
  -- 检查Classification是否符合要求
  IF NEW.Classification NOT IN ('文娱用品', '学习用品', '生活用品', '交通工具', '奇奇怪怪') THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Classification不符合要求';
  END IF;
  
  -- 设置Release_Time为当前时间
  SET NEW.Release_Time = NOW();
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `goods_images_conns`
--

DROP TABLE IF EXISTS `goods_images_conns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods_images_conns` (
  `Goods_ID` int NOT NULL,
  `Image_ID` int NOT NULL,
  PRIMARY KEY (`Goods_ID`,`Image_ID`),
  KEY `Image_ID` (`Image_ID`),
  CONSTRAINT `goods_images_conns_ibfk_1` FOREIGN KEY (`Goods_ID`) REFERENCES `goods` (`Goods_ID`),
  CONSTRAINT `goods_images_conns_ibfk_2` FOREIGN KEY (`Image_ID`) REFERENCES `images` (`Image_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `Goods_Images_Conns_insert_trigger` BEFORE INSERT ON `goods_images_conns` FOR EACH ROW BEGIN
  DECLARE goods_count INT;
  DECLARE image_count INT;
  
  -- 检查 Goods_ID 的有效性
  SELECT COUNT(*) INTO goods_count FROM Goods WHERE Goods_ID = NEW.Goods_ID;
  
  -- 检查 Image_ID 的有效性
  SELECT COUNT(*) INTO image_count FROM Images WHERE Image_ID = NEW.Image_ID;
  
  -- 如果 Goods_ID 或 Image_ID 不存在，则阻止插入
  IF goods_count = 0 OR image_count = 0 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Goods_ID 或 Image_ID 不存在';
  END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `historys`
--

DROP TABLE IF EXISTS `historys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historys` (
  `Search_ID` int NOT NULL AUTO_INCREMENT,
  `User_ID` int NOT NULL,
  `Keyword` varchar(10) NOT NULL,
  PRIMARY KEY (`Search_ID`),
  KEY `User_ID` (`User_ID`),
  CONSTRAINT `historys_ibfk_1` FOREIGN KEY (`User_ID`) REFERENCES `users` (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `Historys_insert_trigger` BEFORE INSERT ON `historys` FOR EACH ROW BEGIN
  DECLARE Favorite_Amount INT;
  DECLARE User_Search_History INT;
  
  -- 检查表中是否已有收藏记录
  SELECT COUNT(*) INTO Favorite_Amount FROM Historys;
  
  -- 如果表为空，将Search_ID设为1000000001
  IF Favorite_Amount = 0 THEN
    SET NEW.Search_ID = 1000000001;
  END IF;
  
  -- 检查相同User_ID的记录数量
  SELECT COUNT(*) INTO User_Search_History FROM Historys WHERE User_ID = NEW.User_ID;
  
  -- 如果数量等于十条，删除Search_ID最小的一条记录
  IF User_Search_History = 10 THEN
    DELETE FROM Historys WHERE Search_ID = (SELECT MIN(Search_ID) FROM Historys WHERE User_ID = NEW.User_ID);
  END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `Image_ID` int NOT NULL AUTO_INCREMENT,
  `Image_Name` varchar(255) DEFAULT NULL,
  `Image_Data` varchar(255) DEFAULT NULL,
  `Image_Class` enum('用户','商品','平台','其他') DEFAULT NULL,
  PRIMARY KEY (`Image_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `Images_insert_trigger` BEFORE INSERT ON `images` FOR EACH ROW BEGIN
  -- 检查 Image_Class 的值是否在合法范围内，不合法则设置为其他
  IF NEW.Image_Class NOT IN ('用户', '商品', '平台', '其他') THEN
    SET NEW.Image_Class = '其他';
  END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `Message_ID` int NOT NULL AUTO_INCREMENT,
  `Sender_ID` int NOT NULL,
  `Receiver_ID` int NOT NULL,
  `Message_Content` varchar(200) NOT NULL,
  `Time` datetime NOT NULL,
  `IsRead` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`Message_ID`),
  KEY `Sender_ID` (`Sender_ID`),
  KEY `Receiver_ID` (`Receiver_ID`),
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`Sender_ID`) REFERENCES `users` (`User_ID`),
  CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`Receiver_ID`) REFERENCES `users` (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `Messages_insert_trigger` BEFORE INSERT ON `messages` FOR EACH ROW BEGIN
  DECLARE 消息数 INT;
  
  -- 检查表中是否已有消息记录
  SELECT COUNT(*) INTO 消息数 FROM Messages;
  
  -- 如果表为空，将Message_ID设为1000000001
  IF 消息数 = 0 THEN
    SET NEW.Message_ID = 1000000001;
  END IF;
  
  -- 检查Sender_ID是否存在于Users中
  IF NOT EXISTS (SELECT 1 FROM Users WHERE User_ID = NEW.Sender_ID) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Sender_ID在Users中不存在';
  END IF;
  
  -- 检查Receiver_ID是否存在于Users中
  IF NOT EXISTS (SELECT 1 FROM Users WHERE User_ID = NEW.Receiver_ID) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Receiver_ID在Users中不存在';
  END IF;
  
  -- 检查Message_Content是否超过200个字符
  IF LENGTH(NEW.Message_Content) > 200 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Message_Content超过200个字符';
  END IF;
  
  -- 设置发送时间为当前时间
  SET NEW.Time = NOW();
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `trades`
--

DROP TABLE IF EXISTS `trades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trades` (
  `Trade_ID` int NOT NULL AUTO_INCREMENT,
  `Buyer_ID` int NOT NULL,
  `Seller_ID` int NOT NULL,
  `Trade_Time` datetime NOT NULL,
  `Paid_Amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`Trade_ID`),
  KEY `Buyer_ID` (`Buyer_ID`),
  KEY `Seller_ID` (`Seller_ID`),
  CONSTRAINT `trades_ibfk_1` FOREIGN KEY (`Buyer_ID`) REFERENCES `users` (`User_ID`),
  CONSTRAINT `trades_ibfk_2` FOREIGN KEY (`Seller_ID`) REFERENCES `users` (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `Trades_insert_trigger` BEFORE INSERT ON `trades` FOR EACH ROW BEGIN
  DECLARE 交易数 INT;
  
  -- 检查表中是否已有交易记录
  SELECT COUNT(*) INTO 交易数 FROM Trades;
  
  -- 如果表为空，将Trade_ID设为1000000001
  IF 交易数 = 0 THEN
    SET NEW.Trade_ID = 1000000001;
  END IF;
  
  -- 检查买家ID和卖家ID是否存在以及是否相同
  IF NOT EXISTS (SELECT 1 FROM Users WHERE User_ID = NEW.Buyer_ID) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = '买家ID在Users中不存在';
  END IF;
  
  IF NOT EXISTS (SELECT 1 FROM Users WHERE User_ID = NEW.Seller_ID) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = '卖家ID在Users中不存在';
  END IF;
  
  IF NEW.Buyer_ID = NEW.Seller_ID THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = '买家ID和卖家ID不能相同';
  END IF;
  
  -- 检查Paid_Amount是否为数字且非负数
  IF NEW.Paid_Amount < 0 OR NEW.Paid_Amount IS NULL THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Paid_Amount必须为非负数';
  END IF;
  
  -- 设置交易时间为当前时间
  SET NEW.Trade_Time = NOW();
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `User_ID` int NOT NULL AUTO_INCREMENT,
  `User_Name` varchar(10) NOT NULL,
  `User_Key` varchar(18) NOT NULL,
  `FZU_Key` char(9) DEFAULT NULL,
  `Head_Portrait` int DEFAULT NULL,
  `Delivery_Address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`User_ID`),
  UNIQUE KEY `User_Name` (`User_Name`),
  UNIQUE KEY `FZU_Key` (`FZU_Key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `Users_insert_trigger` BEFORE INSERT ON `users` FOR EACH ROW BEGIN
  DECLARE Users_In_Table INT;

  -- 检查表中是否已有元组
  SELECT COUNT(*) INTO Users_In_Table FROM Users;
  
  -- 如果表为空，将User_ID设为100000001
  IF Users_In_Table = 0 THEN
    SET NEW.User_ID = 100000001;
  END IF;
  
  -- 检查User_Name长度是否符合要求
  IF LENGTH(NEW.User_Name) < 2 OR LENGTH(NEW.User_Name) > 10 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'User_Name长度不符合要求';
  END IF;
  
  -- 检查User_Name是否已存在
  IF EXISTS (SELECT 1 FROM Users WHERE User_Name = NEW.User_Name) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'User_Name已存在';
  END IF;
  
  -- 检查User_Key长度是否符合要求
  IF LENGTH(NEW.User_Key) < 6 OR LENGTH(NEW.User_Key) > 18 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'User_Key长度不符合要求';
  END IF;
  
  -- 检查FZU_Key是否为9个数字字符
  IF LENGTH(NEW.FZU_Key) <> 9 OR NEW.FZU_Key REGEXP '[^0-9]' THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'FZU_Key格式不正确';
  END IF;
  
  -- 检查FZU_Key是否已存在
  IF EXISTS (SELECT 1 FROM Users WHERE FZU_Key = NEW.FZU_Key) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'FZU_Key已存在';
  END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping routines for database 'item'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-11 17:56:49
