--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`id`, `username`, `sha_pass_hash`, `gmlevel`, `sessionkey`, `v`, `s`, `email`, `joindate`, `last_ip`, `failed_logins`, `locked`, `last_login`, `active_realm_id`, `expansion`, `mutetime`, `locale`, `os`, `playerBot`) VALUES 
(1,'ADMINISTRATOR','a34b29541b87b7e4823683ce6c7bf6ae68beaaac',3,'','0','0','','2006-04-25 13:18:56','127.0.0.1',0,0,'0000-00-00 00:00:00',0,0,0,0,'','\0'),
(2,'GAMEMASTER','7841e21831d7c6bc0b57fbe7151eb82bd65ea1f9',2,'','0','0','','2006-04-25 13:18:56','127.0.0.1',0,0,'0000-00-00 00:00:00',0,0,0,0,'','\0'),
(3,'MODERATOR','a7f5fbff0b4eec2d6b6e78e38e8312e64d700008',1,'','0','0','','2006-04-25 13:19:35','127.0.0.1',0,0,'0000-00-00 00:00:00',0,0,0,0,'','\0'),
(4,'PLAYER','3ce8a96d17c5ae88a30681024e86279f1a38c041',0,'','0','0','','2006-04-25 13:19:35','127.0.0.1',0,0,'0000-00-00 00:00:00',0,0,0,0,'','\0');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;