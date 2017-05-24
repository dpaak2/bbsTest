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

SELECT * FROM Board WHERE writer='홍길동';
SELECT COUNT (writer='홍길동') AS count FROM Board;
SELECT COUNT (*) FROM Board WHERE writer='홍길동';
SELECT COUNT(*) AS qty  FROM Board WHERE writer='홍길동';


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
		

