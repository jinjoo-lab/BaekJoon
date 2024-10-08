-- 코드를 입력하세요
SELECT UU.USER_ID AS USER_ID, UU.NICKNAME AS NICKNAME, UBB.TOTAL_PRICE AS TOTAL_PRICE
FROM USED_GOODS_USER UU
JOIN (
    SELECT UB.WRITER_ID AS USER_ID, SUM(UB.PRICE) AS TOTAL_PRICE
    FROM USED_GOODS_BOARD UB
    WHERE UB.STATUS LIKE 'DONE'
    GROUP BY UB.WRITER_ID
) UBB
ON UU.USER_ID = UBB.USER_ID WHERE TOTAL_PRICE >= 700000
ORDER BY TOTAL_PRICE;



