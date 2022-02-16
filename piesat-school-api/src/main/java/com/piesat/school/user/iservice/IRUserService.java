package com.piesat.school.user.iservice;


import com.piesat.school.user.vto.UserVTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 苏炜鹏
 * @since 2022-02-11
 */
public interface IRUserService {
    //通过邮箱查询用户信息
    UserVTO findUserByPhone(String email);

}
