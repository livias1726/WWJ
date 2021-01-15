DELIMITER $$

CREATE DEFINER = `root`@`localhost`
EVENT IF NOT EXISTS event_exp_notif ON SCHEDULE EVERY 1 DAY
ON COMPLETION PRESERVE 
DO 
	BEGIN
		declare seek_id int;
        declare offer_id int;
        
		declare done int default false;
		declare cur cursor for (select `favoffer`.id_fav, `favoffer`.id_user from `favoffer`);
        
        declare continue handler for not found set done = true;
        
        open cur;
		list_loop: loop
        
			fetch cur into offer_id, seek_id;
			if done then
				leave list_loop;
			end if;
		
			
			
			if (select `favoffer`.id_fav, `favoffer`.id_user 
				from `favoffer` join `offer` on `favoffer`.id_fav = `offer`.id
				where `offer`.expiration < DATE_ADD(curdate(), INTERVAL -3 day) 
                and `favoffer`.id_fav = offer_id and `favoffer`.id_user = seek_id) then
			insert into `notification` (`id_account`, `content`, `viewed`) values (seek_id, "One of your favourite offer (num. " + offer_id + ") is about to expire", 0);
			end if;
            
		end loop;
		close cur;
    END$$
  
DELIMITER ;