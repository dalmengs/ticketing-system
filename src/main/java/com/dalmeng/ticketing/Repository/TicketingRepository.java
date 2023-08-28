package com.dalmeng.ticketing.Repository;

import com.dalmeng.ticketing.Entity.TicketingResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketingRepository extends JpaRepository<TicketingResult, Long> {
    boolean existsByUserId(long userId);
}

