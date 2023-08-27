package com.dalmeng.ticketing.Controller;

import com.dalmeng.ticketing.Dto.Request.TicketingRequestDto;
import com.dalmeng.ticketing.Dto.Response.TicketingResponseDto;
import com.dalmeng.ticketing.Service.TicketingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class TicketingController {

    private final TicketingService ticketingService;

    @PostMapping("/ticketing")
    public TicketingResponseDto ticketing(@RequestBody TicketingRequestDto ticketingRequestDto){
        log.info("Request User ID = " + ticketingRequestDto.getUserId());
        return ticketingService.ticketingRequest(ticketingRequestDto);
    }

}
