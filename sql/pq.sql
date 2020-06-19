create database pq_chat;
use pq_chat;

create table pq_user_info(
  id int auto_increment not null ,
  nick_name varchar(20) not null comment '用户昵称',
  primary key(id)
) engine innodb default charset = utf8;

create table pq_chat_group(
  id int auto_increment not null,
  group_name varchar(20) not null comment '组名',
  create_time timestamp  not null default current_timestamp comment '建组时间',
  menbers varchar(1000) default '[]' comment 'pq_user_info id',
  last_menber_update_time timestamp not null default current_timestamp comment '组成员最后一次更新的时间',
  primary key(id)
) engine innodb default charset=utf8;

create table pq_user(
  id int auto_increment,
  email varchar(100) not null ,
  password varchar(100) not null ,
  last_login timestamp  not null default current_timestamp comment '最后登录时间',
  groups varchar(1000) default '[]' comment 'pq_chat_group id',
  friends varchar(2000) default '[]' comment '好友的id',
  last_friends_update_time timestamp  not null default current_timestamp comment '联系人最后一次更新的时间',
  last_groups_update_time timestamp  not null default current_timestamp comment '加入的组的最后一次更新的时间',
  user_info_id int not null comment 'pq_user_info id',
  primary key (id)
)engine innodb default charset=utf8;




alter table pq_user add index  idx_email(email);
alter table pq_user add index  idx_user_info_id(user_info_id);


### 增加模拟数据
insert into pq_user values
(2, '123@qq.com','544dc67e997c95fbe529ce88d7a64e379182d796',null ,'[]','[1,3,4,5,6]',null,null , 2),
(3, '124@qq.com','544dc67e997c95fbe529ce88d7a64e379182d796',null ,'[]','[1,2,4,5,6]',null,null , 3),
(4, '125@qq.com','544dc67e997c95fbe529ce88d7a64e379182d796',null ,'[]','[1,2,3,5,6]',null,null , 4),
(5, '126@qq.com','544dc67e997c95fbe529ce88d7a64e379182d796',null ,'[]','[1,2,3,4,6]',null,null , 5),
(6, '127@qq.com','544dc67e997c95fbe529ce88d7a64e379182d796',null ,'[]','[1,2,3,4,5]',null,null , 6);


insert into pq_user_info values
(2 ,'烽火1'),
(3 ,'烽火2'),
(4 ,'烽火3'),
(5 ,'烽火4'),
(6 ,'烽火5');