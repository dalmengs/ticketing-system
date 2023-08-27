package com.dalmeng.ticketing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.dalmeng.ticketing.Dto.Request.TicketingRequestDto;
import com.dalmeng.ticketing.Dto.Response.TicketingResponseDto;
import com.dalmeng.ticketing.Service.TicketingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TicketingTest {

    @Autowired
    private TicketingService ticketingService;

    @Test
    @DisplayName("Ticking Service Test: Succeed")
    void ticketingTest1(){
        TicketingResponseDto ticketingResponseDto = ticketingService.ticketingRequest(new TicketingRequestDto(1L));

        assertNotNull(ticketingResponseDto);
        assertEquals(ticketingResponseDto.getResult(), true);
    }

    @Test
    @DisplayName("Ticking Service Test: Without User ID")
    void ticketingTest2(){
        TicketingResponseDto ticketingResponseDto = ticketingService.ticketingRequest(new TicketingRequestDto());

        assertNotNull(ticketingResponseDto);
        assertEquals(ticketingResponseDto.getResult(), false);
    }

    @Test
    @DisplayName("Ticking Service Test: Invalid User ID; userId = 0")
    void ticketingTest3(){
        TicketingResponseDto ticketingResponseDto = ticketingService.ticketingRequest(new TicketingRequestDto(0L));

        assertNotNull(ticketingResponseDto);
        assertEquals(ticketingResponseDto.getResult(), false);
    }

    @Test
    @DisplayName("Ticking Service Test: Invalid User ID; userId < 0")
    void ticketingTest4(){
        TicketingResponseDto ticketingResponseDto = ticketingService.ticketingRequest(new TicketingRequestDto(-324L));

        assertNotNull(ticketingResponseDto);
        assertEquals(ticketingResponseDto.getResult(), false);
    }

    @Test
    @DisplayName("Ticking Service Test: Empty Object")
    void ticketingTest5(){
        TicketingResponseDto ticketingResponseDto = ticketingService.ticketingRequest(null);

        assertEquals(ticketingResponseDto.getResult(), false);
    }
}
