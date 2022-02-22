package com.piesat.school.provider.serv.user;

import com.piesat.school.biz.ds.user.facade.UserFacadeService;
import com.piesat.school.biz.ds.user.service.IUserService;
import com.piesat.school.user.iservice.IRUserService;
import com.piesat.school.user.param.UserParamData;
import com.piesat.school.user.vto.UserVTO;
import com.smartwork.api.Result;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author suweipeng
 * @data 2022/1/17 9:18
 * 用户服务
 */
@DubboService(interfaceClass = IRUserService.class)
public class RUserService implements IRUserService {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private UserFacadeService userFacadeService;

    @Override
    public UserVTO findUserByPhone(String email) {

        return userFacadeService.findUserByPhone(email);
    }

    @Override
    public Result<UserVTO> addUser(UserParamData userParamData) {
        return Result.ofSuccess(userFacadeService.addUser(userParamData));
    }
}
