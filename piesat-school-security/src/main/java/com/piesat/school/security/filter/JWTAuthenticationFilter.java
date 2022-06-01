package com.piesat.school.security.filter;

import com.piesat.school.security.JWT.TokenUtils;
import com.piesat.school.user.iservice.IRUserService;
import com.piesat.school.user.vto.UserVTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Resource
    private IRUserService irUserService;

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/login");
    }


    /**
     * 接收并解析用户凭证
     * @param request
     * @param response
     * @return
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        // 从输入流中获取到登录的信息
//        Enumeration<String> a=request.getParameterNames();
        Map<String,String[]> b= request.getParameterMap();
    try{
        InputStream inputStream=request.getInputStream();
//        UserVTO loginUser =new ObjectMapper().readValue(inputStream, UserVTO.class);
        UserVTO loginUser=new UserVTO();
        loginUser.setEmail(b.get("username")[0]);
        loginUser.setPassword(b.get("password")[0]);
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));
    }catch (IOException e){
        e.printStackTrace();
        return null;
    }
    }
    // 成功验证后调用的方法
    // 如果验证成功，就生成token并返回
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Object o=authResult.getPrincipal();
        User jwtUser = (User) authResult.getPrincipal();
//        System.out.println("jwtUser:" + jwtUser.toString());
        String role = "";
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            role = authority.getAuthority();
        }

        String token = TokenUtils.createToken(jwtUser.getUsername(), role);
        // 返回创建成功的token  但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的时候应该是 `Bearer token`
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String tokenStr = TokenUtils.TOKEN_PREFIX + token;
        response.setHeader("token", tokenStr);

        chain.doFilter(request, response);
    }

    // 失败 返回错误就行
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }

}
