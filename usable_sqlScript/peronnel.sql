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

 Date: 10/10/2018 07:20:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for peronnel
-- ----------------------------
DROP TABLE IF EXISTS `peronnel`;
CREATE TABLE `peronnel`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `soft_delete` bit(1) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `personnel_no` int(11) NULL DEFAULT NULL,
  `surname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tc_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of peronnel
-- ----------------------------
INSERT INTO `peronnel` VALUES (1, '2018-10-10 07:04:16', b'0', NULL, 27, 'Onur', 20181991, 'KELEŞ', '61865418350');
INSERT INTO `peronnel` VALUES (2, '2018-10-10 07:05:00', b'0', NULL, 32, 'Hüseyin', 20182955, 'GELMEZ', '7584521522');
INSERT INTO `peronnel` VALUES (3, '2018-10-10 07:06:26', b'0', NULL, 35, 'Mehmet', 20181290, 'MOZATA', '78545869852');

SET FOREIGN_KEY_CHECKS = 1;
