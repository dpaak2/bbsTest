SELECT * FROM Board WHERE seq_no='1';
SELECT COUNT(*) AS count FROM Board;

SELECT seq_no,title,writer,regi_date,count FROM Board WHERE seq_no='1';
SELECT seq_no,title,writer,count,regi_date FROM Board WHERE seq_no='2';
SELECT seq_no,title,content,writer,regi_date,count FROM Board WHERE seq_no='1';
SELECT * FROM Board;
DELETE  FROM Board WHERE seq_no='43';
UPDATE Board SET title='안녕 사랑이',content='사랑이 사랑스러웡 ㅋㅋ' WHERE seq_no='43';
SELECT seq_no,writer,title,content,regi_date,count FROM Board;



SELECT t2.*
FROM (SELECT ROWNUM seq,t.* 
FROM (SELECT * FROM Board ORDER BY seq_no DESC) t) t2
WHERE t2.seq BETWEEN 11 AND 15;

SELECT t2.* 
FROM (SELECT ROWNUM seq,t.*
FROM (SELECT * FROM Board ORDER BY seq_no DESC) t) t2 WHERE t2.seq BETWEEN 1 AND 5;

SELECT *
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
WHERE C.ROWNUM BETWEEN 11 AND 15;


SELECT *FROM Board ORDER BY seq_no DESC LIMIT 0,5;
--WHERE C.ROWNUM >= 11 AND C.ROWNUM <= 15;
		

