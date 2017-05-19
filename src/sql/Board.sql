SELECT * FROM Board WHERE seq_no='1';
SELECT COUNT(*) AS count FROM Board;

SELECT seq_no,title,writer,regi_date,count FROM Board WHERE seq_no='1';
SELECT seq_no,title,writer,count,regi_date FROM Board WHERE seq_no='2';
SELECT seq_no,title,content,writer,regi_date,count FROM Board WHERE seq_no='1';
SELECT * FROM Board;
DELETE  FROM Board WHERE seq_no='43';
UPDATE Board SET title='안녕 사랑이',content='사랑이 사랑스러웡 ㅋㅋ' WHERE seq_no='43';
SELECT seq_no,writer,title,content,regi_date,count FROM Board;