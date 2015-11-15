/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : haodf

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-11-15 23:51:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `area_id` char(32) NOT NULL,
  `area_name` varchar(125) DEFAULT NULL,
  `url` varchar(125) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for consult_cat
-- ----------------------------
DROP TABLE IF EXISTS `consult_cat`;
CREATE TABLE `consult_cat` (
  `con_cat_id` varchar(125) NOT NULL,
  `con_cat_name` varchar(145) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `super_cat_name` varchar(145) DEFAULT NULL,
  PRIMARY KEY (`con_cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for disease
-- ----------------------------
DROP TABLE IF EXISTS `disease`;
CREATE TABLE `disease` (
  `dis_id` varchar(36) NOT NULL,
  `dis_name` varchar(145) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `cat_id` varchar(45) DEFAULT NULL,
  `cat_name` varchar(145) DEFAULT NULL,
  `dis_type` int(1) DEFAULT NULL,
  PRIMARY KEY (`dis_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for disease_cat
-- ----------------------------
DROP TABLE IF EXISTS `disease_cat`;
CREATE TABLE `disease_cat` (
  `cat_id` varchar(36) NOT NULL,
  `parent_id` varchar(36) DEFAULT NULL,
  `cat_name` varchar(145) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `doc_id` varchar(45) NOT NULL,
  `doc_name` varchar(45) DEFAULT NULL,
  `home_url` varchar(255) DEFAULT NULL,
  `info_url` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `hos_id` varchar(255) DEFAULT NULL,
  `hos_name` varchar(255) DEFAULT NULL,
  `hos_url` varchar(255) DEFAULT NULL,
  `dept_id` varchar(255) DEFAULT NULL,
  `dept_name` varchar(255) DEFAULT NULL,
  `dept_url` varchar(255) DEFAULT NULL,
  `total_view` int(255) DEFAULT NULL,
  `total_article` int(255) DEFAULT NULL,
  `total_patient` int(255) DEFAULT NULL,
  `wexin_baodao` int(255) DEFAULT NULL,
  `total_baodao` int(255) DEFAULT NULL,
  `total_vote` int(255) DEFAULT NULL,
  `total_thank_letter` int(255) DEFAULT NULL,
  `total_gift` int(255) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `doc_title` varchar(255) DEFAULT NULL,
  KEY `ix_hos_id` (`hos_id`),
  KEY `ix_hos_name` (`hos_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for docto_tmp
-- ----------------------------
DROP TABLE IF EXISTS `docto_tmp`;
CREATE TABLE `docto_tmp` (
  `doc_id` varchar(45) NOT NULL,
  `doc_name` varchar(45) DEFAULT NULL,
  `home_url` varchar(255) DEFAULT NULL,
  `info_url` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`doc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for duihua
-- ----------------------------
DROP TABLE IF EXISTS `duihua`;
CREATE TABLE `duihua` (
  `dui_id` int(11) NOT NULL AUTO_INCREMENT,
  `wen_id` varchar(145) DEFAULT NULL,
  `dui_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `doc_home_url` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `doc_id` varchar(145) DEFAULT NULL,
  `pat_id` varchar(145) DEFAULT NULL,
  `content` text,
  `dui_type` varchar(255) DEFAULT NULL COMMENT '文本,\r\n报到',
  PRIMARY KEY (`dui_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hospital
-- ----------------------------
DROP TABLE IF EXISTS `hospital`;
CREATE TABLE `hospital` (
  `hos_id` char(120) NOT NULL,
  `hos_name` varchar(125) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `level` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`hos_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for section
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `sec_id` varchar(36) NOT NULL,
  `sec_name` varchar(145) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`sec_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wenda
-- ----------------------------
DROP TABLE IF EXISTS `wenda`;
CREATE TABLE `wenda` (
  `wen_id` varchar(145) NOT NULL,
  `wen_name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `con_cat_name` varchar(145) DEFAULT NULL,
  `doc_id` varchar(145) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `disease` varchar(255) DEFAULT NULL,
  `description` text,
  `want_help` text,
  `hospital` varchar(255) DEFAULT NULL,
  `hos_dept` varchar(255) DEFAULT NULL,
  `drug` text,
  `zhiliao` text,
  `con_cat_name2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`wen_id`),
  KEY `ix_doc_id` (`doc_id`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zixun
-- ----------------------------
DROP TABLE IF EXISTS `zixun`;
CREATE TABLE `zixun` (
  `zixun_id` varchar(255) NOT NULL,
  `doc_id` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `pat_id` varchar(255) DEFAULT NULL,
  `post_time` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `jibing` varchar(255) DEFAULT NULL,
  `total_chat_num` int(11) DEFAULT NULL,
  `doc_chat_num` int(11) DEFAULT NULL,
  `pat_chat_num` int(11) DEFAULT NULL,
  `gift_flag` int(1) DEFAULT '0',
  `hot_gift_flag` int(1) DEFAULT '0',
  `pay_flag` int(1) DEFAULT '0',
  `ext1` varchar(255) DEFAULT NULL,
  `ext2` varchar(255) DEFAULT NULL,
  `ext3` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `tell_flag` int(1) DEFAULT '0',
  PRIMARY KEY (`zixun_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
