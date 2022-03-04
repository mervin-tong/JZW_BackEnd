package com.piesat.school.biz.ds.uploadpermissions.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.uploadpermissions.entity.UploadPermisssions;
import com.piesat.school.biz.ds.uploadpermissions.mapper.UploadPermisssionsMapper;
import com.piesat.school.biz.ds.uploadpermissions.scheduled.ScheduleTask;
import com.piesat.school.biz.ds.uploadpermissions.service.IUploadPermisssionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.emuerlation.BizEnumType;
import com.piesat.school.uploadpermissions.param.UploadPermissionsParamData;
import com.piesat.school.uploadpermissions.vto.UploadPermissionsVTO;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  上传权限类
 * </p>
 *
 * @author suweipeng
 * @since 2022-02-28
 */
@Slf4j
@Service
public class UploadPermisssionsServiceImpl extends ServiceImpl<UploadPermisssionsMapper, UploadPermisssions> implements IUploadPermisssionsService {

    @Autowired
    private UploadPermisssionsMapper uploadPermisssionsMapper;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    private ScheduleTask scheduleTask;
    /**
     * 获取申请上传权限列表
     * @param uploadPermissionsParamData
     * @return
     */
    @Override
    public TailPage<UploadPermissionsVTO> uploadPermissionsList(UploadPermissionsParamData uploadPermissionsParamData) {
        Page<UploadPermissionsVTO> page = new Page<>(uploadPermissionsParamData.getPn(),uploadPermissionsParamData.getPs());
        List<UploadPermissionsVTO> list = uploadPermisssionsMapper.uploadPermissionsList(page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }

    /**
     * 申请上传权限
     * @param userId 申请用户id
     * @return true 申请成功
     */
    @Override
    public Boolean createPermissions(Long userId) {
        UploadPermisssions uploadPermisssions = new UploadPermisssions();
        uploadPermisssions.setStatus(BizEnumType.UploadPermissionsStatus.CreatePermissions.getKey());
        uploadPermisssions.setApplicatId(userId);
        uploadPermisssions.setCreatedAt(new Date());
        uploadPermisssions.setApprover(0L);

//        // 设置字符串
//        RBucket<String> keyObj = redissonClient.getBucket("k2");
//
//        RSetMultimap<String, String> map = redissonClient.getSetMultimap("myMultimap");
//        map.put("11","22");
//        map.put("11","33");
//        map.put("11","44");
//        RSet<String> strings = map.get("11");
//        scheduleTask.test();


//        RList<Object> list = redissonClient.getList("1646297657");
//        RKeys keys = redissonClient.getKeys();

        if (uploadPermisssionsMapper.insert(uploadPermisssions) >= 1){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 审批人锁定申请
     * @param approver 审批人
     * @param uploadId 上传权限id
     * @return true 申请成功
     */

    @Override
    public Boolean setApprover(Long approver, Long uploadId) {
        RSetMultimap<String, String> map = redissonClient.getSetMultimap("timestampUploadId");
        UpdateWrapper<UploadPermisssions> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("approver",approver);
        updateWrapper.eq("id",uploadId);
        updateWrapper.eq("approver",0L);
        long timestamp = System.currentTimeMillis()/1000;
        long limit = 20L;
        String limitTimestamp = String.valueOf(timestamp+limit);
        boolean isUpdate = this.update(updateWrapper);
        if (isUpdate){
            log.info("到期时间 "+limitTimestamp+" "+isUpdate);
            map.put(limitTimestamp,String.valueOf(uploadId));
        }
        return isUpdate;
    }

    /**
     * 审批人处理申请
     * @param uploadId 申请id
     * @param status 上传权限id
     * @param approver 审批人
     * @return true 申请成功
     */
    @Override
    public Boolean checkPermissions(Long uploadId,Integer status,Long approver) {
        if (approver == null || uploadId == null || status == null){
            return Boolean.FALSE;
        }
        //查询评审人是否锁定了该条申请
        QueryWrapper<UploadPermisssions> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",uploadId);
        UploadPermisssions isApprover = uploadPermisssionsMapper.selectList(queryWrapper).get(0);
        if(isApprover.getApprover() != approver){
            return Boolean.FALSE;
        }
        //评审人处理该申请
        UpdateWrapper<UploadPermisssions> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status",status);
        updateWrapper.eq("id",uploadId);
        boolean update = this.update(updateWrapper);
        isPermissionsLockDate(uploadId);
        return update;
    }
    public Boolean isPermissionsLockDate(Long uploadId) {
        QueryWrapper<UploadPermisssions> queryWrapper = new QueryWrapper<>();
        UploadPermisssions uploadPermisssions = uploadPermisssionsMapper.selectList(queryWrapper).get(0);
        if (uploadPermisssions.getStatus() == 1) {
            UpdateWrapper<UploadPermisssions> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("approver", 0);
            updateWrapper.eq("id", uploadId);
            return this.update(updateWrapper);
        }
        return Boolean.TRUE;
    }
}
