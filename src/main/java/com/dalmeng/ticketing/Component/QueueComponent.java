package com.dalmeng.ticketing.Component;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Component
public class QueueComponent {

    private static final Queue<Long> queue = new LinkedList<>();

    public boolean addRequest(Long userId){
        try{
            queue.add(userId);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public Queue<Long> getQueue(){
        return queue;
    }

    public int getSize(){
        return queue.size();
    }

    public void emptyQueue(){
        while(!queue.isEmpty()) queue.poll();
    }

    public List<Long> getRequest(int cnt){
        List<Long> requests = new ArrayList<>();

        if(cnt <= 0) return requests;

        for(int i = 0; i < cnt; i++){
            Long request = queue.poll();
            if(request == null) break;
            requests.add(request);
        }

        return requests;
    }

}
