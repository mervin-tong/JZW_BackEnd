package com.piesat.school.provider.serv.user;

import com.piesat.school.biz.ds.user.facade.UserFacadeService;
import com.piesat.school.biz.ds.user.mapper.EmailMapper;
import com.piesat.school.biz.ds.user.service.IUserService;
import com.piesat.school.user.iservice.IRUserService;
import com.piesat.school.user.param.*;
import com.piesat.school.user.vto.UserVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    @Autowired
    private EmailMapper emailMapper;
    //通过邮箱或手机号查询用户信息
    @Override
    public UserVTO findUserByPhoneOrEmail(String phoneOrEmail) {
        return userFacadeService.findUserByPhoneOrEmail(phoneOrEmail);
    }
    //注册用户
    @Override
    public Result<UserVTO> addUser(UserParamData userParamData) {
            return userFacadeService.addUser(userParamData);
    }
    //发送验证码
    @Override
    public Result<Boolean> sendEmail(String email) {
         return userFacadeService.sendEmail(email);
    }
    //忘记密码
    @Override
    public Result<Boolean> forgetPassword(ForgetPasswordParamData forgetPasswordParamData) {
        return userFacadeService.forgetPassword(forgetPasswordParamData);
    }
    //忘记密码
    @Override
    public Result<TailPage<UserVTO>> getUserList(UserQueryParamData paramData) {
        return Result.ofSuccess(iUserService.getUserList(paramData));
    }

    @Override
    public Result<UserVTO> updateUser(UpdateUserParamData paramData) {
        return userFacadeService.updateUser(paramData);
    }

    //修改密码
    @Override
    public Result<Boolean> updatePassword(UpdatePasswordParamData paramData) {
        return iUserService.updatePassword(paramData);
    }

    @Override
    public Result<Boolean> limitUser(LimitUserParamData paramData) {
        return userFacadeService.limitUser(paramData);
    }

    @Override
    public Result<Boolean> feedback(FeedBackParamData paramData) {
        return userFacadeService.feedback(paramData);
    }
}
