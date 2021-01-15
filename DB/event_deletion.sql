DELIMITER $$

CREATE DEFINER = `root`@`localhost`
EVENT IF NOT EXISTS event_deletion ON SCHEDULE EVERY 1 DAY
ON COMPLETION PRESERVE 
DO 
	BEGIN
        declare offer_id int;
        
		declare done int default false;
		declare cur cursor for (select `favoffer`.id_fav from `favoffer`);
        
        declare continue handler for not found set done = true;
        
        open cur;
		list_loop: loop
        
			fetch cur into offer_id, seek_id;
			if done then
				leave list_loop;
			end if;

			if (select `favoffer`.id_fav from `favoffer` 
				join `offer` on `favoffer`.id_fav = `offer`.id
				where `offer`.expiration < curdate() and `favoffer`.id_fav = offer_id) then
			delete from `favoffer` where `favoffer`.id_fav = offer_id;
			end if;
            
		end loop;
		close cur;
    END$$
  
DELIMITER ;