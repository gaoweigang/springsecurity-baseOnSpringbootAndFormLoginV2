--创建数据dev
CREATE DATABASE test;

--切换到数据库dev
USE test;

--查看当前正在使用的数据库
select DATABASE();

--查看当前库中存在哪些表
show tables;

drop table tbl_login_log
--创建表
--登陆日志表
create table `tbl_login_log` (
  `id` bigint(64) not null auto_increment comment '主键流水号',
  `client_ip` varchar(64) default null comment '客户端登陆ip',
  `login_time` timestamp null default null comment '登入时间',
  `logout_time` timestamp null default null comment '登出时间',
  `server_ip` varchar(64) default null comment '服务器ip',
  `server_port` varchar(64) default null comment '服务器端口',
  `service_id` varchar(64) default null comment '服务系统 0 业务系统 1支撑系统',
  `username` varchar(64) default null comment '用户名',
   create_time timestamp NULL DEFAULT NULL comment '创建时间',
   modify_time timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   creator varchar(32) default null comment '创建人',
   modifier varchar(32) default null comment '修改人',
   remark varchar(255) default null comment '描述',
  primary key (`id`)
) engine=innodb default charset=utf8 comment='登陆信息表';

--用户登陆信息表
CREATE TABLE `tbl_user` (
  `id` bigint(32) not null auto_increment comment '主键',
  `username` varchar(20) not null comment '用户登录id',
   staff_code varchar(40) default null comment '员工编号',
  `errpwdcount` TINYINT(1) default null comment '密码连续错误次数',
  `lastlogintime` timestamp null default null comment '最近成功登陆时间',
  `lastupdatepwdtime` timestamp null default null comment '最近密码更换时间',
  `password` varchar(20) default null comment '密码',
  `start_date` timestamp null default null comment '用户起用日期',
  `stop_date` timestamp null default null comment '用户停用日期',
  `user_status` varchar(1) default null comment '用户当前状态(0：正常，1：密码过期，2：账户已冻结，3：已销户)',
  `valid_flag` TINYINT(1) not null default '1' comment '有效标志 1：有效，0：无效 表示停用',
   create_time timestamp NULL DEFAULT NULL comment '创建时间',
   modify_time timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   creator varchar(32) default null comment '创建人',
   modifier varchar(32) default null comment '修改人',
   remark varchar(255) default null comment '描述',
  primary key (`id`),
  unique key `unique_username` (`username`) using btree
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录信息表';

--用户角色关联表
CREATE TABLE `tbl_user_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `staff_code` varchar(20) DEFAULT NULL COMMENT '角色id',
  `role_code` varchar(20) DEFAULT NULL COMMENT '角色编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

CREATE TABLE `tbl_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `valid_flag` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0: 无效， 1有效',
   create_time timestamp NULL DEFAULT NULL comment '创建时间',
   modify_time timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   creator varchar(32) default null comment '创建人',
   modifier varchar(32) default null comment '修改人',
   remark varchar(255) default null comment '描述',
  PRIMARY KEY (`id`),
  UNIQUE key `unique_role_code` (`role_code`) using btree
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='角色表';

--角色资源关联表
CREATE TABLE `tbl_role_resource` (
   id bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(32) NOT NULL comment '角色编码',
  `res_code` varchar(32) NOT NULL comment '资源编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源关联表';

--资源表 包含菜单和按钮
CREATE TABLE `tbl_resource` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `res_code` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `res_name` varchar(255) DEFAULT NULL COMMENT '资源名称-英文',
  `res_url` varchar(255) DEFAULT NULL COMMENT '资源url',
  `type` int(11) DEFAULT NULL COMMENT '资源类型   1:菜单    2：按钮',
  `pid` int(11) DEFAULT NULL COMMENT '父资源',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `valid_flag` tinyint(1) default null comment '有效标志 1：有效，0：无效 表示停用',
   create_time timestamp NULL DEFAULT NULL comment '创建时间',
   modify_time timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   creator varchar(32) default null comment '创建人',
   modifier varchar(32) default null comment '修改人',
   remark varchar(255) default null comment '描述',
  PRIMARY KEY (`id`),
  UNIQUE key `unique_res_code` (`res_code`) using btree
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='资源表';



drop table tbl_staff
--人员信息表,仅仅是人员信息，公司在职人员并不一定可以登陆系统
create table `tbl_staff` (
  `id` bigint(32) not null auto_increment comment '主键',
   staff_code varchar(40) default null comment '员工编号',
  `staff_name` varchar(40) default null comment '员工姓名',
  `sex` varchar(10) NOT NULL DEFAULT '0' comment '性别, 0: 女， 1：男',
   birthday timestamp NULL DEFAULT NULL COMMENT '出生日期',
   card_no varchar(32) DEFAULT NULL COMMENT '身份证号',
  `email` varchar(40) default null comment '邮箱',
  `mobile` varchar(20) default null comment '电话',
  `position` varchar(10) default null comment '职位',
   status tinyint(1) NOT NULL DEFAULT '1' comment '人员状态： 1：在职，0：离职',
  `valid_flag` tinyint(1) not null DEFAULT '1' comment '记录是否有效 1:有效, 0:无效',
  `entry_time` timestamp not null default current_timestamp comment '入职时间',
  `resign_time` datetime default null comment '离职日期',
   create_time timestamp NULL DEFAULT NULL comment '创建时间',
   modify_time timestamp NULL DEFAULT NULL COMMENT '修改时间',
   creator varchar(32) default null comment '创建人',
   modifier varchar(32) default null comment '修改人',
   remark varchar(255) default null comment '描述',
  primary key (`id`)
) engine=innodb default charset=utf8 comment='人员信息表';

--查询表
SELECT * from tbl_staff;
--账号表
SELECT * from tbl_user;
SELECT * from tbl_user_role;
SELECT * from tbl_role;
SELECT * from tbl_role_resource;
SELECT * from tbl_resource;
SELECT * from tbl_login_log;


INSERT INTO `tbl_login_log`(CLIENT_IP,LOGIN_TIME , LOGOUT_TIME,SERVER_IP,SERVER_PORT,SERVICE_ID,USERNAME) VALUES ( NULL, '2018-6-8 19:09:00', '2018-6-8 19:09:00', NULL, NULL, NULL, '00000013');
INSERT INTO `tbl_login_log`(CLIENT_IP,LOGIN_TIME , LOGOUT_TIME,SERVER_IP,SERVER_PORT,SERVICE_ID,USERNAME) VALUES ( NULL, '2018-6-8 18:38:47', '2018-6-8 18:38:47', NULL, NULL, NULL, '00000012');


INSERT INTO `tbl_user_role`(staff_code, role_code) VALUES ( 'HB0001', 'MARKET');
INSERT INTO `tbl_user_role`(staff_code, role_code) VALUES ( 'HB0002', 'XLOAN' );
INSERT INTO `tbl_user_role`(staff_code, role_code) VALUES ( 'HB0003', 'ADMIN');


INSERT INTO `tbl_resource` VALUES (1,  'systemPage',              '系统组织管理','/systemPage', 1, 0, 0, 1, NULL, '2018-6-4 11:32:30', NULL, NULL, NULL);
INSERT INTO `tbl_resource` VALUES (2,  'userPage',               '用户管理',    '/userPage', 1, 1, 2, 1, NULL, '2018-6-4 11:32:35', NULL, NULL, NULL);
INSERT INTO `tbl_resource` VALUES (3,  'rolePage',               '角色管理',    '/rolePage', 1, 1, 3, 1, NULL, '2018-6-4 11:32:52', NULL, NULL, NULL);
INSERT INTO `tbl_resource` VALUES (5,  'userAdd',                '添加用户',    '/user/add', 2, 2, 5, 1, NULL, '2018-6-4 11:33:02', NULL, NULL, NULL);
INSERT INTO `tbl_resource` VALUES (6,  'userDelete',             '删除用户',    '/user/delete', 2, 6, 6, 1, NULL, '2018-6-4 11:33:11', NULL, NULL, NULL);
INSERT INTO `tbl_resource` VALUES (7,  'roleAdd',                '添加角色',    '/role/addRole', 2, 3, 7, 1, NULL, '2018-6-4 11:33:21', NULL, NULL, NULL);
INSERT INTO `tbl_resource` VALUES (8,  'roleDel',             '删除角色',    '/role/delRole', 2, 1, 8, 1, NULL, '2018-6-4 11:33:32', NULL, NULL, NULL);
INSERT INTO `tbl_resource` VALUES (9,  'resourcesAdd',            '添加资源',     '/resource/add', 2, 4, 9, 1, NULL, '2018-6-4 11:33:42', NULL, NULL, NULL);
INSERT INTO `tbl_resource` VALUES (10, 'resourceAll',         '获取所有资源',   '/resource/queryAllResources', 2, 4, 10, 1, NULL, '2018-6-4 11:33:51', NULL , NULL, NULL);
INSERT INTO `tbl_resource` VALUES (11, 'userSaveUserRoles',      '分配角色',   '/user/saveUserRoles', 2, 2, 11, 1, NULL, '2018-6-4 11:34:02', NULL, NULL, NULL);
INSERT INTO `tbl_resource` VALUES (13, 'roleSaveRoleResources',  '分配权限',   '/role/saveRoleResources', 2, 3, 12, 1, NULL, '2018-6-4 11:34:12', NULL, NULL, NULL);
INSERT INTO `tbl_resource` VALUES (14,  'marketPage',              '市场营销','/marketPage', 1, 0, 13, 1, NULL, '2018-6-4 11:32:30', NULL, NULL, NULL);
INSERT INTO `tbl_resource` VALUES (15, 'smsSend',            '短信发送',   '/smsSend', 1, 14, 14, 1, NULL, '2018-6-4 11:34:20', NULL, NULL, NULL);
INSERT INTO `tbl_resource` VALUES (16, 'nameIssued',               '名单下发',       '/nameIssued', 1, 14, 15, 1, NULL, '2018-6-4 11:34:31', NULL, NULL, NULL);
INSERT INTO `tbl_resource` VALUES (17, 'Page',               '信审',   '/auditPage', 1, 0, 16, 1, NULL, '2018-6-4 11:34:53', NULL, NULL, NULL);
INSERT INTO `tbl_resource` VALUES (18, 'tdScore',               '同盾跑分',   '/audit/tdScore', 1, 17, 17, 1, NULL, '2018-6-4 11:34:53', NULL, NULL, NULL);
INSERT INTO `tbl_resource` VALUES (19,  'queryUserInfoById',                '根据UserId查询用户信息',    '/user/queryUserInfoByUserId', 2, 2, 5, 1, NULL, '2018-6-4 11:33:02', NULL, NULL, NULL);



--市场
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('MARKET', 'marketPage');
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('MARKET', 'smsSend');
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('MARKET', 'nameIssued');
--信审
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('XLOAN', 'auditPage');
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('XLOAN', 'tdScore');
--管理员
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('ADMIN', 'systemPage'             );
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('ADMIN', 'userPage'              );
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('ADMIN', 'rolePage'              );
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('ADMIN', 'userAdd'               );
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('ADMIN', 'userDelete'            );
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('ADMIN', 'roleAdd'               );
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('ADMIN', 'roleDel'            );
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('ADMIN', 'resourceAdd'           );
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('ADMIN', 'resourceAll'        );
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('ADMIN', 'userSaveUserRoles'     );
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('ADMIN', 'roleSaveRoleResources' );
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('ADMIN', 'marketPage');
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('ADMIN', 'smsSend');
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('ADMIN', 'nameIssued');
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('ADMIN', 'auditPage');
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('ADMIN', 'tdScore');
INSERT INTO `tbl_role_resource`(role_code,res_code) VALUES ('ADMIN', 'queryUserInfoById');




INSERT INTO `tbl_staff`(staff_code, staff_name,SEX,birthday,card_no,EMAIL,MOBILE,POSITION,VALID_FLAG,entry_time) VALUES ('HB0001', 'gaoweigang', 0, '2018-3-12 16:40:00', '420881199101095170', '1245508721@qq.com', '13817191469', '开发', 1, '2018-3-12 16:40:00');
INSERT INTO `tbl_staff`(staff_code, staff_name,SEX,birthday,card_no,EMAIL,MOBILE,POSITION,VALID_FLAG,entry_time) VALUES ('HB0002', '曾宪洲', 0, '2018-3-12 16:40:00', '420881199101095179', '13817191469@163.com', '13817191469', '开发', 1, '2018-3-12 16:40:00');
INSERT INTO `tbl_staff`(staff_code, staff_name,SEX,birthday,card_no,EMAIL,MOBILE,POSITION,VALID_FLAG,entry_time) VALUES ('HB0003', 'test', 0, '2018-3-12 16:40:00', '420881199101095179', '13817191469@163.com', '13817191469', '开发', 1, '2018-3-12 16:40:00');

INSERT INTO `tbl_user`(username,staff_code, PASSWORD) VALUES ('00000012', 'HB0001', '1');
INSERT INTO `tbl_user`(username,staff_code, PASSWORD) VALUES ('00000013', 'HB0002', '1');
INSERT INTO `tbl_user`(username,staff_code, PASSWORD) VALUES ('00000014', 'HB0003', '1');


INSERT INTO `tbl_role` VALUES (1, 'MARKET', '市场', 1, '2018-5-3 13:26:32', '2018-5-3 13:26:32', NULL, NULL, NULL);
INSERT INTO `tbl_role` VALUES (2, 'XLOAN', '信审', 1, '2018-5-8 13:53:57', '2018-5-3 13:26:34', NULL, NULL, NULL);
INSERT INTO `tbl_role` VALUES (3, 'ADMIN', '管理员', 1, '2018-5-7 22:02:54', '2018-5-3 13:26:36', NULL, NULL, NULL);