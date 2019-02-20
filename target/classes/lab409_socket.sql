drop table if exists `sensor_msg`;
drop table if exists `sensor_config`;
drop table if exists `sensor`;
drop table if exists `group_detail`;
drop table if exists `sensor_group`;
drop table if exists `sensor_type`;
drop table if exists `sensor_state`;
drop table if exists `user`;


create table `sensor_type`(
  `typename` varchar(20) not null comment '主键',
  primary key (`typename`)
)ENGINE = InnoDB DEFAULT charset=utf8;


create table `sensor_state`(
  `state` varchar(20) not null comment '主键',
  primary key (`state`)
)ENGINE = InnoDB DEFAULT charset=utf8;


create table `sensor_group` (
  `id` bigint(20) not null auto_increment comment '主键',
  `name` varchar(50) default 'Unnamed' comment 'group name',
  `create_user` varchar(20) default 'admin' comment '创始人',
  `create_time` datetime default now() comment '创造时间',
  primary key (`id`)
)ENGINE = InnoDB DEFAULT charset=utf8;


create table `group_detail` (
  `group_id` bigint(20) not null comment '传感器配置id',
  `type` varchar(20) not null comment '传感器类型',
  `num` bigint(20) default 0 comment '该类型的数量',
  primary key (`group_id`, `type`),
  foreign key (`group_id`) references sensor_group(`id`),
  foreign key (`type`) references sensor_type(`typename`)
)ENGINE =InnoDB DEFAULT charset = utf8;


create table `sensor` (
  `id` bigint(20) not null auto_increment comment '主键',
  `group_id` bigint(20) not null comment '属于哪一个配置',
  `type` varchar(20) default 'thunder'  comment '传感器类型',
  `descr` varchar(200) default 'made in china' comment '传感器描述',
  `host` varchar(20) default '127.0.0.1' comment '传感器host',
  `port` varchar(10) default '8080' comment '传感器port',
  `state` varchar(20) default 'offline' comment '传感器是否在线',
  `msg` varchar(200) default null comment '该传感器的最近一条消息',
  `time` datetime default now() comment '该传感器状态最近一次更新的时间',
  `interval` bigint(20) default 1000 comment '改传感器发送消息的时间间隔(毫秒数)',
  primary key (`id`),
  foreign key (`group_id`) references `sensor_group`(`id`),
  foreign key (`type`) references `sensor_type`(`typename`),
  foreign key (`state`) references `sensor_state`(`state`)
)ENGINE = InnoDB DEFAULT charset=utf8;

create table `sensor_config`(
  `sensor_id` bigint(20) not null,
  primary key (`sensor_id`),
  foreign key (`sensor_id`) references `sensor`(`id`)
)ENGINE = InnoDB default charset = utf8;

create table `sensor_msg` (
  `id` bigint(20) not null auto_increment comment '主键',
  `sensor_id` bigint(20) not null comment '传感器ID',
  `msg` varchar(1000) default null comment '传感器发送过来的消息',
  `time` datetime default now() comment '该信息发送过来的时刻',
  primary key (`id`),
  foreign key (`sensor_id`) references `sensor`(`id`)
)ENGINE = InnoDB DEFAULT charset = utf8;


create table `user` (
  `id` bigint(20) not null auto_increment comment '主键',
  `name` varchar(20) not null default 'admin' comment '用户名',
  `pwd` varchar(20) not null default 'admin' comment '密码',
  primary key (`id`)
)ENGINE = InnoDB DEFAULT charset=utf8;


insert into `user`() values ();
insert into `user`(`name`,`pwd`) values ('user','123456');

insert into `sensor_state` values ('offline');
insert into `sensor_state` values ('online');

insert into `sensor_type` values ('thunder');
insert into `sensor_type` values ('temperature');
insert into `sensor_type` values ('humidity');
insert into `sensor_type` values ('pressure');

insert into `sensor_group`(`name`,`create_user`,`create_time`) values ('test','admin',now());

insert into `group_detail`(`group_id`,`type`,`num`) values ('1','thunder',2);
insert into `group_detail`(`group_id`,`type`,`num`) values ('1','temperature',2);
insert into `group_detail`(`group_id`,`type`,`num`) values ('1','humidity',2);
insert into `group_detail`(`group_id`,`type`,`num`) values ('1','pressure',2);

insert into `sensor`(`group_id`,`type`,`port`,`time`) values (1,'thunder','65521',now());
insert into `sensor`(`group_id`,`type`,`port`,`time`) values (1,'thunder','65522',now());
insert into `sensor`(`group_id`,`type`,`port`,`time`) values (1,'humidity','65523',now());
insert into `sensor`(`group_id`,`type`,`port`,`time`) values (1,'humidity','65524',now());
insert into `sensor`(`group_id`,`type`,`port`,`time`) values (1,'temperature','65525',now());
insert into `sensor`(`group_id`,`type`,`port`,`time`) values (1,'temperature','65526',now());
insert into `sensor`(`group_id`,`type`,`port`,`time`) values (1,'pressure','65527',now());
insert into `sensor`(`group_id`,`type`,`port`,`time`) values (1,'pressure','65528',now());
#insert into `sensor` (`group_id`, `type`, `port`, `time`,`interval`) values (1, 'pressure', '65528',now(),1);



insert into `sensor_msg`(`sensor_id`,`msg`) values (1,'today dont have thunder');
insert into `sensor_msg`(`sensor_id`,`msg`) values (1,'tomorrow will have thunder');
insert into `sensor_msg`(`sensor_id`,`msg`) values (2,'today dont have thunder');
insert into `sensor_msg`(`sensor_id`,`msg`) values (2,'tomorrow will have thunder');
insert into `sensor_msg`(`sensor_id`,`msg`) values (3,'today temperature is high');
insert into `sensor_msg`(`sensor_id`,`msg`) values (3,'tomorrow temperature will be low');
insert into `sensor_msg`(`sensor_id`,`msg`) values (4,'today temperature is high');
insert into `sensor_msg`(`sensor_id`,`msg`) values (4,'tomorrow temperature will be low');
insert into `sensor_msg`(`sensor_id`,`msg`) values (5,'today humidity is low');
insert into `sensor_msg`(`sensor_id`,`msg`) values (5,'tomorrow humidity will be high');
insert into `sensor_msg`(`sensor_id`,`msg`) values (6,'today humidity is low');
insert into `sensor_msg`(`sensor_id`,`msg`) values (6,'tomorrow humidity will be high');
insert into `sensor_msg`(`sensor_id`,`msg`) values (7,'no pressure');
insert into `sensor_ms`(`sensor_id`,`msg`) values (7,'detect pressure');
insert into `sensor_msg`(`sensor_id`,`msg`) values (8,'no pressure');
insert into `sensor_msg`(`sensor_id`,`msg`) values (8,'detect pressure');


#select * from sensor_group sc ,sensor s where  sc.id = s.group_id and s.type = 'thunder';

#select * from sensor;


select * from sensor_msg where sensor_id = 1 order by id desc limit 1

#select count(*) from sensor_msg where sensor_id = 1