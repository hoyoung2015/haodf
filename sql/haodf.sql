/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : haodf

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-11-11 23:15:52
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
  `doc_id` varchar(36) NOT NULL,
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
  PRIMARY KEY (`dui_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `desc` text,
  `want_help` text,
  `hospital` varchar(255) DEFAULT NULL,
  `hos_dept` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`wen_id`),
  KEY `ix_doc_id` (`doc_id`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
