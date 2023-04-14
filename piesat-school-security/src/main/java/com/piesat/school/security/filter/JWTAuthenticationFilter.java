package com.piesat.school.security.filter;

import com.alibaba.fastjson.JSON;
import com.piesat.school.security.JWT.TokenUtils;
import com.piesat.school.security.JsonResult;
import com.piesat.school.security.ResultCode;
import com.piesat.school.security.handler.CustomSessionInformationExpiredStrategy;
import com.piesat.school.user.iservice.IRUserService;
import com.piesat.school.user.vto.UserVTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Resource
    private IRUserService irUserService;


    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, SpringSessionBackedSessionRegistry springSessionBackedSessionRegistry) {
        this.authenticationManager = authenticationManager;
        ConcurrentSessionControlAuthenticationStrategy strategy=new ConcurrentSessionControlAuthenticationStrategy( springSessionBackedSessionRegistry);
        strategy.setMaximumSessions(1);
        this.setSessionAuthenticationStrategy(strategy);

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
        HttpSession session=request.getSession();
        System.out.println(session.getAttribute(loginUser.getEmail()));

//        UserVTO userVTO=irUserService.findUserByPhoneOrEmail(loginUser.getEmail());
//        if (!userVTO.getDeleteYou().equals(0)){
//            return null;
//        }
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
        response.setContentType("application/json;charset=UTF-8");
        JsonResult<String> jsonResult = new JsonResult<>();
        if (failed instanceof AccountExpiredException) {
            //账号过期
            jsonResult.setcode(ResultCode.USER_ACCOUNT_EXPIRED.getCode());
            jsonResult.setmessage(ResultCode.USER_ACCOUNT_EXPIRED.getMessage());
            response.getWriter().write(JSON.toJSONString(jsonResult));
        } else if (failed instanceof BadCredentialsException) {
            //密码错误
            jsonResult.setcode(ResultCode.USER_CREDENTIALS_ERROR.getCode());
            jsonResult.setmessage(ResultCode.USER_CREDENTIALS_ERROR.getMessage());
            response.getWriter().write(JSON.toJSONString(jsonResult));
        } else if (failed instanceof CredentialsExpiredException) {
            //密码过期
            jsonResult.setcode(ResultCode.USER_CREDENTIALS_EXPIRED.getCode());
            jsonResult.setmessage(ResultCode.USER_CREDENTIALS_EXPIRED.getMessage());
            response.getWriter().write(JSON.toJSONString(jsonResult));
        } else if (failed instanceof DisabledException) {
            //账号不可用
            jsonResult.setcode(ResultCode.USER_ACCOUNT_DISABLE.getCode());
            jsonResult.setmessage(ResultCode.USER_ACCOUNT_DISABLE.getMessage());
            response.getWriter().write(JSON.toJSONString(jsonResult));
        } else if (failed instanceof LockedException) {
            //账号锁定
            jsonResult.setcode(ResultCode.USER_ACCOUNT_LOCKED.getCode());
            jsonResult.setmessage(ResultCode.USER_ACCOUNT_LOCKED.getMessage());
            response.getWriter().write(JSON.toJSONString(jsonResult));
        } else if (failed instanceof InternalAuthenticationServiceException) {
            //用户不存在
            jsonResult.setcode(ResultCode.USER_ACCOUNT_NOT_EXIST.getCode());
            jsonResult.setmessage(ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
            response.getWriter().write(JSON.toJSONString(jsonResult));
        }else{
            //其他错误
            jsonResult.setcode(ResultCode.COMMON_FAIL.getCode());
            jsonResult.setmessage(ResultCode.COMMON_FAIL.getMessage());
            response.getWriter().write(JSON.toJSONString(jsonResult));
        }
    }

}
