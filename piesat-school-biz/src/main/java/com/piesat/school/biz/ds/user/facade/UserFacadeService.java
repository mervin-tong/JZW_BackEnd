package com.piesat.school.biz.ds.user.facade;

import com.piesat.school.biz.ds.user.entity.UserRole;
import com.piesat.school.biz.ds.user.service.IRoleService;
import com.piesat.school.biz.ds.user.service.IUserRoleService;
import com.piesat.school.biz.ds.user.service.IUserService;
import com.piesat.school.emuerlation.BizEnumType;
import com.piesat.school.user.param.UserParamData;
import com.piesat.school.user.vto.RoleVTO;
import com.piesat.school.user.vto.UserVTO;
import com.smartwork.api.Result;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author suweipeng
 * @data 2022/2/11 17:41
 */
@Slf4j
@Service
public class UserFacadeService {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IUserRoleService iUserRoleService;
    public UserVTO findUserByphoneOrEmail(String phoneOrEmail){

        UserVTO userVTO = iUserService.findUserByphoneOrEmail(phoneOrEmail);
        Set<RoleVTO> roleVTOs = iRoleService.getRolesByUserId(userVTO.getId());
        userVTO.setRoles(roleVTOs);
        return userVTO;
    }
    //普通用户注册
    public Result<UserVTO> addUser(UserParamData userParamData){
        UserVTO userVTO = new UserVTO();

        //如果注册的手机号或邮箱重复则捕获异常
        try {
            userVTO = iUserService.addUser(userParamData);

        }catch (DataAccessException e){
                return Result.ofFail("401","手机号/邮箱已存在");

        }
        UserRole userRole = new UserRole();
        userRole.setUserId(userVTO.getId());
        userRole.setRoleId(BizEnumType.RoleStatus.USER.getKey());
        log.info(userRole.toString());
        iUserRoleService.addUserRole(userRole);
        return Result.ofSuccess(userVTO);


    }
}
