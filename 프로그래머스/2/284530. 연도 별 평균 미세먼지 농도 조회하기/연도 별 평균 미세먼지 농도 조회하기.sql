select year(ym) as year, round(avg(pm_val1),2) as 'pm10', round(avg(pm_val2),2) as 'pm2.5'
from AIR_POLLUTION 
group by year, location1, location2
having location2 = '수원'
order by year;