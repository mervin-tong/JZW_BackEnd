package com.piesat.school.biz.ds.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.biz.ds.user.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lawliet
 * @since 2021-06-24
 */
public interface IUserService extends IService<User> {
    Map<String, User> usersMap(List<String> userIds);
}
