package com.piesat.school.biz.ds.job.bulider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.piesat.school.biz.ds.datainf.entity.DataShareinf;
import com.piesat.school.biz.ds.datainf.mapper.DataShareinfMapper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/02/07/10:51
 * @Description: 定时任务，保质期7天，删除文件
 */
public class ScheduleJob extends QuartzJobBean {

    @Resource
    private DataShareinfMapper dataShareinfMapper;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalDate date = LocalDate.now();
//      列出url不为空的数据
        QueryWrapper<DataShareinf> dataShareinfQueryWrapper=new QueryWrapper<DataShareinf>().isNotNull("url");
        List<DataShareinf> dataShareinfs=dataShareinfMapper.selectList(dataShareinfQueryWrapper);
//        String load="/apps/school/hsd/mPXisoMBaBYypYOAySiIs5NM3/20230206mPXisoMBaBYypYOAySiIs5NM3.rar".replace("/","\\");
//        File file=new File(load);
//        System.out.println(file.length());
//        System.out.println(file.getPath());

//        判断当前时间与表里的更新时间的时间差，如果大于7，删除文件
        for (DataShareinf dataShareinf:dataShareinfs) {
            String  apiKey=dataShareinf.getUrl().replace("/","\\");
            Date applyDate =dataShareinf.getUpdatedAt();
            Instant instant=applyDate.toInstant();
            ZoneId zoneId=ZoneId.systemDefault();
            LocalDate applyLocalDate=instant.atZone(zoneId).toLocalDate();
            if (ChronoUnit.DAYS.between(date,applyLocalDate)>7){
                File file=new File(apiKey);
                if (file.exists()) {
                    System.out.println(file.delete());
//                    删除父目录
                    File file1=new File(file.getParent());
                    System.out.println(file1.delete());
                }

            }
        }
    }
}
