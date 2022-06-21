package com.piesat.school.provider.serv.aspectj;

import com.piesat.school.aspectj.iservice.IRAspectService;
import com.piesat.school.biz.ds.aspect.service.IAspectService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.Date;

@DubboService(interfaceClass = IRAspectService.class)
public class RAspectService implements IRAspectService {
    @Resource
    private IAspectService aspectService;

    @Override
    public Boolean addRecord(String methodName, StringBuilder params, String username, Date operationTime) {
        return aspectService.addRecord(methodName,params,username,operationTime);
    }
}
