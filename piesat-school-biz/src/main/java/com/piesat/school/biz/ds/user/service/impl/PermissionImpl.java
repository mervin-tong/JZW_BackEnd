package com.piesat.school.biz.ds.user.service.impl;

import com.piesat.school.biz.ds.user.bulider.PermissionBulider;
import com.piesat.school.biz.ds.user.entity.Permission;
import com.piesat.school.biz.ds.user.mapper.PermissionMapper;
import com.piesat.school.biz.ds.user.service.IPermissionService;
import com.piesat.school.user.vto.PermissionVTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author suweipeng
 * @data 2022/2/13 23:42
 */
@Service
public class PermissionImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public Set<PermissionVTO> getPermissionsByRoleId(Integer id) {
        Set<Permission> permissions = permissionMapper.getPermissionByRoleId(id);
        HashSet<PermissionVTO> permissionVTOs = new HashSet<>();
        //变成PermissionVTO
        for (Permission permission : permissions) {
            permissionVTOs.add(PermissionBulider.toPermissionVTO(permission));

        }
        return permissionVTOs;
    }

    @Override
    public List<PermissionVTO> getPermissionByUrl(String path) {
        Set<Permission> permissions = permissionMapper.getPermissionByUrl(path);
        List<PermissionVTO> permissionVTOs = new LinkedList<>();
        //变成PermissionVTO
        for (Permission permission : permissions) {
            PermissionVTO permissionVTO = PermissionBulider.toPermissionVTO(permission);
            permissionVTOs.add(permissionVTO);

        }
        return permissionVTOs;
    }
}
