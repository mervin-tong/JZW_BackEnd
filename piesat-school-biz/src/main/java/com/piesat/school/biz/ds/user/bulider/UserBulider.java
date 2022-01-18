package com.piesat.school.biz.ds.user.bulider;

import com.piesat.school.biz.ds.user.entity.User;
import com.piesat.school.user.vto.UserVTO;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

/**
 * @author suweipeng
 * @data 2022/1/17 14:20
 */
public class UserBulider {
    public static UserVTO toUserVTO(User user){
        UserVTO userVTO = new UserVTO();
        Optional.ofNullable(user).ifPresent(
                _user-> {
                    BeanUtils.copyProperties(_user,userVTO);
                });

        return userVTO;
    }
}
