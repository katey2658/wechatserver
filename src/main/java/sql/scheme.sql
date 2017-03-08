
#创建一个数据库
CREATE database if not EXISTS wechatserver;

USE wechatsever;

# 用户聊天信息表
CREATE TABLE  wc_message{
id unsigned bigint NOT  NULL  auto_increment COMMENT '消息Id',
chat_session_id unsigned bigint NOT NULL  COMMENT '会话ID',
from_user_id unsigned INT  NOT  NULL COMMent '发送用户编号',
to_user_id unsigned INT NOT NULL comment '接收用户',
status tinyint NOT NULL DEFAULT 0 comment '信息状态',
message_text VARCHAR (100) comment '消息文本内容',
gmt_create date_time NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
gmt_modified date_time NOT NULL DEFAULT CURRENT_TIMESTAMP
ON UPDATE CURRENT_TIMESTAMP  comment '最后修改时间',
PRIMARY KEY uk_id(id),
KEY idx_gmtcreate(gmt_create)
}engine=Innodb DEFAULT CHARSET = utf8 COMMENT = '用户聊天信息表' ;

# 创建用户注册信息
CREATE TABLE wc_user{
id unsigned INT NOT NULL  DEFAULT auto_increment comment '用户Id',
username VARCHAR (20) UNION NOT NULL comment '用户名',
telphone VARCHAR (20) UNION NOT NULL comment '用户手机号',
qr_code CHAR (300) NOT NULL comment '用户二维码',
user_mail VARCHAR (20) DEFAULT NULL comment '用户邮箱',
password CHAR (32) NOT NULL comment 'MD5加密的密码',
status tinyint NOT NULL DEFAULT 1 '账户状态:1 正常使用,0 暂未激活 -1 禁用',
gmt_create date_time NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
gmt_modified date_time NOT NULL DEFAULT CURRENT_TIMESTAMP
ON UPDATE CURRENT_TIMESTAMP  comment '最后修改时间',
PRIMARY KEY uk_id(id),
KEY idx_status(status),
KEY idx_username(username),
KEY idx_telephone(telephone)
}engine=innodb DEFAULT CHARSET = utf8 COMMENT =  '用户注册信息表';

# 用户权限表
CREATE TABLE wc_user_authority{
id unsigned bigint NOT NULL auto_increment comment'权限表id',
username VARCHAR (20) NOT NULL comment '用户名',
authority VARCHAR (10) NOT NULL comment '权限',
gmt_create date_time NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
gmt_modified date_time NOT NULL DEFAULT CURRENT_TIMESTAMP
ON UPDATE CURRENT_TIMESTAMP  comment '最后修改时间',
PRIMARY KEY uk_id(id),
KEY idx_username(username)
}engine=innodb DEFAULT CHARSET = utf8 COMMENT =  '用户权限表';
