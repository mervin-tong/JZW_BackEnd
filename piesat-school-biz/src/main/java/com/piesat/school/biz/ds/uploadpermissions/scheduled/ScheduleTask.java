package com.piesat.school.biz.ds.uploadpermissions.scheduled;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.piesat.school.biz.ds.uploadpermissions.entity.UploadPermisssions;
import com.piesat.school.biz.ds.uploadpermissions.mapper.UploadPermisssionsMapper;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RSet;
import org.redisson.api.RSetMultimap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author suweipeng
 * @data 2021/12/17 16:42
 */
@Slf4j
@Component
public class ScheduleTask {

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private UploadPermisssionsMapper uploadPermisssionsMapper;

    @Scheduled(cron = "0/1 * * * * ?")
    public void test(){
        //从redis获取map
        RSetMultimap<String, String> map = redissonClient.getSetMultimap("timestampUploadId");
        //当前时间戳精确到秒
        long timestamp = System.currentTimeMillis()/1000;
        String ts = String.valueOf(timestamp);
        ArrayList list = new ArrayList();
//        log.info(ts);
        //从map获取到期的申请id
        RSet<String> timestampUploadId = map.get(ts);
        for (String s : timestampUploadId) {
                list.add(Long.valueOf(s));
        }
        //获取到期还没处理的申请id
        if (!list.isEmpty()){
            QueryWrapper<UploadPermisssions> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("id");
            queryWrapper.eq("status",0);
            queryWrapper.in("id",list);
            List<UploadPermisssions> uploadPermisssions = uploadPermisssionsMapper.selectList(queryWrapper);
            List<Long> idList = uploadPermisssions.stream().map(UploadPermisssions::getId).collect(Collectors.toList());
            //把申请变为未锁定
            if (!idList.isEmpty()){
                UpdateWrapper<UploadPermisssions> updateWrapper = new UpdateWrapper();
                updateWrapper.set("approver",0L);
                updateWrapper.in("id",idList);
                uploadPermisssionsMapper.update(null,updateWrapper);
            }
        }
    }

}
