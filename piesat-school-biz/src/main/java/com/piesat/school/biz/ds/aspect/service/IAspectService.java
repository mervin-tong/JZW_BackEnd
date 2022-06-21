package com.piesat.school.biz.ds.aspect.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.biz.ds.aspect.entity.AspectEntity;

import java.util.Date;

public interface IAspectService extends IService<AspectEntity> {

    Boolean addRecord(String methodName, StringBuilder params, String username, Date operationTime);
}
