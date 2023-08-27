package com.dalmeng.ticketing.Service;

import com.dalmeng.ticketing.Component.QueueComponent;
import com.dalmeng.ticketing.Dto.Request.TicketingRequestDto;
import com.dalmeng.ticketing.Dto.Response.TicketingResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketingService {

    private final QueueComponent queueComponent;

    public TicketingResponseDto ticketingRequest(TicketingRequestDto ticketingRequestDto){
        if(!isRequestValid(ticketingRequestDto)) return new TicketingResponseDto(false);

        queueComponent.addRequest(ticketingRequestDto.getUserId());

        return new TicketingResponseDto(true);
    }

    private boolean isRequestValid(TicketingRequestDto ticketingRequestDto){
        if(ticketingRequestDto == null) return false;
        return ticketingRequestDto.getUserId() != null && ticketingRequestDto.getUserId() > 0;
    }

}
