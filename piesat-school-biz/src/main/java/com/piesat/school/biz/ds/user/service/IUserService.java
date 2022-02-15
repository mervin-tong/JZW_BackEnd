package com.piesat.school.biz.ds.user.service;

import com.piesat.school.biz.ds.user.entity.User;
import com.piesat.school.user.vto.UserVTO;

/**
 * @author suweipeng
 * @data 2022/2/11 16:03
 */
public interface IUserService {
     UserVTO findUserByPhone(String phone);
}
