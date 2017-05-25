SELECT * FROM Board WHERE seq_no='1';
SELECT COUNT(*) AS count FROM Board;
select * from Board;
SELECT seq_no,title,writer,regi_date,count FROM Board WHERE seq_no='1';
SELECT seq_no,title,writer,count,regi_date FROM Board WHERE seq_no='2';
SELECT seq_no,title,content,writer,regi_date,count FROM Board WHERE seq_no='1';
SELECT * FROM Board;
DELETE  FROM Board WHERE seq_no='43';
UPDATE Board SET title='안녕 사랑이',content='사랑이 사랑스러웡 ㅋㅋ' WHERE seq_no='43';
SELECT seq_no,writer,title,content,regi_date,count FROM Board;
SELECT COUNT(*) AS qty FROM Board WHERE writer LIKE '%홍길동%';
SELECT * FROM Board WHERE title='봄바람';
SELECT COUNT (writer='홍길동') AS count FROM Board;
SELECT COUNT (*) FROM Board WHERE writer='홍길동';
SELECT COUNT(*) AS qty  FROM Board WHERE writer='홍길동';
SELECT * FROM Board WHERE title LIKE '%봄바람%';
SELECT * FROM Board WHERE title='북치기 박치기';

UPDATE Board SET title='"+article.getTitle()+"',content='"+article.getContent()+"' WHERE seq_no='"+article.getSeqNo()+';
UPDATE Board  SET title='반석어린이집', writer='나갱이',content='칼퇴는 나의 소망',regi_date='2017-05-23',count='29' WHERE seq_no='48';
UPDATE Board  SET writer='찌노닝',content='퇴사는 나의 소망',regi_date='2017-05-24',count='29' WHERE seq_no='41';
UPDATE Board  SET writer='닝노찌',content='하지만 나는 집에서 일하고 있지... 벗어날수가 없어!!',regi_date='2017-05-24',count='29' WHERE seq_no='39';

SELECT COUNT(*) AS qty  FROM Board WHERE writer ;
SELECT * FROM Board WHERE title LIKE "%뼈 쓸쓸한 %";
SELECT * FROM Board WHERE title LIKE "%길동이%";
FROM Board
ORDER BY seq_no DESC
LIMIT 0, 5;

SELECT *
FROM (
SELECT @NO := @NO + 1 AS ROWNUM, A.*
FROM
  (
    SELECT *
    FROM Board
  ) A,
  ( SELECT @NO := 0 ) B 
) C
WHERE C.ROWNUM BETWEEN 1 AND 5;


SELECT *FROM Board ORDER BY seq_no DESC LIMIT 0,5;
--WHERE C.ROWNUM >= 11 AND C.ROWNUM <= 15;
		

