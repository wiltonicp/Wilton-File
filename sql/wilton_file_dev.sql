/*
 Navicat Premium Data Transfer

 Source Server         : wilton
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : rds.aliyuncs.com:3306
 Source Schema         : wilton_file_dev

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 02/07/2021 17:31:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for f_file
-- ----------------------------
DROP TABLE IF EXISTS `f_file`;
CREATE TABLE `f_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `folder_id` bigint(20) DEFAULT NULL COMMENT '目录 id',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `file_type` varchar(50) DEFAULT NULL COMMENT '文件类型',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `file_md5` varchar(255) DEFAULT NULL COMMENT '文件唯一指纹',
  `store_name` varchar(255) DEFAULT NULL COMMENT '存储名称',
  `open` bit(1) DEFAULT NULL COMMENT '是否公开',
  `ico` varchar(255) DEFAULT NULL COMMENT '图标类型',
  `thumb` varchar(520) DEFAULT NULL COMMENT '预览地址',
  `created_by` bigint(20) DEFAULT NULL COMMENT '上传者',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_by` bigint(20) DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `version` bigint(20) DEFAULT '0' COMMENT '乐观锁',
  `deleted` int(5) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of f_file
-- ----------------------------
BEGIN;
INSERT INTO `f_file` VALUES (144, 1, '页面 201.pdf', 'txt', 76132862, 'e4c6a62c47486cd4baf0372d6dc64357', 'e4c6a62c47486cd4baf0372d6dc64357页面 201.pdf', b'1', 'pdf', NULL, 1, '2021-04-08 21:17:55', 1, '2021-05-18 11:41:00', 1, 0);
INSERT INTO `f_file` VALUES (146, 1, 'java复习大纲.docx', 'txt', 18863, 'a16002003edb9dc0980b02a7f13e8a42', 'a16002003edb9dc0980b02a7f13e8a42java复习大纲.docx', b'1', 'docx', NULL, 1, '2021-04-14 17:47:57', 1, '2021-04-14 17:47:57', 1, 0);
INSERT INTO `f_file` VALUES (147, 1, 'defult.jpeg', 'image', 18722, 'dc5f479840fe14ab3bc499c391a27674', 'dc5f479840fe14ab3bc499c391a27674defult.jpeg', b'1', 'jpeg', NULL, 1, '2021-04-17 23:16:14', 1, '2021-04-17 23:16:14', 1, 0);
INSERT INTO `f_file` VALUES (148, 1, '1b32w2v8xyY.jpg', 'image', 324224, 'be981b007885af196815ced6c01c76e4', 'be981b007885af196815ced6c01c76e41b32w2v8xyY.jpg', b'1', 'jpg', NULL, 1, '2021-04-17 23:58:09', 1, '2021-05-19 11:57:16', 1, 0);
INSERT INTO `f_file` VALUES (157, 1, '编程环境和软件工具安装手册.pdf', 'txt', 14499514, 'f6303caf5b53c6538e25816bdf644d42', 'f6303caf5b53c6538e25816bdf644d42编程环境和软件工具安装手册.pdf', b'1', 'pdf', NULL, 1, '2021-05-17 10:19:38', 1, '2021-05-17 10:19:42', 1, 1);
INSERT INTO `f_file` VALUES (158, 1, '《Spring Cloud Alibaba 从入门到实战》.pdf', 'txt', 4476638, '59f4115b4c674e8f6a85635aae145502', '59f4115b4c674e8f6a85635aae145502《Spring Cloud Alibaba 从入门到实战》.pdf', b'1', 'pdf', NULL, 1, '2021-05-17 11:08:49', 1, '2021-05-17 11:08:49', 1, 1);
INSERT INTO `f_file` VALUES (159, 1, 'think-in-java.pdf', 'txt', 4526576, 'c7d288abf46a3f1da5ab504f5efb4c54', 'c7d288abf46a3f1da5ab504f5efb4c54think-in-java.pdf', b'1', 'pdf', NULL, 1, '2021-05-17 13:48:23', 1, '2021-05-17 13:48:23', 1, 0);
INSERT INTO `f_file` VALUES (160, 1, 'Java设计模式ppt.ppt', 'txt', 5845504, 'fffc4013855f3b5b41ea1abe4d8a567a', 'fffc4013855f3b5b41ea1abe4d8a567aJava设计模式ppt.ppt', b'1', 'ppt', NULL, 1, '2021-05-17 13:53:56', 1, '2021-05-17 13:53:56', 1, 0);
INSERT INTO `f_file` VALUES (161, 1, 'JAVA核心知识点整理.pdf', 'txt', 10921848, 'f6b22cf45e3344cf68539b9efc74ce9d', 'f6b22cf45e3344cf68539b9efc74ce9dJAVA核心知识点整理.pdf', b'1', 'pdf', NULL, 1, '2021-05-17 13:55:18', 1, '2021-05-17 13:55:18', 1, 1);
INSERT INTO `f_file` VALUES (162, 1, '[尚硅谷]_佟刚_Spring 面试题分析.pdf', 'txt', 543430, '43aae0feea6be8e8493896680a878ba9', '43aae0feea6be8e8493896680a878ba9[尚硅谷]_佟刚_Spring 面试题分析.pdf', b'1', 'pdf', NULL, 1, '2021-05-17 14:05:41', 1, '2021-05-17 14:05:41', 1, 0);
INSERT INTO `f_file` VALUES (163, 1, '115个Java面试题和答案——终极（上）_尚硅谷_宋红康.pdf', 'txt', 479418, 'e8146dbb2f5483b276a72adc54a2d93d', 'e8146dbb2f5483b276a72adc54a2d93d115个Java面试题和答案——终极（上）_尚硅谷_宋红康.pdf', b'1', 'pdf', NULL, 1, '2021-05-17 14:06:08', 1, '2021-05-17 14:06:08', 1, 0);
INSERT INTO `f_file` VALUES (164, 1, 'logo.png', 'image', 30840, '71766350d073cc4e7d4aa4a53d8d88af', '71766350d073cc4e7d4aa4a53d8d88aflogo.png', b'1', 'png', NULL, 1, '2021-05-22 22:35:10', 1, '2021-05-22 22:35:10', 4, 1);
INSERT INTO `f_file` VALUES (165, 0, 'favicon1.ico', 'image', 40149, '1b00074b7d8e92bb00a75a4af386a40b', '1b00074b7d8e92bb00a75a4af386a40bfavicon1.ico', b'1', 'ico', NULL, 1, '2021-05-22 23:03:12', 1, '2021-05-22 23:03:12', 3, 0);
INSERT INTO `f_file` VALUES (166, 111, 'OfficialAccounts11.jpeg', 'image', 73169, '19405482d08b2372d03276b5c1206460', '19405482d08b2372d03276b5c1206460OfficialAccounts11.jpeg', b'1', 'jpeg', NULL, 1, '2021-05-22 23:37:00', 1, '2021-05-22 23:37:00', 1, 0);
INSERT INTO `f_file` VALUES (178, 31, '产品结构.xmind', 'other', 97223, '214d63dc5b105ad6ce374b15e40a0d33', '214d63dc5b105ad6ce374b15e40a0d33产品结构.xmind', b'1', 'xmind', NULL, 1, '2021-05-23 00:22:42', 1, '2021-05-23 00:22:42', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for f_folder
-- ----------------------------
DROP TABLE IF EXISTS `f_folder`;
CREATE TABLE `f_folder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `folder_name` varchar(50) DEFAULT NULL COMMENT '文件夹名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
  `type` bigint(20) DEFAULT NULL COMMENT 'type',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_by` bigint(20) DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `version` bigint(20) DEFAULT '0' COMMENT '乐观锁',
  `deleted` int(5) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of f_folder
-- ----------------------------
BEGIN;
INSERT INTO `f_folder` VALUES (1, '根目录', 0, 0, 1, '2021-04-01 15:43:37', 1, '2021-04-01 15:44:33', 0, 0);
INSERT INTO `f_folder` VALUES (2, '我的资源', 1, 0, 1, '2021-04-01 15:43:42', 1, '2021-04-05 21:04:03', 0, 0);
INSERT INTO `f_folder` VALUES (3, '开发工具', 1, 0, 1, '2021-04-01 15:43:45', 1, '2021-04-05 21:21:58', 0, 0);
INSERT INTO `f_folder` VALUES (4, '4K 视频', 2, 0, 1, '2021-04-01 15:43:49', 1, '2021-04-01 15:44:33', 0, 0);
INSERT INTO `f_folder` VALUES (9, '破解工具', 3, 0, 1, '2021-04-01 15:43:57', 1, '2021-04-01 15:44:33', 0, 0);
INSERT INTO `f_folder` VALUES (13, 'Spring 相关', 1, 0, 1, '2021-04-01 15:44:01', 1, '2021-04-05 22:15:02', 0, 0);
INSERT INTO `f_folder` VALUES (14, '乔布斯', 12, 0, 1, '2021-04-01 15:44:01', 1, '2021-04-01 15:44:33', 0, 1);
INSERT INTO `f_folder` VALUES (21, '根目录', 0, 0, 6, '2021-04-24 19:50:38', 6, '2021-04-24 19:50:38', 0, 0);
INSERT INTO `f_folder` VALUES (22, '测试文件夹', 3, 0, 1, '2021-04-25 20:30:09', 1, '2021-05-18 11:34:58', 0, 1);
INSERT INTO `f_folder` VALUES (28, '我的地盘', 3, 0, 1, '2021-05-18 11:15:18', 1, '2021-05-18 11:15:18', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for f_share
-- ----------------------------
DROP TABLE IF EXISTS `f_share`;
CREATE TABLE `f_share` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `ico` varchar(10) DEFAULT NULL COMMENT '文件后缀',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `file_type` varchar(20) DEFAULT NULL COMMENT '文件类别',
  `file_id` bigint(20) DEFAULT NULL COMMENT '文件 id',
  `open` bit(1) DEFAULT NULL COMMENT '是否公开',
  `share_code` varchar(255) DEFAULT NULL COMMENT '分享码',
  `pickup_code` varchar(20) DEFAULT NULL COMMENT '取件码',
  `state` int(5) DEFAULT '1' COMMENT '状态 0：已失效、1：永久有效、自定义天数',
  `views` bigint(20) DEFAULT '0' COMMENT '下载次数',
  `created_by` bigint(20) DEFAULT NULL COMMENT '上传者',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_by` bigint(20) DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `version` bigint(20) DEFAULT '0' COMMENT '乐观锁',
  `deleted` int(5) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of f_share
-- ----------------------------
BEGIN;
INSERT INTO `f_share` VALUES (9, 'java复习大纲.docx', 'docx', 18863, 'docx', 146, b'1', 'g4s4tq3jhceethei', 'm8xc', -1, 0, 1, '2021-04-15 11:23:33', 1, '2021-04-15 11:23:33', 0, 1);
INSERT INTO `f_share` VALUES (10, 'java复习大纲.docx', 'docx', 18863, 'docx', 146, b'1', '8bqus82g934nsz3j', 'yify', -1, 0, 1, '2021-04-16 09:20:26', 1, '2021-04-16 09:20:26', 0, 1);
INSERT INTO `f_share` VALUES (11, '页面 101.pdf', 'pdf', 75185335, 'pdf', 145, b'1', '6t3gxhtg7jp2xq2r', 'ptpb', -1, 0, 1, '2021-04-16 09:27:25', 1, '2021-04-16 09:27:25', 0, 0);
INSERT INTO `f_share` VALUES (12, 'java复习大纲.docx', 'docx', 18863, 'docx', 146, b'1', 'xmb8t3wrwvxatsfa', 'b9z4', -1, 0, 1, '2021-04-16 19:38:11', 1, '2021-04-16 19:38:11', 0, 1);
INSERT INTO `f_share` VALUES (13, 'java复习大纲.docx', 'docx', 18863, 'docx', 146, b'1', 'pmwqvnwnnvasvfpf', 'zmyq', -1, 0, 1, '2021-04-16 21:15:34', 1, '2021-04-16 21:15:34', 0, 0);
INSERT INTO `f_share` VALUES (14, 'defult.jpeg', 'jpeg', 18722, 'image', 147, b'1', 'wswwgksdxiakgd7e', '8wzr', -1, 0, 1, '2021-04-17 23:16:29', 1, '2021-04-17 23:16:29', 0, 1);
INSERT INTO `f_share` VALUES (15, 'defult.jpeg', 'jpeg', 18722, 'image', 147, b'1', 'sujuh8amtk7nhz3q', 'idh6', -1, 0, 1, '2021-04-18 00:15:15', 1, '2021-04-18 00:15:15', 0, 0);
INSERT INTO `f_share` VALUES (16, '1b32w2v8xyY.jpg', 'jpg', 324224, 'image', 148, b'1', 'viniffpdus2yfnep', '6sqa', -1, 0, 1, '2021-04-18 17:22:51', 1, '2021-04-18 17:22:51', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for msg
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_id` bigint(20) DEFAULT NULL COMMENT '聊天室 id',
  `content` varchar(1000) DEFAULT NULL COMMENT '消息内容',
  `created_by` bigint(20) DEFAULT NULL COMMENT '上传者',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_by` bigint(20) DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `version` bigint(20) DEFAULT '0' COMMENT '乐观锁',
  `deleted` int(5) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of msg
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for msg_log
-- ----------------------------
DROP TABLE IF EXISTS `msg_log`;
CREATE TABLE `msg_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `msg_id` bigint(20) DEFAULT NULL COMMENT '消息 id',
  `room_id` bigint(20) DEFAULT NULL COMMENT '群组 id',
  `from_uid` bigint(20) DEFAULT NULL COMMENT '发送者 id',
  `to_uid` bigint(20) DEFAULT NULL COMMENT '接受者 id',
  `is_read` bit(1) DEFAULT NULL COMMENT '已读',
  `created_by` bigint(20) DEFAULT NULL COMMENT '上传者',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_by` bigint(20) DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `version` bigint(20) DEFAULT '0' COMMENT '乐观锁',
  `deleted` int(5) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of msg_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
BEGIN;
INSERT INTO `persistent_logins` VALUES ('809733657@qq.com', '6+UsKgcxaq/Lik6iNjvn1A==', 'mEn7e/GOJtDGdxnyu0UI7w==', '2021-04-26 16:54:19');
INSERT INTO `persistent_logins` VALUES ('809733657@qq.com', 'TlIBFKddnQ2VRGDkmANzXA==', 'FKADst3YjeQO5QG6QqLD+g==', '2021-04-24 19:19:41');
COMMIT;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_name` varchar(20) DEFAULT NULL COMMENT '聊天室名称',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_by` bigint(20) DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `version` bigint(20) DEFAULT '0' COMMENT '乐观锁',
  `deleted` int(5) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of room
-- ----------------------------
BEGIN;
INSERT INTO `room` VALUES (1, 'vihacker云畅聊室', 1, '2021-04-26 15:39:57', 1, '2021-04-26 15:40:00', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for room_user
-- ----------------------------
DROP TABLE IF EXISTS `room_user`;
CREATE TABLE `room_user` (
  `room_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  UNIQUE KEY `room_id` (`room_id`,`user_id`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of room_user
-- ----------------------------
BEGIN;
INSERT INTO `room_user` VALUES (1, 1);
INSERT INTO `room_user` VALUES (1, 6);
COMMIT;

-- ----------------------------
-- Table structure for t_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `USERNAME` varchar(50) NOT NULL COMMENT '用户名',
  `LOGIN_TIME` datetime NOT NULL COMMENT '登录时间',
  `LOCATION` varchar(50) DEFAULT NULL COMMENT '登录地点',
  `IP` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `SYSTEM` varchar(50) DEFAULT NULL COMMENT '操作系统',
  `BROWSER` varchar(50) DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`ID`) USING BTREE,
  KEY `t_login_log_login_time` (`LOGIN_TIME`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='登录日志表';

-- ----------------------------
-- Records of t_login_log
-- ----------------------------
BEGIN;
INSERT INTO `t_login_log` VALUES (44, 'wilton.icp@gmail.com', '2021-05-20 15:49:28', '', '127.0.0.1', 'Mac OS X 11_0_1)', 'Chrome 89');
INSERT INTO `t_login_log` VALUES (45, 'wilton.icp@gmail.com', '2021-05-20 15:49:49', '', '127.0.0.1', 'Mac OS X 11_0_1)', 'Chrome 89');
INSERT INTO `t_login_log` VALUES (46, 'wilton.icp@gmail.com', '2021-05-20 16:08:23', '', '127.0.0.1', 'Mac OS X 11_0_1)', 'Chrome 89');
INSERT INTO `t_login_log` VALUES (47, 'wilton.icp@gmail.com', '2021-05-20 16:08:30', '', '127.0.0.1', 'Mac OS X 11_0_1)', 'Chrome 89');
INSERT INTO `t_login_log` VALUES (48, 'wilton.icp@gmail.com', '2021-05-20 16:12:10', '', '127.0.0.1', 'Mac OS X 11_0_1)', 'Chrome 89');
INSERT INTO `t_login_log` VALUES (49, 'wilton.icp@gmail.com', '2021-05-20 16:18:25', '', '127.0.0.1', 'Mac OS X 11_0_1)', 'Chrome 89');
INSERT INTO `t_login_log` VALUES (50, 'wilton.icp@gmail.com', '2021-05-20 16:26:02', '', '127.0.0.1', 'Mac OS X 11_0_1)', 'Chrome 89');
INSERT INTO `t_login_log` VALUES (51, 'wilton.icp@gmail.com', '2021-05-20 16:30:18', '', '127.0.0.1', 'Mac OS X 11_0_1)', 'Chrome 89');
INSERT INTO `t_login_log` VALUES (52, 'wilton.icp@gmail.com', '2021-05-22 22:34:26', '内网IP|0|0|内网IP|内网IP', '127.0.0.1', 'Mac OS X 11_0_1)', 'Chrome 89');
INSERT INTO `t_login_log` VALUES (53, 'wilton.icp@gmail.com', '2021-05-23 18:18:25', '内网IP|0|0|内网IP|内网IP', '127.0.0.1', 'Mac OS X 11_0_1)', 'Chrome 89');
INSERT INTO `t_login_log` VALUES (54, 'wilton.icp@gmail.com', '2021-05-23 18:20:24', '内网IP|0|0|内网IP|内网IP', '127.0.0.1', 'Mac OS X 11_0_1)', 'Chrome 89');
INSERT INTO `t_login_log` VALUES (55, 'wilton.icp@gmail.com', '2021-05-23 18:50:13', '内网IP|0|0|内网IP|内网IP', '127.0.0.1', 'Mac OS X 11_0_1)', 'Chrome 89');
INSERT INTO `t_login_log` VALUES (56, 'wilton.icp@gmail.com', '2021-05-24 15:24:18', '内网IP|0|0|内网IP|内网IP', '127.0.0.1', 'Mac OS X 11_0_1)', 'Chrome 89');
INSERT INTO `t_login_log` VALUES (57, 'wilton.icp@gmail.com', '2021-05-27 15:15:04', '内网IP|0|0|内网IP|内网IP', '127.0.0.1', 'Mac OS X 11_0_1)', 'Chrome 89');
INSERT INTO `t_login_log` VALUES (58, 'wilton.icp@gmail.com', '2021-06-01 14:08:54', '内网IP|0|0|内网IP|内网IP', '127.0.0.1', 'Mac OS X 10_15_7', 'Chrome 91');
COMMIT;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL COMMENT '权限标识符',
  `description` varchar(64) DEFAULT NULL COMMENT '描述',
  `url` varchar(128) DEFAULT NULL COMMENT '请求地址',
  `version` bigint(20) DEFAULT '0' COMMENT '乐观锁',
  `deleted` int(5) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_permission` VALUES (1, 'file:view', '首页', '/', 0, 0);
INSERT INTO `t_permission` VALUES (2, 'share:view', '我的分享', '/share', 0, 0);
INSERT INTO `t_permission` VALUES (3, 'manage:view', '用户管理', '/admin/user', 0, 0);
INSERT INTO `t_permission` VALUES (4, 'recycle:view', '回收站', '/recycle', 0, 0);
INSERT INTO `t_permission` VALUES (5, 'project:view', '我的项目', '/project', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modify_by` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT '0' COMMENT '乐观锁',
  `deleted` int(5) DEFAULT '0' COMMENT '逻辑删除',
  `status` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES (1, 'ADMIN', '超级管理员，管理所有，可查看所有', 1, 1, '2021-04-02 16:22:31', '2021-04-02 16:22:28', 0, 0, '');
INSERT INTO `t_role` VALUES (2, 'USER', '普通用户、可查看自己目录下文件', 1, 1, '2021-04-02 16:22:31', '2021-04-02 16:22:28', 0, 0, '');
COMMIT;

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  `version` bigint(20) DEFAULT '0' COMMENT '乐观锁',
  `deleted` int(5) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_role_permission` VALUES (1, 1, 0, 0);
INSERT INTO `t_role_permission` VALUES (1, 2, 0, 0);
INSERT INTO `t_role_permission` VALUES (1, 3, 0, 0);
INSERT INTO `t_role_permission` VALUES (1, 4, 0, 0);
INSERT INTO `t_role_permission` VALUES (2, 1, 0, 0);
INSERT INTO `t_role_permission` VALUES (2, 2, 0, 0);
INSERT INTO `t_role_permission` VALUES (2, 4, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `nick_name` varchar(15) NOT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT 'default.jpg' COMMENT '头像',
  `ssex` tinyint(5) DEFAULT NULL COMMENT '性别',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `description` varchar(500) DEFAULT NULL COMMENT '个人描述',
  `storage_size` bigint(20) DEFAULT NULL COMMENT '用户可存储大小',
  `space_code` varchar(255) DEFAULT NULL COMMENT '存储空间id',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `created_by` bigint(20) DEFAULT NULL,
  `modify_by` bigint(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT '0' COMMENT '乐观锁',
  `deleted` int(5) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `space_code` (`space_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_users
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1, 'wilton.icp@gmail.com', '$2a$10$AKxb9Mzi83McsLNPbMKeW.PFYArQhQuwOuu26x7.WGlIFYRGdNbwO', 'Ranger', '20180414165909.jpg', 0, '18877778888', 'wilton.icp@gmail.com', '我是作者', 500, '5f6acede-679e-4deb-94fa-0e7d469edcee', '2021-06-01 14:08:54', 1, NULL, '2021-04-02 16:21:29', NULL, 0, 0);
INSERT INTO `t_user` VALUES (2, 'test', '$2a$10$aFsOFzujtPCnUCUKcozsHux0rQ/3faAHGFSVb9Y.B1ntpmEhjRtru', 'Test', 'default.jpg', 0, NULL, NULL, NULL, 1, '4da6e0d4-ca2f-43e7-9cb2-38be420e19b0', NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `t_user` VALUES (6, '809733657@qq.com', '$2a$10$SO99SUBv4J.wMMuFQYUnrOqdbeBvn8fI4OciW2zKcHlLzfYtKRGk2', 'Vihacker', 'default.jpg', 0, NULL, '809733657@qq.com', NULL, 10, '5c0b7c78ced74411a792a4bbb16aa990', NULL, 1, 1, '2021-04-24 19:50:38', '2021-04-24 19:50:38', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT '0' COMMENT '乐观锁',
  `deleted` int(5) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
BEGIN;
INSERT INTO `t_user_role` VALUES (1, 1, '2021-04-02 16:28:07', NULL, 0, 0);
INSERT INTO `t_user_role` VALUES (2, 2, '2021-04-02 16:29:12', NULL, 0, 0);
INSERT INTO `t_user_role` VALUES (6, 2, NULL, NULL, 0, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
