package com.piesat.school.security.JWT;

import com.piesat.school.user.vto.UserVTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class JwtUser implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {
    }

    // 写一个能直接使用user创建jwtUser的构造器
    public JwtUser(UserVTO user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
//        for (RoleVTO roleVTO:user.getRoles()) {
//            authorities = Collections.singleton(new SimpleGrantedAuthority(roleVTO.getKeyword()));
//        }
        authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRoles().iterator().next().getKeyword()));
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
