create table tbUser(
	id int auto_increment not null PRIMARY key comment '主键',
	name varchar(64) not null comment '姓名',
	age tinyint not null comment '年龄',
	version int default 0 not null comment '版本'
);

create table tbStock(
	id int auto_increment not null PRIMARY key comment '主键',
	quantity long not null comment '库存量'
);
