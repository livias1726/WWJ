DELIMITER $$

CREATE DEFINER = `root`@`localhost`
EVENT IF NOT EXISTS event_exp_recr ON SCHEDULE EVERY 1 DAY
ON COMPLETION PRESERVE 
DO 
	BEGIN
		declare rec_id int;
        declare offer_id int;
	
		declare done int default false;
		declare cur cursor for (select `offer`.id, `offer`.recruiter from `offer`);
        
        declare continue handler for not found set done = true;
        
        open cur;
		list_loop: loop
        
			fetch cur into offer_id, rec_id;
			if done then
				leave list_loop;
			end if;
            
			if (select `offer`.id, `offer`.recruiter from `offer`
				where `offer`.expiration < curdate() 
                and `offer`.id = offer_id and `offer`.recruiter = rec_id) then
			insert into `notification` (`id_account`, `content`, `viewed`) values (rec_id, "Offer num. " + offer_id + " has expired", 0);
			end if;
            
		end loop;
		close cur;
    END$$
  
DELIMITER ;