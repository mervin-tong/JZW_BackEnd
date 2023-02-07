package com.piesat.school.biz.ds.job.config;

import com.piesat.school.biz.ds.job.bulider.ScheduleJob;
import org.bouncycastle.jcajce.provider.digest.Tiger;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/02/07/14:54
 * @Description:
 */
@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail jobDetail1(){
        return JobBuilder.newJob(ScheduleJob.class).storeDurably().build();
    }

    @Bean
    public Trigger trigger1(){
//        每日0点开始执行
        CronScheduleBuilder scheduleBuilder=CronScheduleBuilder.cronSchedule("0 0/1 * * * ?");

//        测试定时任务是否运行
//                SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(1)    //每5秒执行一次
//                .repeatForever();


        return TriggerBuilder.newTrigger()
                .forJob(jobDetail1())
                .withSchedule(scheduleBuilder)
                .build();
    }
}
