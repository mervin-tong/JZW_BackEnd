package com.piesat.school.biz.ds.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.user.entity.Role;
import com.piesat.school.user.vto.RoleVTO;
import com.piesat.school.user.vto.UserVTO;

import java.util.List;
import java.util.Set;

public interface IRoleService {
    //通过id获取角色集合
    Set<RoleVTO> getRolesByUserId(Long id);

    Boolean isEGCAdmin(Long id);

}
