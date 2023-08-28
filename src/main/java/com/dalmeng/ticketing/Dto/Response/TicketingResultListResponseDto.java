package com.dalmeng.ticketing.Dto.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TicketingResultListResponseDto {
    List<TicketingResultResponseDto> results;

    public TicketingResultListResponseDto(){
        this.results = new ArrayList<>();
    }
}
