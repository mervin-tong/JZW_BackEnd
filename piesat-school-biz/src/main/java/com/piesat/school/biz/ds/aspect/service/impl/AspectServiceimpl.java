package com.piesat.school.biz.ds.aspect.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.biz.ds.aspect.entity.AspectEntity;
import com.piesat.school.biz.ds.aspect.mapper.AspectMapper;
import com.piesat.school.biz.ds.aspect.service.IAspectService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AspectServiceimpl extends ServiceImpl<AspectMapper, AspectEntity> implements IAspectService {

    @Override
    public Boolean addRecord(String methodName, StringBuilder params, String username, Date operationTime) {
        AspectEntity aspectEntity=new AspectEntity();
        aspectEntity.setParam(params.toString());
        aspectEntity.setTime(operationTime);
        aspectEntity.setUrl(methodName);
        aspectEntity.setUserId(username);
        return this.save(aspectEntity);
    }
}
