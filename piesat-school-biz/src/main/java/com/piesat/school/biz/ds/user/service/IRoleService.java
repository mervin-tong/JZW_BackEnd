package com.piesat.school.biz.ds.user.service;

import com.piesat.school.biz.ds.user.entity.Role;
import com.piesat.school.user.vto.RoleVTO;

import java.util.Set;

public interface IRoleService {
    //通过id获取角色集合
    Set<RoleVTO>  getRolesByUserId(Long id);


}
