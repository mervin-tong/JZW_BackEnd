package com.piesat.school.security.handler;

import com.alibaba.fastjson.JSON;
import com.piesat.school.security.JsonResult;
import com.piesat.school.security.ResultCode;
import com.piesat.school.security.ResultTool;
import com.smartwork.api.Result;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: suweipeng
 * @Description: 登录失败处理逻辑
 */
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {

    public Result<Boolean> onAuthenticationFailure(AuthenticationException e){
        if (e instanceof AccountExpiredException) {
            //账号过期
            return Result.ofFail(String.valueOf(ResultCode.USER_ACCOUNT_EXPIRED.getCode())
                    ,ResultCode.USER_ACCOUNT_EXPIRED.getMessage());
        } else if (e instanceof BadCredentialsException) {
            //密码错误
            return Result.ofFail(String.valueOf(ResultCode.USER_CREDENTIALS_ERROR.getCode())
                    ,ResultCode.USER_CREDENTIALS_ERROR.getMessage());
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
            return Result.ofFail(String.valueOf(ResultCode.USER_CREDENTIALS_EXPIRED.getCode())
                    ,ResultCode.USER_CREDENTIALS_EXPIRED.getMessage());
        } else if (e instanceof DisabledException) {
            //账号不可用
            return Result.ofFail(String.valueOf(ResultCode.USER_ACCOUNT_DISABLE.getCode())
                    ,ResultCode.USER_ACCOUNT_DISABLE.getMessage());
        } else if (e instanceof LockedException) {
            //账号锁定
            return Result.ofFail(String.valueOf(ResultCode.USER_ACCOUNT_LOCKED.getCode())
                    ,ResultCode.USER_ACCOUNT_LOCKED.getMessage());
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
            return Result.ofFail(String.valueOf(ResultCode.USER_ACCOUNT_NOT_EXIST.getCode())
                    ,ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
        }else{
            //其他错误
            return Result.ofFail(String.valueOf(ResultCode.COMMON_FAIL.getCode())
                    ,ResultCode.COMMON_FAIL.getMessage());
        }
    }


    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

    }
}
