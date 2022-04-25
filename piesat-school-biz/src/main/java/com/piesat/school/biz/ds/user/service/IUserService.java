package com.piesat.school.biz.ds.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.biz.ds.user.entity.User;
import com.piesat.school.user.param.UpdatePasswordParamData;
import com.piesat.school.user.param.UserParamData;
import com.piesat.school.user.param.UserQueryParamData;
import com.piesat.school.user.vto.UserVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

/**
 * @author suweipeng
 * @data 2022/2/11 16:03
 */
public interface IUserService extends IService<User> {
     //通过email查找用户
     UserVTO findUserByPhoneOrEmail(String phoneOrEmail);
     //注册普通用户
     UserVTO addUser(UserParamData userParamData) ;
     //根据邮箱修改密码
     Boolean alterPassword(String email,String password);
     //获取用户列表
     TailPage<UserVTO> getUserList(UserQueryParamData paramData);
     //根据id修改密码
     Result<Boolean> updatePassword(UpdatePasswordParamData paramData);
     //添加管理员
     Result<Boolean> addAdministrator(UserParamData userParamData);
}
