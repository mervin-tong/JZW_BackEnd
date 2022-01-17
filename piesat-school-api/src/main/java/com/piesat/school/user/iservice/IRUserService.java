package com.piesat.school.user.iservice;


import com.piesat.school.user.vto.UserVTO;
import com.smartwork.api.Result;

/**
 * @author lawliet
 * @date 2021-06-07
 * <p>
 * Remote Service Interface
 */
public interface IRUserService {
    Result<UserVTO> getUser();
}
