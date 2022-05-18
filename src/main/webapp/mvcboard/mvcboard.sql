create table mvcboard(
    idx number primary key, 
    name varchar2(50) not null,
    title varchar2(200) not null,
    content varchar2(2000) not null,
    postdate date default sysdate not null,
    ofile varchar2(200),
    sfile varchar2(50),
    downcount number(5) default 0 not null,
    pass varchar2 (50) not null,
    visitcount number default 0 not null 
);

create sequence seq_board_num
    increment by 1
    start with 1
    nocache;
    
-- ���� ������ �Է� ( 5������ ���Է�) 
insert into mvcboard (idx, name , title , content , pass)
values ( seq_board_num.nextval , '������1' , '�ڷ�� ����1' , '����1' , '1234');
insert into mvcboard (idx, name , title , content , pass)
values ( seq_board_num.nextval , '������2' , '�ڷ�� ����2' , '����2' , '1234');
insert into mvcboard (idx, name , title , content , pass)
values ( seq_board_num.nextval , '������3' , '�ڷ�� ����3' , '����3' , '1234');
insert into mvcboard (idx, name , title , content , pass)
values ( seq_board_num.nextval , '������4' , '�ڷ�� ����4' , '����4' , '1234');
insert into mvcboard (idx, name , title , content , pass)
values ( seq_board_num.nextval , '������5' , '�ڷ�� ����5' , '����5' , '1234');

select * from mvcboard;

commit;