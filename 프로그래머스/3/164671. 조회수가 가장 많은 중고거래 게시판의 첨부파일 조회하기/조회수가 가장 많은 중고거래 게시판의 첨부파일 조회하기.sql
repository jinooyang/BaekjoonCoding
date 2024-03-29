-- 코드를 입력하세요
# USED_GOODS_BOARD 
# USED_GOODS_FILE 


select concat('/home/grep/src/',a.board_id,'/',file_id,file_name,file_ext) as file_path from USED_GOODS_FILE a, (

select board_id from USED_GOODS_BOARD
where views = (select max(views) from USED_GOODS_BOARD)) as b

where a.board_id = b.board_id
order by a.file_id desc;


