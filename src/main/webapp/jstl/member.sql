drop table member;

create table member(
    id varchar2(100) not null primary key,
    password varchar2(100) ,
    name varchar2(100)
);



insert into member
values('1' , '1234' , '°­¼º¹Î');

insert into member
values('2' , '1235' , '°­¼º¹Î2');

select * from member;

commit;