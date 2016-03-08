/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : paper

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-03-07 09:39:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for application
-- ----------------------------
DROP TABLE IF EXISTS `application`;
CREATE TABLE `application` (
  `Qno` int(10) NOT NULL,
  `Question` text NOT NULL,
  `Answer` text NOT NULL,
  `Difficulty` int(10) NOT NULL,
  PRIMARY KEY (`Qno`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of application
-- ----------------------------
INSERT INTO `application` VALUES ('51', '为什么1+1=?', '2', '5');
INSERT INTO `application` VALUES ('53', '从前有只鹿，它越跑越快，最后它变成了什么？', '高速公鹿。。', '1');
INSERT INTO `application` VALUES ('54', '11', '11', '1');

-- ----------------------------
-- Table structure for blank
-- ----------------------------
DROP TABLE IF EXISTS `blank`;
CREATE TABLE `blank` (
  `Qno` int(10) NOT NULL,
  `Question` text NOT NULL,
  `Answer` tinytext NOT NULL,
  `Difficulty` int(10) NOT NULL,
  PRIMARY KEY (`Qno`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of blank
-- ----------------------------
INSERT INTO `blank` VALUES ('21', '1+4=?', '5', '1');
INSERT INTO `blank` VALUES ('22', '1+5=?', '6', '1');
INSERT INTO `blank` VALUES ('23', '多少分', '这', '2');
INSERT INTO `blank` VALUES ('24', '      123', 'dgf', '2');
INSERT INTO `blank` VALUES ('26', '11', '22', '1');
INSERT INTO `blank` VALUES ('28', '9+9=?', '18', '1');

-- ----------------------------
-- Table structure for brief
-- ----------------------------
DROP TABLE IF EXISTS `brief`;
CREATE TABLE `brief` (
  `Qno` int(10) NOT NULL,
  `Question` text NOT NULL,
  `Answer` text NOT NULL,
  `Difficulty` int(10) NOT NULL,
  PRIMARY KEY (`Qno`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of brief
-- ----------------------------
INSERT INTO `brief` VALUES ('41', 'why1+1=2?', 'I do not know', '5');
INSERT INTO `brief` VALUES ('42', 'why1+2=3?', 'I do not know', '1');
INSERT INTO `brief` VALUES ('43', 'why1+3=4?', 'I do not know', '4');
INSERT INTO `brief` VALUES ('44', 'njknj', 'sretr', '1');
INSERT INTO `brief` VALUES ('45', '222', '111', '1');

-- ----------------------------
-- Table structure for judge
-- ----------------------------
DROP TABLE IF EXISTS `judge`;
CREATE TABLE `judge` (
  `Qno` int(10) NOT NULL,
  `Question` text NOT NULL,
  `Answer` tinytext NOT NULL,
  `Difficulty` int(10) NOT NULL,
  PRIMARY KEY (`Qno`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of judge
-- ----------------------------
INSERT INTO `judge` VALUES ('31', '1+1=3', 'NO', '1');
INSERT INTO `judge` VALUES ('32', '2+2=4', 'YES', '2');
INSERT INTO `judge` VALUES ('33', '2+3=6', 'NO', '3');

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `Pno` int(10) NOT NULL,
  `Qno` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of paper
-- ----------------------------
INSERT INTO `paper` VALUES ('3', '13');
INSERT INTO `paper` VALUES ('3', '11');
INSERT INTO `paper` VALUES ('3', '12');
INSERT INTO `paper` VALUES ('5', '12');
INSERT INTO `paper` VALUES ('5', '14');
INSERT INTO `paper` VALUES ('5', '11');
INSERT INTO `paper` VALUES ('5', '21');
INSERT INTO `paper` VALUES ('5', '32');
INSERT INTO `paper` VALUES ('5', '42');
INSERT INTO `paper` VALUES ('5', '53');
INSERT INTO `paper` VALUES ('2', '14');
INSERT INTO `paper` VALUES ('2', '11');
INSERT INTO `paper` VALUES ('2', '32');
INSERT INTO `paper` VALUES ('2', '33');
INSERT INTO `paper` VALUES ('2', '51');
INSERT INTO `paper` VALUES ('3', '14');
INSERT INTO `paper` VALUES ('3', '11');
INSERT INTO `paper` VALUES ('3', '12');
INSERT INTO `paper` VALUES ('3', '44');
INSERT INTO `paper` VALUES ('3', '53');
INSERT INTO `paper` VALUES ('3', '54');
INSERT INTO `paper` VALUES ('7', '15');
INSERT INTO `paper` VALUES ('7', '31');
INSERT INTO `paper` VALUES ('7', '33');
INSERT INTO `paper` VALUES ('7', '51');
INSERT INTO `paper` VALUES ('1', '13');
INSERT INTO `paper` VALUES ('1', '14');
INSERT INTO `paper` VALUES ('1', '16');
INSERT INTO `paper` VALUES ('1', '21');
INSERT INTO `paper` VALUES ('1', '32');
INSERT INTO `paper` VALUES ('1', '31');
INSERT INTO `paper` VALUES ('1', '41');
INSERT INTO `paper` VALUES ('1', '43');
INSERT INTO `paper` VALUES ('1', '54');
INSERT INTO `paper` VALUES ('1', '51');
INSERT INTO `paper` VALUES ('12', '12');
INSERT INTO `paper` VALUES ('12', '11');
INSERT INTO `paper` VALUES ('122212', '12');
INSERT INTO `paper` VALUES ('122212', '11');
INSERT INTO `paper` VALUES ('122212', '21');
INSERT INTO `paper` VALUES ('122212', '26');
INSERT INTO `paper` VALUES ('12311', '15');
INSERT INTO `paper` VALUES ('12311', '14');
INSERT INTO `paper` VALUES ('12311', '28');
INSERT INTO `paper` VALUES ('12311', '23');
INSERT INTO `paper` VALUES ('12311', '22');
INSERT INTO `paper` VALUES ('999', '16');
INSERT INTO `paper` VALUES ('999', '19');
INSERT INTO `paper` VALUES ('999', '12');
INSERT INTO `paper` VALUES ('999', '21');
INSERT INTO `paper` VALUES ('999', '23');
INSERT INTO `paper` VALUES ('999', '22');

-- ----------------------------
-- Table structure for paperinfo
-- ----------------------------
DROP TABLE IF EXISTS `paperinfo`;
CREATE TABLE `paperinfo` (
  `Pno` int(10) NOT NULL,
  `Qtype` int(10) NOT NULL,
  `score` int(10) NOT NULL,
  `quantity` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of paperinfo
-- ----------------------------
INSERT INTO `paperinfo` VALUES ('3', '1', '1', '3');
INSERT INTO `paperinfo` VALUES ('5', '1', '1', '3');
INSERT INTO `paperinfo` VALUES ('5', '2', '2', '1');
INSERT INTO `paperinfo` VALUES ('5', '3', '1', '1');
INSERT INTO `paperinfo` VALUES ('5', '4', '1', '1');
INSERT INTO `paperinfo` VALUES ('5', '5', '5', '1');
INSERT INTO `paperinfo` VALUES ('2', '1', '1', '2');
INSERT INTO `paperinfo` VALUES ('2', '3', '2', '2');
INSERT INTO `paperinfo` VALUES ('2', '5', '10', '1');
INSERT INTO `paperinfo` VALUES ('3', '1', '2', '3');
INSERT INTO `paperinfo` VALUES ('3', '4', '5', '1');
INSERT INTO `paperinfo` VALUES ('3', '5', '10', '2');
INSERT INTO `paperinfo` VALUES ('7', '1', '2', '1');
INSERT INTO `paperinfo` VALUES ('7', '3', '1', '2');
INSERT INTO `paperinfo` VALUES ('7', '5', '5', '1');
INSERT INTO `paperinfo` VALUES ('1', '1', '5', '3');
INSERT INTO `paperinfo` VALUES ('1', '2', '2', '1');
INSERT INTO `paperinfo` VALUES ('1', '3', '2', '2');
INSERT INTO `paperinfo` VALUES ('1', '4', '2', '2');
INSERT INTO `paperinfo` VALUES ('1', '5', '22', '2');
INSERT INTO `paperinfo` VALUES ('12', '1', '3', '2');
INSERT INTO `paperinfo` VALUES ('122212', '1', '2', '2');
INSERT INTO `paperinfo` VALUES ('122212', '2', '2', '2');
INSERT INTO `paperinfo` VALUES ('12311', '1', '5', '2');
INSERT INTO `paperinfo` VALUES ('12311', '2', '10', '3');
INSERT INTO `paperinfo` VALUES ('999', '1', '5', '3');
INSERT INTO `paperinfo` VALUES ('999', '2', '5', '3');

-- ----------------------------
-- Table structure for selection
-- ----------------------------
DROP TABLE IF EXISTS `selection`;
CREATE TABLE `selection` (
  `Qno` int(10) NOT NULL,
  `Question` text NOT NULL,
  `ItemA` tinytext NOT NULL,
  `ItemB` tinytext NOT NULL,
  `ItemC` tinytext NOT NULL,
  `ItemD` tinytext NOT NULL,
  `Answer` varchar(10) NOT NULL,
  `Difficulty` int(10) NOT NULL,
  PRIMARY KEY (`Qno`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of selection
-- ----------------------------
INSERT INTO `selection` VALUES ('11', '1+2=?', '6', '2', '3', '4', 'C', '1');
INSERT INTO `selection` VALUES ('12', '2', 's', 'df', 'g', 'h', 'B', '1');
INSERT INTO `selection` VALUES ('13', '2+2=?', '1', '2', '3', '4', 'D', '1');
INSERT INTO `selection` VALUES ('14', '2+3=?', '1', '3', '5', '7', 'C', '2');
INSERT INTO `selection` VALUES ('15', '为什么1加1等于2', 'rf', 'wef', 'we', 'ergt', 'C', '1');
INSERT INTO `selection` VALUES ('16', '123123213123', '12', '12', '1', '2', 'A', '1');
INSERT INTO `selection` VALUES ('19', '1231312313', '12', '12', '12', '11', 'A', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userID` varchar(20) NOT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('root', '管理员', '123');
