/*
Navicat MySQL Data Transfer

Source Server         : rc-bos-192.168.200.100
Source Server Version : 50721
Source Host           : 192.168.200.100:3306
Source Database       : bos

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-07-27 10:01:50
*/

SET FOREIGN_KEY_CHECKS=0;


DROP TABLE IF EXISTS `tb_sys_user`;
CREATE TABLE `tb_sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `user_pwd` varchar(200) NOT NULL COMMENT '用户密码',
  `creator` varchar(100) NOT NULL COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updator` varchar(100) DEFAULT NULL COMMENT '更新者',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='系统用户';


DROP TABLE IF EXISTS `tb_role_info`;
CREATE TABLE `tb_role_info` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(150) NOT NULL COMMENT '角色名称',
  `remark` varchar(200) NOT NULL COMMENT '描述',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色表';


DROP TABLE IF EXISTS `tb_sys_user_role`;
CREATE TABLE `tb_sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

DROP TABLE IF EXISTS `tb_menu_resource`;
CREATE TABLE `tb_menu_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_menu_id` int(11) DEFAULT NULL COMMENT '父级菜单ID',
  `parent_menu_name` varchar(50) DEFAULT NULL COMMENT '父级菜单名称',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `creator` varchar(100) NOT NULL COMMENT '创建者',
  `create_timie` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='资源表';

DROP TABLE IF EXISTS `tb_role_menu_resource`;
CREATE TABLE `tb_role_menu_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_resource_id` int(11) NOT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色资源关系表';
