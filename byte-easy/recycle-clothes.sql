/*
Navicat MySQL Data Transfer

Source Server         : ·101.200.33.51
Source Server Version : 50726
Source Host           : 101.200.33.51:3306
Source Database       : recycle-clothes

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-07-17 19:26:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `clothes_type`
-- ----------------------------
DROP TABLE IF EXISTS `clothes_type`;
CREATE TABLE `clothes_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(200) DEFAULT NULL COMMENT '旧衣类型',
  `pic` varchar(200) DEFAULT NULL COMMENT '图片地址',
  `is_del` smallint(6) DEFAULT NULL COMMENT '状态（1：已删除；2：正常）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='旧衣类型表';

-- ----------------------------
-- Records of clothes_type
-- ----------------------------

-- ----------------------------
-- Table structure for `comments`
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` varchar(200) DEFAULT NULL COMMENT '评论',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '对应的商品id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父评论id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '评论人的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

-- ----------------------------
-- Records of comments
-- ----------------------------

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `pic` varchar(100) DEFAULT NULL COMMENT '图片地址',
  `price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `info` varchar(500) DEFAULT NULL COMMENT '物品详情',
  `goods_type_id` bigint(20) DEFAULT NULL COMMENT '商品类型id',
  `is_del` smallint(6) DEFAULT NULL COMMENT '状态（1：已删除；2：正常）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品信息表';

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for `goods_img`
-- ----------------------------
DROP TABLE IF EXISTS `goods_img`;
CREATE TABLE `goods_img` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pic` varchar(200) DEFAULT NULL COMMENT '商品图片url',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '对应商品id',
  `is_del` smallint(6) DEFAULT NULL COMMENT '状态（1：已删除；2：正常）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品信息表';

-- ----------------------------
-- Records of goods_img
-- ----------------------------

-- ----------------------------
-- Table structure for `goods_type`
-- ----------------------------
DROP TABLE IF EXISTS `goods_type`;
CREATE TABLE `goods_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(200) DEFAULT NULL COMMENT '旧衣类型',
  `pic` varchar(200) DEFAULT NULL COMMENT '图片地址',
  `is_del` smallint(6) DEFAULT NULL COMMENT '状态（1：已删除；2：正常）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='旧衣类型表';

-- ----------------------------
-- Records of goods_type
-- ----------------------------

-- ----------------------------
-- Table structure for `order_courier_rel`
-- ----------------------------
DROP TABLE IF EXISTS `order_courier_rel`;
CREATE TABLE `order_courier_rel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `courier_id` bigint(20) DEFAULT NULL COMMENT '取货员id',
  `pick_code` varchar(20) DEFAULT NULL COMMENT '取货码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='取货员订单联系表';

-- ----------------------------
-- Records of order_courier_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `persistent_logins`
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `series` varchar(255) NOT NULL,
  `last_used` datetime DEFAULT NULL,
  `token` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`series`),
  UNIQUE KEY `UK_bqa9l0go97v49wwyx4c0u3kpd` (`token`),
  UNIQUE KEY `UK_f8t9fsfwc17s6qcbx0ath6l3h` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for `recycle_order`
-- ----------------------------
DROP TABLE IF EXISTS `recycle_order`;
CREATE TABLE `recycle_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` smallint(6) DEFAULT NULL COMMENT '订单状态（1：待审核；2：未通过；3：待取货；4：交易完成）',
  `order_no` varchar(30) DEFAULT NULL COMMENT '订单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `courier_id` bigint(20) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL COMMENT '订单创建时间',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `appointment` datetime DEFAULT NULL COMMENT '预约取货时间 ',
  `address_id` bigint(20) DEFAULT NULL COMMENT '取货地址',
  `phone` varchar(11) DEFAULT NULL,
  `is_show` smallint(6) DEFAULT NULL COMMENT '是否删除（1：）',
  `is_del` smallint(6) DEFAULT NULL COMMENT '状态（1：已删除；2：正常）',
  `pick_code` varchar(20) DEFAULT NULL COMMENT '取货码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='回收订单表';

-- ----------------------------
-- Records of recycle_order
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_car`
-- ----------------------------
DROP TABLE IF EXISTS `shop_car`;
CREATE TABLE `shop_car` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `is_del` smallint(6) DEFAULT NULL COMMENT '状态（1:已删除；2：正常）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车';

-- ----------------------------
-- Records of shop_car
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_order`
-- ----------------------------
DROP TABLE IF EXISTS `shop_order`;
CREATE TABLE `shop_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(30) DEFAULT NULL COMMENT '订单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '买家id',
  `createtime` datetime DEFAULT NULL COMMENT '下单时间',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `address_id` bigint(20) DEFAULT NULL COMMENT '用户地址id',
  `phone` varchar(11) DEFAULT NULL COMMENT '用户电话号码',
  `is_show` smallint(6) DEFAULT NULL COMMENT '订单是否展示（1：不展示；2：展示）',
  `id_del` smallint(6) DEFAULT NULL COMMENT '使否删除（1：删除；2：正常）',
  `express` varchar(20) DEFAULT NULL COMMENT '快递单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品订单';

-- ----------------------------
-- Records of shop_order
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_generator`
-- ----------------------------
DROP TABLE IF EXISTS `sys_generator`;
CREATE TABLE `sys_generator` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `moduleName` varchar(80) DEFAULT NULL COMMENT '模块名称',
  `tableName` varchar(80) DEFAULT NULL COMMENT '表名称',
  `ignoreFlag` int(11) DEFAULT NULL COMMENT '是否忽略前缀1：是',
  `ignorePrefix` varchar(20) DEFAULT NULL COMMENT '前缀',
  `createRest` int(11) DEFAULT NULL COMMENT '是否生成rest接口1:是',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_generator
-- ----------------------------
INSERT INTO `sys_generator` VALUES ('1', 'admin', 'weixin_user', '0', '', '1', '2019-04-20 15:27:14', '2019-04-20 15:27:14');

-- ----------------------------
-- Table structure for `sys_organization`
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmeds2u6ae5usj0ko0bqj3k0eo` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order_num` int(11) NOT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3fekum3ead5klp7y4lckn5ohi` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '', '顶级栏目', '100', null, '0', null, '0');
INSERT INTO `sys_resource` VALUES ('2', '', '权限配置', '8', '', '0', '', '1');
INSERT INTO `sys_resource` VALUES ('3', '', '角色管理', '102', '/role', '0', '/role', '2');
INSERT INTO `sys_resource` VALUES ('4', '', '权限管理', '103', '/resource', '0', '/resource', '2');
INSERT INTO `sys_resource` VALUES ('5', '', '用户管理', '101', '/user', '0', '/user', '2');
INSERT INTO `sys_resource` VALUES ('6', '', '编辑', '100', '/role/editor-role', '1', '/role/editor-role', '3');
INSERT INTO `sys_resource` VALUES ('7', '', '添加权限节点', '100', '/resource/add-permission', '1', '/resource/add-permission', '4');
INSERT INTO `sys_resource` VALUES ('8', '', '添加管理员', '100', '/user/add-user', '1', '/user/add-user', '5');
INSERT INTO `sys_resource` VALUES ('9', '', '添加角色', '100', '/role/add-role', '1', '/role/add-role', '3');
INSERT INTO `sys_resource` VALUES ('10', '', '删除角色', '100', '/role/delete', '1', '/role/delete', '3');
INSERT INTO `sys_resource` VALUES ('11', '', '删除用户', '100', '/user/delete-user', '1', '/user/delete-user', '5');
INSERT INTO `sys_resource` VALUES ('12', '', '删除权限', '100', '/resource/delete', '1', '/resource/delete', '4');
INSERT INTO `sys_resource` VALUES ('13', '', '启用', '100', '/user/available-user', '1', '/user/available-user', '3');
INSERT INTO `sys_resource` VALUES ('14', '', '修改管理员密码', '100', '/user/modify-password', '1', '/user/modify-password', '5');
INSERT INTO `sys_resource` VALUES ('16', null, '权限编辑', '100', '/resource/editor-permission', '1', '/resource/editor-permission', '4');
INSERT INTO `sys_resource` VALUES ('150', '', '编辑管理员信息', '100', '/user/edit-user', '1', '/user/edit-user', '5');
INSERT INTO `sys_resource` VALUES ('154', null, '代码生成工具', '1', '/generator/sysGenerator', '0', '/generator/sysGenerator', '1');
INSERT INTO `sys_resource` VALUES ('155', null, '基础页面生成', '1', '/generator/sysGenerator', '0', '/generator/sysGenerator', '154');
INSERT INTO `sys_resource` VALUES ('156', null, '马里奥', '1', '', '0', '', '1');
INSERT INTO `sys_resource` VALUES ('157', null, '马里奥1', '1', '', '0', '/food/tFood', '156');
INSERT INTO `sys_resource` VALUES ('164', null, '舒克', '1', '', '0', '', '1');
INSERT INTO `sys_resource` VALUES ('165', null, '舒克1', '1', '', '0', 'animal/tAnimal', '164');
INSERT INTO `sys_resource` VALUES ('174', null, '人员管理', '1', '', '0', '', '1');
INSERT INTO `sys_resource` VALUES ('175', null, '人员管理模块', '1', '', '0', '/peoples/tPeoples', '174');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', null, '管理员', '管理员');

-- ----------------------------
-- Table structure for `sys_role_resources`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resources`;
CREATE TABLE `sys_role_resources` (
  `sys_role_id` bigint(20) NOT NULL,
  `resources_id` bigint(20) NOT NULL,
  KEY `FKog6jj4v6yh9e1ilxk2mwuk75a` (`resources_id`),
  KEY `FKsqkqfd2hpr5cc2kbrtgoced2w` (`sys_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resources
-- ----------------------------
INSERT INTO `sys_role_resources` VALUES ('1', '2');
INSERT INTO `sys_role_resources` VALUES ('1', '3');
INSERT INTO `sys_role_resources` VALUES ('1', '6');
INSERT INTO `sys_role_resources` VALUES ('1', '9');
INSERT INTO `sys_role_resources` VALUES ('1', '10');
INSERT INTO `sys_role_resources` VALUES ('1', '13');
INSERT INTO `sys_role_resources` VALUES ('1', '4');
INSERT INTO `sys_role_resources` VALUES ('1', '7');
INSERT INTO `sys_role_resources` VALUES ('1', '12');
INSERT INTO `sys_role_resources` VALUES ('1', '16');
INSERT INTO `sys_role_resources` VALUES ('1', '5');
INSERT INTO `sys_role_resources` VALUES ('1', '8');
INSERT INTO `sys_role_resources` VALUES ('1', '11');
INSERT INTO `sys_role_resources` VALUES ('1', '14');
INSERT INTO `sys_role_resources` VALUES ('1', '150');
INSERT INTO `sys_role_resources` VALUES ('1', '154');
INSERT INTO `sys_role_resources` VALUES ('1', '155');
INSERT INTO `sys_role_resources` VALUES ('1', '182');
INSERT INTO `sys_role_resources` VALUES ('1', '183');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createtime` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `available` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `sex_type` int(11) DEFAULT NULL COMMENT '性别(0.男,1.女)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '2017-07-11 17:42:18', '$2a$10$SIU57gfkh/TsIVYALXBNAeDnQzkm652FT9cg4h8wtEfC306uliyYa', '2019-04-16 17:13:21', 'admin', '', '1191134106@qq.com', '15030103078', '1');

-- ----------------------------
-- Table structure for `sys_user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `sys_user_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  KEY `FKdpvc6d7xqpqr43dfuk1s27cqh` (`roles_id`),
  KEY `FKd0ut7sloes191bygyf7a3pk52` (`sys_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_roles
-- ----------------------------
INSERT INTO `sys_user_roles` VALUES ('1', '1');

-- ----------------------------
-- Table structure for `user_prop`
-- ----------------------------
DROP TABLE IF EXISTS `user_prop`;
CREATE TABLE `user_prop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '和weixin_user表保持一致',
  `phone` varchar(11) DEFAULT NULL COMMENT '收货电话',
  `address` varchar(500) DEFAULT NULL COMMENT '收货地址',
  `user_id` bigint(20) DEFAULT NULL COMMENT '对应微信用户id',
  `is_del` smallint(6) DEFAULT NULL COMMENT '状态（1：已删除；2：正常）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户属性表';

-- ----------------------------
-- Records of user_prop
-- ----------------------------

-- ----------------------------
-- Table structure for `weixin_user`
-- ----------------------------
DROP TABLE IF EXISTS `weixin_user`;
CREATE TABLE `weixin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nickName` varchar(50) DEFAULT NULL COMMENT '昵称',
  `gender` int(11) DEFAULT NULL COMMENT '性别 1：男',
  `language` varchar(20) DEFAULT NULL COMMENT '语言',
  `city` varchar(20) DEFAULT NULL COMMENT '城市',
  `province` varchar(20) DEFAULT NULL COMMENT '省份',
  `avatarUrl` varchar(500) DEFAULT NULL COMMENT '头像地址#img',
  `openId` varchar(50) DEFAULT NULL COMMENT '唯一主键id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='微信用户表';

-- ----------------------------
-- Records of weixin_user
-- ----------------------------
