package com.piesat.school.provider.serv.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.piesat.school.biz.ds.user.entity.Email;
import com.piesat.school.biz.ds.user.facade.UserFacadeService;
import com.piesat.school.biz.ds.user.mapper.EmailMapper;
import com.piesat.school.biz.ds.user.service.IUserService;
import com.piesat.school.user.iservice.IRUserService;
import com.piesat.school.user.param.ForgetPasswordParamData;
import com.piesat.school.user.param.UpdateUserParamData;
import com.piesat.school.user.param.UserParamData;
import com.piesat.school.user.vto.UserListVTO;
import com.piesat.school.user.vto.UserVTO;
import com.smartwork.api.Result;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
    public Result<List<UserListVTO>> getUserList() {
        return Result.ofSuccess(iUserService.getUserList());
    }

    @Override
    public Result<UserVTO> updateUser(UpdateUserParamData paramData) {
        return userFacadeService.updateUser(paramData);
    }
}
