/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : haodf

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-11-14 21:14:28
*/

SET FOREIGN_KEY_CHECKS=0;

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
  `doc_title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
