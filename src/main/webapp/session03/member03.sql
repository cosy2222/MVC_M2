drop table member03;

create table member03(
    id varchar2(10) not null,
    pass varchar2(10) not null,
    name varchar2(30) not null,
    regidate  date default sysdate not null,
    grade varchar2(50) not null,
    primary key (id)
);

insert into member03 ( id , pass , name , grade)
values( 'admin' , '1234' , '包府磊' , 'vip');
insert into member03 ( id , pass , name , grade)
values( 'kang' , '1234' , '碍己刮1' , 'gold' );
insert into member03 ( id , pass , name , grade)
values( 'min' , '1234' , '碍己刮2' , 'silver' );

select * from member03;

commit;