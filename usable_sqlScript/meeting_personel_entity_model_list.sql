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

 Date: 10/10/2018 07:19:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for meeting_personel_entity_model_list
-- ----------------------------
DROP TABLE IF EXISTS `meeting_personel_entity_model_list`;
CREATE TABLE `meeting_personel_entity_model_list`  (
  `meeting_entity_model_id` bigint(20) NOT NULL,
  `personel_entity_model_list_id` bigint(20) NOT NULL,
  INDEX `FKrcwq268pxi9ha7rdlm59kcqg3`(`personel_entity_model_list_id`) USING BTREE,
  INDEX `FKkvd3i6y09wba793w42yju5538`(`meeting_entity_model_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of meeting_personel_entity_model_list
-- ----------------------------
INSERT INTO `meeting_personel_entity_model_list` VALUES (1, 1);
INSERT INTO `meeting_personel_entity_model_list` VALUES (1, 3);
INSERT INTO `meeting_personel_entity_model_list` VALUES (2, 2);
INSERT INTO `meeting_personel_entity_model_list` VALUES (3, 1);
INSERT INTO `meeting_personel_entity_model_list` VALUES (3, 2);
INSERT INTO `meeting_personel_entity_model_list` VALUES (3, 3);

SET FOREIGN_KEY_CHECKS = 1;
