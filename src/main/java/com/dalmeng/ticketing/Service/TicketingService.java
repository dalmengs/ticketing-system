package com.dalmeng.ticketing.Service;

import com.dalmeng.ticketing.Component.QueueComponent;
import com.dalmeng.ticketing.Dto.Request.TicketingRequestDto;
import com.dalmeng.ticketing.Dto.Response.TicketingResponseDto;
import com.dalmeng.ticketing.Dto.Response.TicketingResultListResponseDto;
import com.dalmeng.ticketing.Dto.Response.TicketingResultResponseDto;
import com.dalmeng.ticketing.Dto.Response.UserTicketingResultResponseDto;
import com.dalmeng.ticketing.Entity.TicketingResult;
import com.dalmeng.ticketing.Repository.TicketingRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketingService {

    private final QueueComponent queueComponent;
    private final TicketingRepository ticketingRepository;

    public TicketingResponseDto ticketingRequest(TicketingRequestDto ticketingRequestDto){
        if(!isRequestValid(ticketingRequestDto)) return new TicketingResponseDto(false);

        queueComponent.addRequest(ticketingRequestDto.getUserId());

        return new TicketingResponseDto(true);
    }

    @Transactional
    public void saveAll(List<Long> requests){
        List<TicketingResult> ticketingResults = new ArrayList<>();
        for(int i = 0; i < requests.size(); i++){
            ticketingResults.add(TicketingResult.builder()
                    .userId(requests.get(i))
                    .build()
            );
        }
        ticketingRepository.saveAll(ticketingResults);
    }

    @Transactional
    public TicketingResultListResponseDto findAll(){
        List<TicketingResult> results = ticketingRepository.findAll();

        TicketingResultListResponseDto ticketingResults = new TicketingResultListResponseDto();
        for(int i = 0; i < results.size(); i++){
            ticketingResults.getResults().add(TicketingResultResponseDto.builder()
                    .rank(i + 1L)
                    .userId(results.get(i).getUserId())
                    .build()
            );
        }

        return ticketingResults;
    }

    @Transactional
    public UserTicketingResultResponseDto findById(TicketingRequestDto ticketingRequestDto){
        return UserTicketingResultResponseDto.builder()
                .isSucceed(ticketingRepository.existsByUserId(ticketingRequestDto.getUserId()))
                .build();
    }

    private boolean isRequestValid(TicketingRequestDto ticketingRequestDto){
        if(ticketingRequestDto == null) return false;
        return ticketingRequestDto.getUserId() != null && ticketingRequestDto.getUserId() > 0;
    }

}
