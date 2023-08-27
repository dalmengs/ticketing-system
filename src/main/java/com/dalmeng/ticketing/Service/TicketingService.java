package com.dalmeng.ticketing.Service;

import com.dalmeng.ticketing.Dto.Request.TicketingRequestDto;
import com.dalmeng.ticketing.Dto.Response.TicketingResponseDto;
import org.springframework.stereotype.Service;

@Service
public class TicketingService {

    public TicketingResponseDto ticketingRequest(TicketingRequestDto ticketingRequestDto){
        if(!isRequestValid(ticketingRequestDto)) return new TicketingResponseDto(false);
        return new TicketingResponseDto(true);
    }

    private boolean isRequestValid(TicketingRequestDto ticketingRequestDto){
        if(ticketingRequestDto == null) return false;
        return ticketingRequestDto.getUserId() != null && ticketingRequestDto.getUserId() > 0;
    }

}
