create table user
(
    id           bigint auto_increment comment 'user id'
        primary key,
    username     varchar(256)                       null comment 'user name',
    userAccount  varchar(256)                       null comment 'user account',
    avatarUrl    varchar(1024)                      null comment 'user avatar',
    gender       tinyint                            null comment 'user gender',
    userPassword varchar(512)                       not null comment 'user password',
    phone        varchar(128)                       null comment 'user phone number',
    email        varchar(512)                       null comment 'user email',
    userStatus   int      default 0                 not null comment '0 - normal
1 - ...',
    createTime   datetime default CURRENT_TIMESTAMP null comment 'first create time',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment 'last update time',
    isDelete     tinyint  default 0                 not null comment '0 - not delete
1 - deleted',
    userRole     int      default 0                 not null comment 'identity: 0 - normal user, 1 - admin'
)
    comment 'user_table';

