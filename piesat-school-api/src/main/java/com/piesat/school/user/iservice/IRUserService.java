package com.piesat.school.user.iservice;


import com.piesat.school.base.PageQueryParamData;
import com.piesat.school.user.param.*;
import com.piesat.school.user.vto.UserVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

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
    UserVTO findUserByPhoneOrEmail(String phoneOrEmail);
    //注册用户
    Result<UserVTO> addUser(UserParamData userParamData);
    //发送邮箱验证码
    Result<Boolean> sendEmail(String email);
    //忘记密码
    Result<Boolean> forgetPassword(ForgetPasswordParamData forgetPasswordParamData);

    Result<TailPage<UserVTO>> getUserList(UserQueryParamData paramData);

    Result<UserVTO> updateUser(UpdateUserParamData paramData);
    //修改密码
    Result<Boolean> updatePassword(UpdatePasswordParamData paramData);

    Result<Boolean> limitUser(LimitUserParamData paramData);

    Result<Boolean> feedback(FeedBackParamData paramData);

    Result<Boolean> addAdministrator(UserParamData userParamData);

    Result<TailPage<UserVTO>> getAdminList(PageQueryParamData paramData);

    Result<Boolean> deleteAdmin(Long id);
}
