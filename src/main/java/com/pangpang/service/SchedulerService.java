package com.pangpang.service;

import com.pangpang.aop.LoggerManage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by leewake on 2017/8/1 0001.
 */

@Component
public class SchedulerService {

    private int count = 0;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

/*    @Scheduled(cron = "*//*6 * * * * ?")
    private void process(){
        System.out.println("this is scheduler task running " + count++);
    }

    @Scheduled(fixedRate = 10000)
    private void printTime(){
        System.out.println("现在时间是" + dateFormat.format(new Date()));
    }*/
}
