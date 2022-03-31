create table user
(
    id    int(20)     not null
        primary key,
    name  varchar(30) null,
    pwd   varchar(20) null,
    perms varchar(20) null
);