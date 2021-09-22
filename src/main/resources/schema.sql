DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS user;


create table payment (
    id bigint not null AUTO_INCREMENT,
    amount decimal(19,2),
    amount_disbursment decimal(19,2),
    status varchar(255),
    user_id varchar(255),
    primary key (id));

create table user (
    user_id varchar(255) not null ,
    name varchar(255),
    primary key (user_id));