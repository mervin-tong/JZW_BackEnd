package com.piesat.school.biz.ds.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.biz.ds.user.entity.User;
import com.piesat.school.biz.ds.user.mapper.UserMapper;
import com.piesat.school.biz.ds.user.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lawliet
 * @since 2021-06-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public Map<String, User> usersMap(List<String> userIds){
        List<User> users = this.listByIds(userIds);
        return users.stream().collect(toMap(User::getId, Function.identity(), (v1, v2)->v2));
    }
}
