package com.piesat.school.biz.ds.user.service;

import com.piesat.school.user.vto.PermissionVTO;

import java.util.List;
import java.util.Set;

public interface IPermissionService {
    //通过角色id获取权限集合
    Set<PermissionVTO> getPermissionsByRoleId(Integer id);

    //查询可以访问这个url的权限
    List<PermissionVTO> getPermissionByUrl(String path);


}
