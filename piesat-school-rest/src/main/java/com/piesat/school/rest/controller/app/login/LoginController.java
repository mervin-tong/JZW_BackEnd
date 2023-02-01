package com.piesat.school.rest.controller.app.login;

import com.piesat.school.security.ResultCode;
import com.piesat.school.user.iservice.IRUserService;
import com.piesat.school.user.vto.UserVTO;
import com.smartwork.api.Result;
import com.smartwork.web.controller.BaseController;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.rmi.AlreadyBoundException;

@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {
    @DubboReference
    private IRUserService irUserService;

    /**
     * 登录失败返回 401 以及提示信息.
     *
     * @return the rest
     */
    @PostMapping("/failure")
    public Result<Object> loginFailure(HttpServletRequest request) {
        AuthenticationException e = (AuthenticationException) request.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
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

    /**
     * 登录成功后拿到个人信息.
     *
     * @return the rest
     */
    @PostMapping("/success")
    public Result loginSuccess() {
        // 登录成功后用户的认证信息 UserDetails会存在 安全上下文寄存器 SecurityContextHolder 中
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.equals("anonymousUser")){
            return Result.ofFail("4401","当前没有用户登录");
        }
        UserVTO userVTO = irUserService.findUserByPhoneOrEmail(principal.getUsername());
        if (userVTO.getDeleteYou().equals(1)){
            return Result.ofFail("2010", "账号已被删除");
        }
//        userVTO.setToken(TokenUtils.token(userVTO.getPhone(), userVTO.getPassword()));
        return  Result.ofSuccess(userVTO);
    }
}


