insert into course (id, name, last_updated_at, created_at) values (1001, 'Math', current_timestamp(), current_timestamp());
insert into course (id, name, last_updated_at, created_at) values (1002, 'Chemistry', current_timestamp(), current_timestamp());
insert into course (id, name, last_updated_at, created_at) values (1003, 'Physics', current_timestamp(), current_timestamp());

insert into passport (id, number) values ( 2001, 'MP145364NT' );
insert into passport (id, number) values ( 2002, 'VS459025MR' );
insert into passport (id, number) values ( 2003, 'KU753093LE' );

insert into student (id, name, passport_id) values ( 1001, 'Leon', 2001 );
insert into student (id, name, passport_id) values ( 1002, 'Victor', 2002 );
insert into student (id, name, passport_id) values ( 1003, 'Peter', 2003 );

insert into review (id, rating, description, course_id) values ( 3001, '4', 'OK', 1001);
insert into review (id, rating, description, course_id) values ( 3002, '5', 'Good', 1001);
insert into review (id, rating, description, course_id) values ( 3003, '5', 'Great', 1003);