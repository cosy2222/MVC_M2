drop table member;

create table member(
    id varchar2(10) not null,
    pass varchar2(10) not null,
    name varchar2(30) not null,
    regidate  date default sysdate not null,
    primary key (id)
);


insert into member ( id , pass , name)
values( 'admin' , '1234' , '������' );
insert into member ( id , pass , name)
values( 'kang' , '1234' , '������' );

commit;

select * from member;