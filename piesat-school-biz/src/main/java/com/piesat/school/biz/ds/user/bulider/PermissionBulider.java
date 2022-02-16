package com.piesat.school.biz.ds.user.bulider;

import com.piesat.school.biz.ds.user.entity.Permission;
import com.piesat.school.biz.ds.user.entity.Role;
import com.piesat.school.user.vto.PermissionVTO;
import com.piesat.school.user.vto.RoleVTO;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

/**
 * @author suweipeng
 * @data 2022/2/14 0:04
 */
public class PermissionBulider {
    public static PermissionVTO toPermissionVTO(Permission permission){
        PermissionVTO permissionVTO = new PermissionVTO();
        Optional.ofNullable(permission).ifPresent(
                _permission-> {
                    BeanUtils.copyProperties(_permission,permissionVTO);
                });

        return permissionVTO;
    }
}
