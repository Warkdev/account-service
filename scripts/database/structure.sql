-- MySQL dump 10.13  Distrib 5.5.37, for Win32 (x86)
--
-- Host: localhost    Database: realmd
-- ------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@SESSION.TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE  IF NOT EXISTS `auth` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `auth`;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'The unique account ID.',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT 'The account user name.',
  `sha_pass_hash` varchar(40) NOT NULL DEFAULT '' COMMENT 'This field contains the encrypted SHA1 password.',
  `gmlevel` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'The account security level.',
  `sessionkey` longtext COMMENT 'The Session Key.',
  `v` longtext COMMENT 'The validated Hash Value.',
  `s` longtext COMMENT 'Password ''Salt'' Value.',
  `email` text COMMENT 'The e-mail address associated with this account.',
  `joindate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'The date when the account was created.',
  `last_ip` varchar(30) NOT NULL DEFAULT '0.0.0.0' COMMENT 'The last IP used by the person who last logged into the account.',
  `failed_logins` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'The number of failed logins attempted on the account.',
  `locked` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'Indicates whether the account has been locked or not.',
  `last_login` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'The date when the account was last logged into.',
  `active_realm_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'Which maximum expansion content a user has access to.',
  `expansion` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'Which maximum expansion content a user has access to.',
  `mutetime` bigint(40) unsigned NOT NULL DEFAULT '0' COMMENT 'The time, in Unix time, when the account will be unmuted.',
  `locale` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'The locale used by the client logged into this account.',
  `os` varchar(3) DEFAULT '' COMMENT 'The Operating System of the connected client',
  `playerBot` bit(1) NOT NULL DEFAULT b'0' COMMENT 'Determines whether the account is a User or a PlayerBot',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  KEY `idx_gmlevel` (`gmlevel`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Account System';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_banned`
--

DROP TABLE IF EXISTS `account_banned`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_banned` (
  `id` int(11) unsigned NOT NULL COMMENT 'The account ID (See account.id).',
  `bandate` bigint(40) NOT NULL DEFAULT '0' COMMENT 'The date when the account was banned, in Unix time.',
  `unbandate` bigint(40) NOT NULL DEFAULT '0' COMMENT 'The date when the account will be automatically unbanned.',
  `bannedby` varchar(50) NOT NULL COMMENT 'The character that banned the account.',
  `banreason` varchar(255) NOT NULL COMMENT 'The reason for the ban.',
  `active` tinyint(4) NOT NULL DEFAULT '1' COMMENT 'Is the ban is currently active or not.',
  PRIMARY KEY (`id`,`bandate`),
  CONSTRAINT `account_banned_ibfk_1` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Ban List';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_banned`
--

LOCK TABLES `account_banned` WRITE;
/*!40000 ALTER TABLE `account_banned` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_banned` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `ip_banned`
--

DROP TABLE IF EXISTS `ip_banned`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_banned` (
  `ip` varchar(32) NOT NULL DEFAULT '0.0.0.0' COMMENT 'The IP address that is banned.',
  `bandate` bigint(40) NOT NULL COMMENT 'The date when the IP was first banned, in Unix time.',
  `unbandate` bigint(40) NOT NULL COMMENT 'The date when the IP will be unbanned in Unix time.',
  `bannedby` varchar(50) NOT NULL DEFAULT '[Console]' COMMENT 'The name of the character that banned the IP.',
  `banreason` varchar(255) NOT NULL DEFAULT 'no reason' COMMENT 'The reason given for the IP ban.',
  PRIMARY KEY (`ip`,`bandate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Banned IPs';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_banned`
--

LOCK TABLES `ip_banned` WRITE;
/*!40000 ALTER TABLE `ip_banned` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_banned` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warden_log`
--

DROP TABLE IF EXISTS `warden_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warden_log` (
  `entry` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Log entry ID',
  `check` smallint(5) unsigned NOT NULL COMMENT 'Failed Warden check ID',
  `action` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'Action taken (enum WardenActions)',
  `account` int(11) unsigned NOT NULL COMMENT 'The account ID of the player.',
  `guid` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'Player GUID',
  `map` int(11) unsigned DEFAULT NULL COMMENT 'The map id. (See map.dbc)',
  `position_x` float DEFAULT NULL COMMENT 'The x location of the player.',
  `position_y` float DEFAULT NULL COMMENT 'The y location of the player.',
  `position_z` float DEFAULT NULL COMMENT 'The z location of the player.',
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'The date/time when the log entry was raised, in Unix time.',
  PRIMARY KEY (`entry`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Warden log of failed checks';
/*!40101 SET character_set_client = @saved_cs_client */;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
