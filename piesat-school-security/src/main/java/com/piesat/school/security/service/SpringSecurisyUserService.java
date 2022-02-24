package com.piesat.school.security.service;

import com.piesat.school.user.iservice.IRUserService;
import com.piesat.school.user.vto.RoleVTO;
import com.piesat.school.user.vto.UserVTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author suweipeng
 * @data 2022/2/11 10:26
 */
@Component
public class SpringSecurisyUserService implements UserDetailsService {
    //使用Dubbo通过网络远程调用服务提供方获取数据库中的用户信息
    @DubboReference
    private IRUserService irUserService;

    //根据用户名查询数据库获取用户信息
    @Override
    public UserDetails loadUserByUsername(String phoneOrEmail) throws UsernameNotFoundException {
        UserVTO userVTO = irUserService.findUserByphoneOrEmail(phoneOrEmail);
        if (userVTO == null){
            //用户名不存在
            return null;
        }
        //为当前用户授权
        Set<RoleVTO> roles = userVTO.getRoles();
        List<GrantedAuthority> list = new ArrayList<>();
        for (RoleVTO role : roles) {
            //遍历角色集合，为用户授权
            list.add(new SimpleGrantedAuthority(role.getKeyword()));
        }

        User securityUser = new User(phoneOrEmail,userVTO.getPassword(),list);
        return securityUser;
    }
}
