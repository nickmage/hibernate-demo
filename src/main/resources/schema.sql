create table person
(
    id         integer      not null,
    name       varchar(255) not null,
    location   varchar(255),
    birth_date timestamp,
    primary key (id)
);

insert into person (id, name, location, birth_date)
values (100001, 'Kolia', 'Kherson', sysdate());
insert into person (id, name, location, birth_date)
values (100002, 'Mykola', 'Kharkiv', sysdate());
insert into person (id, name, location, birth_date)
values (100003, 'Nickolay', 'Kyiv', sysdate());