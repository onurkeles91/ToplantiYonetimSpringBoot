/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : toplanti

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 10/10/2018 07:12:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for meeting
-- ----------------------------
DROP TABLE IF EXISTS `meeting`;
CREATE TABLE `meeting`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `soft_delete` bit(1) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `meeting_date` datetime(0) NULL DEFAULT NULL,
  `meeting_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of meeting
-- ----------------------------
INSERT INTO `meeting` VALUES (1, '2018-10-10 07:08:12', b'0', NULL, '2018-10-10 07:08:18', 'Toplanti_1');
INSERT INTO `meeting` VALUES (2, '2018-10-10 07:08:43', b'0', NULL, '2018-10-18 07:08:48', 'Toplanti_2');
INSERT INTO `meeting` VALUES (3, '2018-10-10 07:09:00', b'0', NULL, '2018-10-24 07:09:10', 'Toplanti_3');

SET FOREIGN_KEY_CHECKS = 1;
