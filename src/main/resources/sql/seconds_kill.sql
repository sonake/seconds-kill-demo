/*
Navicat MariaDB Data Transfer

Source Server         : localhost
Source Server Version : 100214
Source Host           : localhost:3308
Source Database       : seconds_kill

Target Server Type    : MariaDB
Target Server Version : 100214
File Encoding         : 65001

Date: 2019-12-26 18:30:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_name` varchar(255) NOT NULL COMMENT '商品名称',
  `store` int(11) NOT NULL COMMENT '价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', 'watch', '-2');
INSERT INTO `goods` VALUES ('2', 'pencil', '150');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_name` varchar(255) NOT NULL,
  `order_user` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36956 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('36911', 'watch', 'z5');
INSERT INTO `orders` VALUES ('36912', 'watch', 'z4');
INSERT INTO `orders` VALUES ('36913', 'watch', 'z9');
INSERT INTO `orders` VALUES ('36914', 'watch', 'z12');
INSERT INTO `orders` VALUES ('36915', 'watch', 'z13');
INSERT INTO `orders` VALUES ('36916', 'watch', 'z2');
INSERT INTO `orders` VALUES ('36917', 'watch', 'z10');
INSERT INTO `orders` VALUES ('36918', 'watch', 'z6');
INSERT INTO `orders` VALUES ('36919', 'watch', 'z3');
INSERT INTO `orders` VALUES ('36920', 'watch', 'z15');
INSERT INTO `orders` VALUES ('36921', 'watch', 'z11');
INSERT INTO `orders` VALUES ('36922', 'watch', 'z7');
INSERT INTO `orders` VALUES ('36923', 'watch', 'z1');
INSERT INTO `orders` VALUES ('36924', 'watch', 'z8');
INSERT INTO `orders` VALUES ('36925', 'watch', 'z14');
INSERT INTO `orders` VALUES ('36926', 'watch', 'z6');
INSERT INTO `orders` VALUES ('36927', 'watch', 'z9');
INSERT INTO `orders` VALUES ('36928', 'watch', 'z4');
INSERT INTO `orders` VALUES ('36929', 'watch', 'z5');
INSERT INTO `orders` VALUES ('36930', 'watch', 'z2');
INSERT INTO `orders` VALUES ('36931', 'watch', 'z11');
INSERT INTO `orders` VALUES ('36932', 'watch', 'z3');
INSERT INTO `orders` VALUES ('36933', 'watch', 'z8');
INSERT INTO `orders` VALUES ('36934', 'watch', 'z1');
INSERT INTO `orders` VALUES ('36935', 'watch', 'z10');
INSERT INTO `orders` VALUES ('36936', 'watch', 'z7');
INSERT INTO `orders` VALUES ('36937', 'watch', 'z15');
INSERT INTO `orders` VALUES ('36938', 'watch', 'z12');
INSERT INTO `orders` VALUES ('36939', 'watch', 'z14');
INSERT INTO `orders` VALUES ('36940', 'watch', 'z13');
INSERT INTO `orders` VALUES ('36941', 'watch', 'z9');
INSERT INTO `orders` VALUES ('36942', 'watch', 'z12');
INSERT INTO `orders` VALUES ('36943', 'watch', 'z6');
INSERT INTO `orders` VALUES ('36944', 'watch', 'z4');
INSERT INTO `orders` VALUES ('36945', 'watch', 'z11');
INSERT INTO `orders` VALUES ('36946', 'watch', 'z3');
INSERT INTO `orders` VALUES ('36947', 'watch', 'z10');
INSERT INTO `orders` VALUES ('36948', 'watch', 'z13');
INSERT INTO `orders` VALUES ('36949', 'watch', 'z2');
INSERT INTO `orders` VALUES ('36950', 'watch', 'z7');
INSERT INTO `orders` VALUES ('36951', 'watch', 'z1');
INSERT INTO `orders` VALUES ('36952', 'watch', 'z5');
INSERT INTO `orders` VALUES ('36953', 'watch', 'z14');
INSERT INTO `orders` VALUES ('36954', 'watch', 'z15');
INSERT INTO `orders` VALUES ('36955', 'watch', 'z8');
