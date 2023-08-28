package com.dalmeng.ticketing.Scheduler;

import com.dalmeng.ticketing.Component.QueueComponent;
import com.dalmeng.ticketing.Service.TicketingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class TicketingProcessingScheduler {

    private final QueueComponent queueComponent;
    private final TicketingService ticketingService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void processingTicketing(){

        // 현재 티켓팅에 성공한 사람 수
        int succeedCount = queueComponent.getSucceedCount();
        // 티켓팅 정원
        int totalSucceedCount = queueComponent.getTotalSucceedCount();

        // 티켓이 아직 남았으면...
        if(succeedCount < totalSucceedCount){
            // Batch Size 만큼 대기열에서 데이터를 가져온다.
            List<Long> requests = queueComponent.getRequest(queueComponent.getBatchSize());

            // 가져갈 수 있는 티켓의 개수
            int processCount = Math.min(totalSucceedCount - succeedCount, requests.size());

            String temp = "Succeed User Id = [";

            ticketingService.saveAll(requests.subList(0, processCount));


            for(int i = 0; i < processCount; i++){
                if(i < processCount - 1) temp += String.format("%d, ", requests.get(i));
                else temp += String.format("%d]", requests.get(i));
            }


            // 티켓팅에 성공한 사람 수를 더해준다.
            queueComponent.addSucceedCount(processCount);

            if(queueComponent.getSucceedCount() == totalSucceedCount){
                log.info("---------- Ticketing End!!! ----------");
            }
        }
        // 티켓팅이 끝났으면...
        else{
            List<Long> requests = queueComponent.getRequest(100);

            String temp = "Failed User Id = [";

            for(int i = 0; i < requests.size(); i++){
                if(i < requests.size() - 1) temp += String.format("%d, ", requests.get(i));
                else temp += String.format("%d]", requests.get(i));
            }
            log.info(temp);
        }

        log.info("Result - [" + queueComponent.getSucceedCount() +
                "/" + queueComponent.getTotalSucceedCount() + "]");
    }
}


