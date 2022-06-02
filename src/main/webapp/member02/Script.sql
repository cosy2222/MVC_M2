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
values( 'admin@aaa.com' , '1234'  , '관리자' , default , '서울' , '010' , sysdate);

insert into member02
values( 'kang@aaa.com' , '1234'  , '강성민' , default , '경기도' , '011' , sysdate);

commit;

logon02  : LogonDTO.java   , LogonDAO.java
member02 : 회원가입처리 (DB에 Insert) , ID와 Password인증 처리 :   <== AJAX사용해서 처리 