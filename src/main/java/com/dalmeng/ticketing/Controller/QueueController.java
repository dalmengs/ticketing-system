package com.dalmeng.ticketing.Controller;

import com.dalmeng.ticketing.Component.QueueComponent;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class QueueController {

    private final QueueComponent queueComponent;

    @GetMapping("/request")
    public List<Long> getRequests(){
        return queueComponent.getRequest(5);
    }

}
