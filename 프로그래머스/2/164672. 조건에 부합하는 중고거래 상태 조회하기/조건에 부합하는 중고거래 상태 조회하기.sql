select  a.board_id,
        a.writer_id, 
        a.title, 
        a.price,
        case 
        when a.status='DONE' then '거래완료'
        when a.status='SALE' then '판매중'
        when a.status='RESERVED' then '예약중'
        end as status
from used_goods_board a 
where created_date = '2022-10-05'
order by a.board_id desc;
