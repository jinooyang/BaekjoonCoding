-- 코드를 작성해주세요
SELECT 
    B.ITEM_ID,
    B.ITEM_NAME 
FROM 
    ITEM_TREE A, 
    ITEM_INFO B 
WHERE 
    A.ITEM_ID = B.ITEM_ID 
    AND 
    A.PARENT_ITEM_ID IS NULL 
ORDER BY 
    B.ITEM_ID ASC;