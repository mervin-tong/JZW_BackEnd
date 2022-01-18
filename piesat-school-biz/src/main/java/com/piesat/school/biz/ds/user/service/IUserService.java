package com.piesat.school.biz.ds.user.service;

import com.piesat.school.biz.ds.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.user.vto.UserVTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 唐子超
 * @since 2022-01-16
 */
public interface IUserService extends IService<User> {
    UserVTO getUser();

}
