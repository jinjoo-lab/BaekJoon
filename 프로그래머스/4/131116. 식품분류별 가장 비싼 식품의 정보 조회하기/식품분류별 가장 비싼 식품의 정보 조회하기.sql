-- 코드를 입력하세요

select A.category, A.price as max_price, A.product_name
from (
    select *, RANK() OVER(PARTITION BY CATEGORY ORDER BY PRICE DESC) RNK
    FROM FOOD_PRODUCT
) A
where A.RNK = 1 AND A.category in ('과자','국','김치','식용유')
order by max_price desc;