package com.dalmeng.ticketing;

import com.dalmeng.ticketing.Component.QueueComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class QueueTest {

    @Autowired
    private QueueComponent queueComponent;

    @BeforeEach
    void emptyQueue(){
        queueComponent.emptyQueue();
    }

    @Test
    @DisplayName("Get Queue Object")
    void queueTest1(){
        Queue<Long> queue = queueComponent.getQueue();
        assertNotNull(queue);
    }

    @Test
    @DisplayName("Get Queue Size")
    void queueTest2(){
        Queue<Long> queue = queueComponent.getQueue();
        assertEquals(queueComponent.getSize(), 0);
    }

    @Test
    @DisplayName("Add Object to Queue")
    void queueTest3(){
        queueComponent.addRequest(1L);

        assertEquals(queueComponent.getSize(), 1);
    }

    @Test
    @DisplayName("Add Many Object to Queue")
    void queueTest4(){
        for(int i = 0; i < 10; i++) queueComponent.addRequest(i + 1L);

        assertEquals(queueComponent.getSize(), 10);
    }

    @Test
    @DisplayName("Get Object from Queue")
    void queueTest5(){
        for(int i = 0; i < 10; i++) queueComponent.addRequest(10L - i);

        List<Long> requests = queueComponent.getRequest(1);

        assertEquals(requests.size(), 1);
        assertEquals(requests.get(0), 10);
    }

    @Test
    @DisplayName("Get Many Object from Queue")
    void queueTest6(){
        for(int i = 0; i < 10; i++) queueComponent.addRequest(10L - i);

        List<Long> requests = queueComponent.getRequest(4);

        assertEquals(requests.size(), 4);
        assertEquals(requests.get(0), 10);
        assertEquals(requests.get(1), 9);
        assertEquals(requests.get(2), 8);
        assertEquals(requests.get(3), 7);
    }

    @Test
    @DisplayName("Get Object from Empty Queue")
    void queueTest7(){
        List<Long> requests = queueComponent.getRequest(4);

        assertEquals(requests.size(), 0);
    }

    @Test
    @DisplayName("Get Many Object More Than Queue Size")
    void queueTest8(){
        queueComponent.addRequest(1L);
        queueComponent.addRequest(3L);
        queueComponent.addRequest(2L);
        List<Long> requests = queueComponent.getRequest(5);

        assertEquals(requests.size(), 3);
        assertEquals(requests.get(0), 1);
        assertEquals(requests.get(1), 3);
        assertEquals(requests.get(2), 2);
    }
}
