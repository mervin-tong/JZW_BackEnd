package com.piesat.school.security.filter;

import com.alibaba.fastjson.JSON;
import com.piesat.school.security.JWT.TokenUtils;
import com.piesat.school.security.JsonResult;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {


    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader("token");
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(TokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        String URI=request.getRequestURI();
        List<String> requireRole= Stream.of
                ("/piesat-school/app/dataInf/mergeFirstClass",
                "/piesat-school/app/dataInf/mergeSecClass",
                "/piesat-school/app/dataInf/deleteDataClassification",
                "/piesat-school/app/dataInf/saveDataClassification",
                "/piesat-school/app/dataInf/deleteGenerationMode",
                "/piesat-school/app/dataInf/mergeGenerationMode",
                "/piesat-school/app/orderfrom/assign",
                "/piesat-school/app/orderfrom/release",
                "/piesat-school/app/order/orderfromDelete",
                "/piesat-school/app/order/checkInOrOut",
                "/piesat-school/app/topic/del",
                "/piesat-school/app/topic/saveOrUpdate",
                "/piesat-school/app/uploadPermissions/setApprover",
                "/piesat-school/app/uploadPermissions/cleanApprover",
                "/piesat-school/app/uploadPermissions/closeUpPermissions",
                "/piesat-school/app/information/del",
                "/piesat-school/app/uploadPermissions/detail",
                "/piesat-school/app/uploadPermissions/checkPermissions").collect(Collectors.toList());
        if(requireRole.contains(URI)) {
            String token = tokenHeader.replace(TokenUtils.TOKEN_PREFIX, "");
            if(TokenUtils.isExpiration(token)) {
                String username = TokenUtils.getUsername(token);
                String role = TokenUtils.getUserRole(token);
                if (role.equals("ROLE_ADMIN") || role.equals("ROLE_EGCADMIN")) {
                    if (username != null) {
                        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(new SimpleGrantedAuthority(role))));
                    }
                }else {
                    response.setContentType("application/json;charset=UTF-8");
                    JsonResult<String> jsonResult = new JsonResult<>();
                    jsonResult.setcode(601);
                    jsonResult.setmessage("用户无权限");
                    response.getWriter().write(JSON.toJSONString(jsonResult));
                    return;
                }
            }else {
                response.setContentType("application/json;charset=UTF-8");
                JsonResult<String> jsonResult = new JsonResult<>();
                jsonResult.setcode(600);
                jsonResult.setmessage("token过期");
                response.getWriter().write(JSON.toJSONString(jsonResult));
                return;
            }
        }
        super.doFilterInternal(request, response, chain);
    }

    // 这里从token中获取用户信息并新建一个token 就是上面说的设置认证信息
//    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader, HttpServletResponse response) throws RuntimeException {
//        String token = tokenHeader.replace(TokenUtils.TOKEN_PREFIX, "");
//        // 检测token是否过期 如果过期会自动抛出错误
//        if(TokenUtils.isExpiration(token)) {
//            String username = TokenUtils.getUsername(token);
//            String role = TokenUtils.getUserRole(token);
//            if (role.equals("ROLE_ADMIN") || role.equals("ROLE_EGCADMIN")) {
//                if (username != null) {
//                    return new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(new SimpleGrantedAuthority(role))
//                    );
//                }
//            }
//            return null;
//        }
//        return null;
//    }
}
