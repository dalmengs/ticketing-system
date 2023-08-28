package com.dalmeng.ticketing.Controller;

import com.dalmeng.ticketing.Dto.Request.TicketingRequestDto;
import com.dalmeng.ticketing.Dto.Response.TicketingResponseDto;
import com.dalmeng.ticketing.Dto.Response.TicketingResultListResponseDto;
import com.dalmeng.ticketing.Dto.Response.UserTicketingResultResponseDto;
import com.dalmeng.ticketing.Service.TicketingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class TicketingController {

    private final TicketingService ticketingService;

    @PostMapping("/ticketing")
    public TicketingResponseDto ticketing(@RequestBody TicketingRequestDto ticketingRequestDto){
        return ticketingService.ticketingRequest(ticketingRequestDto);
    }

    @GetMapping("/ticketing")
    public TicketingResultListResponseDto results(){
        return ticketingService.findAll();
    }

    @GetMapping("/ticketing/user")
    public UserTicketingResultResponseDto result(@RequestBody TicketingRequestDto ticketingRequestDto){
        return ticketingService.findById(ticketingRequestDto);
    }

}
