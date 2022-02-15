package com.piesat.school.user.iservice;


import com.piesat.school.user.vto.PermissionVTO;
import com.piesat.school.user.vto.UserVTO;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 苏炜鹏
 * @since 2022-02-11
 */
public interface IRUserService {
    UserVTO findUserByPhone(String phone);

    List<PermissionVTO> getPermissionByUrl(String path);

}
