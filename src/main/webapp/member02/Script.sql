create table member02 (
    u_id varchar2 (50) not null primary key,
    u_pass varchar2 (50) not null , 
    u_name varchar2 (50) not null,
    r_date date default sysdate not null,
    u_address varchar2 (200) not null,
    u_tel varchar2 (50) not null ,
    u_birthday date not null
);

select * from member02;

insert into member02
values( 'admin@aaa.com' , '1234'  , '������' , default , '����' , '010' , sysdate);

insert into member02
values( 'kang@aaa.com' , '1234'  , '������' , default , '��⵵' , '011' , sysdate);

commit;

logon02  : LogonDTO.java   , LogonDAO.java
member02 : ȸ������ó�� (DB�� Insert) , ID�� Password���� ó�� :   <== AJAX����ؼ� ó�� 