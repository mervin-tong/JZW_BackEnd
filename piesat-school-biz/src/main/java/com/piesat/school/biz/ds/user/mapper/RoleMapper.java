package com.piesat.school.biz.ds.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piesat.school.biz.ds.user.entity.Role;
import com.piesat.school.user.vto.RoleVTO;

import java.util.Set;

/**
 * @author suweipeng
 * @data 2022/2/12 17:33
 */

public interface RoleMapper extends BaseMapper<Role> {
    Set<Role> getRolesByUserId(Long id);
}
