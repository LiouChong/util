/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50712
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50712
 File Encoding         : 65001

 Date: 26/12/2018 18:59:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for asset_vulnerability_rel
-- ----------------------------
DROP TABLE IF EXISTS `asset_vulnerability_rel`;
CREATE TABLE `asset_vulnerability_rel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vul_id` int(11) NOT NULL COMMENT '漏洞Id',
  `vul_no` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '漏洞的编号',
  `vul_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '漏洞的名称',
  `vul_type` tinyint(4) NOT NULL COMMENT '漏洞的类型，1 信息泄露等',
  `asset_id` int(11) NOT NULL COMMENT '资产设备的Id',
  `asset_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资产设备的名称',
  `ip` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资产设备的IP地址',
  `repair_status` tinyint(4) DEFAULT 2 COMMENT '资产漏洞的修复情况，如：1已修复 2未修复\r\n            ',
  `ignore_status` tinyint(4) DEFAULT 2 COMMENT '资产漏洞的忽略情况，如：1已忽略 2未忽略',
  `create_user` int(11) DEFAULT 0 COMMENT '创建人ID',
  `modified_user` int(11) DEFAULT 0 COMMENT '更改人ID',
  `gmt_create` bigint(20) DEFAULT 0 COMMENT '创建时间',
  `gmt_modified` bigint(20) DEFAULT 0 COMMENT '更改时间',
  `state` tinyint(4) NOT NULL DEFAULT 1 COMMENT '此条数据的状态：1 有效 0 无效\r\n            ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for patch
-- ----------------------------
DROP TABLE IF EXISTS `patch`;
CREATE TABLE `patch`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `patch_no` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '补丁的编号',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '补丁的名称',
  `os_name` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '补丁适用的系统名称',
  `apt` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT 'APT的标签',
  `grade` tinyint(4) NOT NULL COMMENT '补丁的等级，1 重要 2 中等 3 严重\r\n            ',
  `release_time` bigint(20) NOT NULL COMMENT '补丁的发布时间',
  `summary` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '补丁的摘要',
  `pre_no` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '前置补丁的编号',
  `reference` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '补丁链接',
  `source` tinyint(4) DEFAULT 1 COMMENT '来源，如 1 软件厂商 2 自主研发',
  `create_user` int(11) DEFAULT 0 COMMENT '创建人ID',
  `modified_user` int(11) DEFAULT 0 COMMENT '更改人ID',
  `gmt_create` bigint(20) DEFAULT 0 COMMENT '创建时间',
  `gmt_modified` bigint(20) DEFAULT 0 COMMENT '更改时间',
  `state` tinyint(4) NOT NULL DEFAULT 1 COMMENT '此条数据的状态：1 有效 0 无效\r\n            ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for patch_file
-- ----------------------------
DROP TABLE IF EXISTS `patch_file`;
CREATE TABLE `patch_file`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `patch_no` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '补丁编号',
  `os_bit` tinyint(4) DEFAULT 2 COMMENT '操作系统架构，如：1  32位 2  64位\r\n            ',
  `file_size` int(11) NOT NULL COMMENT '文件大小',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '补丁实体的MD5',
  `file_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '补丁实体文件的地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patch_file
-- ----------------------------
INSERT INTO `patch_file` VALUES (1, '123asf', 2, 2014, 'asfas', 'aasccas');
INSERT INTO `patch_file` VALUES (2, '1894291asd', 2, 123, 'assve', 'as');
INSERT INTO `patch_file` VALUES (3, '123', 2, 12, '', '1d2');
INSERT INTO `patch_file` VALUES (4, 'KCASD', 1, 201, 'asca13', 'http');
INSERT INTO `patch_file` VALUES (5, '1234', 1, 201, 'asca13', 'http');

-- ----------------------------
-- Table structure for vulnerability
-- ----------------------------
DROP TABLE IF EXISTS `vulnerability`;
CREATE TABLE `vulnerability`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vul_no` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '漏洞编号',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '漏洞名称',
  `vul_type` tinyint(4) NOT NULL COMMENT '漏洞的类型，1信息泄露2 SQL注入等\r\n            ',
  `threat_grade` tinyint(4) NOT NULL COMMENT '漏洞的危害等级，1高危 2中危 3低危\r\n            ',
  `threat_type` tinyint(4) NOT NULL COMMENT '威胁的类型，1 远程代码 2 恶意软件等',
  `release_time` bigint(20) NOT NULL COMMENT '漏洞发布的时间',
  `apt` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT 'APT 事件',
  `affected_software` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '受漏洞影响的产品列表',
  `repair_time` bigint(20) DEFAULT 0 COMMENT '漏洞修复的时间',
  `description` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '漏洞的中文描述',
  `source` tinyint(4) DEFAULT 2 COMMENT '漏洞的来源，1 CVE 2 CNVD',
  `use_detail` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '可能是漏洞利用的描述和展示',
  `reference` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '参考链接',
  `features` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '漏洞特征',
  `create_user` int(11) DEFAULT 0 COMMENT '创建人ID',
  `modified_user` int(11) DEFAULT 0 COMMENT '更改人ID',
  `gmt_create` bigint(20) DEFAULT 0 COMMENT '创建时间',
  `gmt_modified` bigint(20) DEFAULT 0 COMMENT '更改时间',
  `state` tinyint(4) NOT NULL DEFAULT 1 COMMENT '此条数据的状态：1 有效 0 无效\r\n            ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
