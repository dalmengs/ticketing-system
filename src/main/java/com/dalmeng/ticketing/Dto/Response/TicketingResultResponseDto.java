package com.dalmeng.ticketing.Dto.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TicketingResultResponseDto {

    private Long rank;
    private Long userId;

}
