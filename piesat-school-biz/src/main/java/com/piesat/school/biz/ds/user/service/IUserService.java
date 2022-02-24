package com.piesat.school.biz.ds.user.service;

import com.piesat.school.user.param.UserParamData;
import com.piesat.school.user.vto.UserVTO;

/**
 * @author suweipeng
 * @data 2022/2/11 16:03
 */
public interface IUserService {
     //通过email查找用户
     UserVTO findUserByphoneOrEmail(String phoneOrEmail);
     //注册普通用户
     UserVTO addUser(UserParamData userParamData) ;
     //修改密码
     Boolean alterPassword(String email,String password);


}
