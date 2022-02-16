package com.piesat.school.biz.ds.user.service.impl;

import com.piesat.school.biz.ds.user.bulider.RoleBulider;
import com.piesat.school.biz.ds.user.entity.Role;
import com.piesat.school.biz.ds.user.mapper.RoleMapper;
import com.piesat.school.biz.ds.user.service.IRoleService;
import com.piesat.school.user.vto.RoleVTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author suweipeng
 * @data 2022/2/12 11:46
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Set<RoleVTO> getRolesByUserId(Integer id) {
        Set<Role> roles = roleMapper.getRolesByUserId(id);
        HashSet<RoleVTO> roleVTOS = new HashSet<>();
        for (Role role : roles) {
            roleVTOS.add(RoleBulider.toRoleVTO(role));
        }
        return roleVTOS;
    }
}
