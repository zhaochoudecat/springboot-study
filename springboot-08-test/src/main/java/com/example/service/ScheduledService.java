package com.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    @Scheduled(cron="0 * * * * 0-7")
    public void hello(){
        System.out.println("你被执行了...");
    }
}
