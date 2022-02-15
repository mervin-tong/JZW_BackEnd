package com.piesat.school.biz.ds.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.piesat.school.biz.ds.user.bulider.UserBulider;
import com.piesat.school.biz.ds.user.entity.User;
import com.piesat.school.biz.ds.user.mapper.UserMapper;
import com.piesat.school.biz.ds.user.service.IUserService;
import com.piesat.school.user.iservice.IRUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.user.vto.UserVTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 唐子超
 * @since 2022-01-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserVTO findUserByPhone(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        User user = userMapper.selectList(queryWrapper).get(0);

        return UserBulider.toUserVTO(user);
    }
}
