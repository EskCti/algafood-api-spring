CREATE TABLE kitchen (
    id BIGINT not null auto_increment,
    name VARCHAR(60) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;