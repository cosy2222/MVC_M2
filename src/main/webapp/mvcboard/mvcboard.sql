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
    
-- 더미 데이터 입력 ( 5개정도 값입력) 
insert into mvcboard (idx, name , title , content , pass)
values ( seq_board_num.nextval , '강성민1' , '자료실 제목1' , '내용1' , '1234');
insert into mvcboard (idx, name , title , content , pass)
values ( seq_board_num.nextval , '강성민2' , '자료실 제목2' , '내용2' , '1234');
insert into mvcboard (idx, name , title , content , pass)
values ( seq_board_num.nextval , '강성민3' , '자료실 제목3' , '내용3' , '1234');
insert into mvcboard (idx, name , title , content , pass)
values ( seq_board_num.nextval , '강성민4' , '자료실 제목4' , '내용4' , '1234');
insert into mvcboard (idx, name , title , content , pass)
values ( seq_board_num.nextval , '강성민5' , '자료실 제목5' , '내용5' , '1234');

select * from mvcboard;

commit;


-- DB에서 특정 레코드만 출력 할때 (검색 없이 출력) 
SELECT * FROM (     
SELECT Tb.*, ROWNUM rNum fROM (         
SELECT * FROM mvcboard   ORDER BY idx DESC) Tb )  WHERE rNum BETWEEN 1 AND 4;

-- 검색기능을 사용해서 출력 할떄 
SELECT * FROM (     
SELECT Tb.*, ROWNUM rNum fROM (         
SELECT * FROM mvcboard where title like '%제목%'  ORDER BY idx DESC) Tb ) 
WHERE rNum BETWEEN 1 AND 4;















