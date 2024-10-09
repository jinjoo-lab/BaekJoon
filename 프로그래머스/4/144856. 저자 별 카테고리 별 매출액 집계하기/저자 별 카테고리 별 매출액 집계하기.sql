-- 코드를 입력하세요
SELECT
    B.AUTHOR_ID AS AUTHOR_ID,
    C.AUTHOR_NAME AS AUTHOR_NAME,
    B.CATEGORY AS CATEGORY,
    SUM(B.PRICE * A.TOTAL) AS TOTAL_SALES
FROM BOOK B JOIN
(
    SELECT 
        BS.BOOK_ID AS BOOK_ID,
        SUM(BS.SALES) AS TOTAL
    FROM BOOK_SALES BS 
    WHERE YEAR(BS.SALES_DATE) = 2022 AND MONTH(BS.SALES_DATE) = 1
    GROUP BY BOOK_ID
) A ON B.BOOK_ID = A.BOOK_ID JOIN AUTHOR C ON B.AUTHOR_ID = C.AUTHOR_ID
GROUP BY AUTHOR_ID, CATEGORY
ORDER BY AUTHOR_ID, CATEGORY DESC;