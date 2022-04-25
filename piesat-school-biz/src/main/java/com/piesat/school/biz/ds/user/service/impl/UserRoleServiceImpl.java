package com.piesat.school.biz.ds.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.biz.ds.user.entity.UserRole;
import com.piesat.school.biz.ds.user.mapper.UserRoleMapper;
import com.piesat.school.biz.ds.user.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author suweipeng
 * @data 2022/2/20 17:08
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;
    @Override
    public void addUserRole(UserRole userRole) {
        this.save(userRole);

    }
}
