package com.piesat.school.biz.ds.user.facade;

import com.piesat.school.biz.ds.user.entity.Permission;
import com.piesat.school.biz.ds.user.entity.User;
import com.piesat.school.biz.ds.user.service.IPermissionService;
import com.piesat.school.biz.ds.user.service.IRoleService;
import com.piesat.school.biz.ds.user.service.IUserService;
import com.piesat.school.user.vto.PermissionVTO;
import com.piesat.school.user.vto.RoleVTO;
import com.piesat.school.user.vto.UserVTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author suweipeng
 * @data 2022/2/11 17:41
 */
@Service
public class UserFacadeService {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IPermissionService iPermissionService;
    public UserVTO findUserByPhone(String phone){

        UserVTO userVTO = iUserService.findUserByPhone(phone);
        Set<RoleVTO> roleVTOs = iRoleService.getRolesByUserId(userVTO.getId());
        for (RoleVTO roleVTO : roleVTOs) {
            Set<PermissionVTO> permissionVTOs = iPermissionService.getPermissionsByRoleId(roleVTO.getId());
            roleVTO.setPermissions(permissionVTOs);
        }
        userVTO.setRoles(roleVTOs);
        return userVTO;
    }
    public List<PermissionVTO> getPermissionVTOsByUrl(String path){
        List<PermissionVTO> permissionVTOS = iPermissionService.getPermissionByUrl(path);
        return permissionVTOS;
    }


}
