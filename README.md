# software_project

dev08：完成了数据库数据加密等功能，进行了已知bug的修复

CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `phonenumber` varchar(11) COLLATE utf8_unicode_ci NOT NULL COMMENT '手机号',
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`userId`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE COMMENT '用户名唯一',
  UNIQUE KEY `phonenumber` (`phonenumber`) USING BTREE COMMENT '手机号码唯一'
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci

CREATE TABLE `product` (
  `productName` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '名称',
  `productImage` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '图片',
  `productInformation` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '描述',
  `productType` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '类型',
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '唯一标识符',
  `userId` int(255) NOT NULL,
  `productTimeStamp` datetime DEFAULT NULL COMMENT '时间戳',
  `location` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `permission` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
