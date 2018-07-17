DROP TABLE IF EXISTS tb_user;

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(20) NOT NULL COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL COMMENT '用户名称',
  `pwd` varchar(50) NOT NULL COMMENT '密码',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `regist_time` timestamp NOT NULL COMMENT '注册时间',
  `last_update_time` timestamp NULL DEFAULT NULL COMMENT '最后更新时间',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态(0:正常)',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`)
);









