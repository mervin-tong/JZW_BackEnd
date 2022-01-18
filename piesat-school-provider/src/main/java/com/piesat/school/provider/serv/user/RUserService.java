package com.piesat.school.provider.serv.user;

import com.piesat.school.biz.ds.user.service.IUserService;
import com.piesat.school.user.iservice.IRUserService;
import com.piesat.school.user.vto.UserVTO;
import com.smartwork.api.Result;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author suweipeng
 * @data 2022/1/17 9:18
 */
@DubboService(version = "1.0.0")
public class RUserService implements IRUserService {
    @Autowired
    private IUserService iUserService;
    @Override
    public Result<UserVTO> getUser() {
        return Result.ofSuccess(iUserService.getUser());
    }
}
