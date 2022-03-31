package com.piesat.school.biz.ds.user.bulider;

import com.piesat.school.biz.ds.user.entity.User;
import com.piesat.school.user.param.UserParamData;
import com.piesat.school.user.vto.UserListVTO;
import com.piesat.school.user.vto.UserVTO;
import org.springframework.beans.BeanUtils;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * @author suweipeng
 * @data 2022/1/17 14:20
 */
public class UserBuilder {
    public static UserVTO toUserVTO(User user){
        UserVTO userVTO = new UserVTO();
        //ofNullable(T value)不会throw Exception，ofNullable(T value)直接返回一个EMPTY对象。
        //如果user不为空把user复制给userVTO
        Optional.ofNullable(user).ifPresent(
                _user-> {
                    BeanUtils.copyProperties(_user,userVTO);
                });

        return userVTO;
    }
    public static User toUser(UserParamData userParamData){
        User user = new User();
        Optional.ofNullable(userParamData).ifPresent(_userParamData->{
            BeanUtils.copyProperties(_userParamData,user);
        });
        return user;
    }

    public static UserListVTO toUserListVTO(User user){
        UserListVTO userListVTO = new UserListVTO();
        //ofNullable(T value)不会throw Exception，ofNullable(T value)直接返回一个EMPTY对象。
        //如果user不为空把user复制给userVTO
        Optional.ofNullable(user).ifPresent(
                _user-> {
                    BeanUtils.copyProperties(_user,userListVTO);
                });

        return userListVTO;
    }
}
