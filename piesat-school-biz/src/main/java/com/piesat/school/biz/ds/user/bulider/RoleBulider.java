package com.piesat.school.biz.ds.user.bulider;

import com.piesat.school.biz.ds.user.entity.Role;
import com.piesat.school.biz.ds.user.entity.User;
import com.piesat.school.user.vto.RoleVTO;
import com.piesat.school.user.vto.UserVTO;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

/**
 * @author suweipeng
 * @data 2022/2/13 23:05
 */
public class RoleBulider {
    public static RoleVTO toRoleVTO(Role role){
        RoleVTO roleVTO = new RoleVTO();
        Optional.ofNullable(role).ifPresent(
                _role-> {
                    BeanUtils.copyProperties(_role,roleVTO);
                });

        return roleVTO;
    }
}
