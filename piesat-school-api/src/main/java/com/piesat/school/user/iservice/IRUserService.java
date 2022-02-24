package com.piesat.school.user.iservice;


import com.piesat.school.user.param.ForgetPasswordParamData;
import com.piesat.school.user.param.UserParamData;
import com.piesat.school.user.vto.UserVTO;
import com.smartwork.api.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 苏炜鹏
 * @since 2022-02-11
 */
public interface IRUserService {
    //通过邮箱或手机号查询用户信息
    UserVTO findUserByphoneOrEmail(String phoneOrEmail);

    Result<UserVTO> addUser(UserParamData userParamData);
    //发送邮箱验证码
    Result<Boolean> sendEmail(String email);
    //忘记密码
    Result<Boolean> forgetPassword(ForgetPasswordParamData forgetPasswordParamData);

}
