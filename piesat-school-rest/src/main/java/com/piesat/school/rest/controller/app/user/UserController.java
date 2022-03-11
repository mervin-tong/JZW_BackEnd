package com.piesat.school.rest.controller.app.user;

import com.piesat.school.rest.constants.DubboConstant;
import com.piesat.school.security.JsonResult;
import com.piesat.school.security.ResultTool;
import com.piesat.school.security.handler.CustomizeAuthenticationFailureHandler;
import com.piesat.school.user.iservice.IRUserService;
import com.piesat.school.user.param.ForgetPasswordParamData;
import com.piesat.school.user.param.LoginRequest;
import com.piesat.school.user.param.UserParamData;
import com.piesat.school.user.vto.UserListVTO;
import com.piesat.school.user.vto.UserVTO;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.smartwork.api.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @author suweipeng
 * @data 2022/2/15 16:13
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/app/user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomizeAuthenticationFailureHandler authenticationFailureHandler;
    @DubboReference
    private IRUserService irUserService;
    @DubboReference(consumer = DubboConstant.CONSUMER_NAME)
    private IRUserService userService;
    @Secured({"ROLE_ADMIN","ROLE_EGCADMIN"})
    @ApiResponses({
            @ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
            @ApiResponse(code=403,message="没有权限")
    })
    @GetMapping("/getUserInfo")
    //获取用户
    public Result<UserVTO> getUser() {
        return Result.ofSuccess(userService.findUserByphoneOrEmail("zhangsan@163.com"));
    }


    //注册用户
    @ApiResponses({
            @ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
            @ApiResponse(code=403,message="没有权限"),
            @ApiResponse(code=4011,message="验证码错误"),
            @ApiResponse(code=4012,message="手机号/邮箱已存在"),
    })
    @PostMapping("/addUser")
    public Result<UserVTO> addUser(@RequestBody UserParamData userParamData){
        return userService.addUser(userParamData);
    }

    //发送邮箱验证码
    @ApiOperation(value = "发送邮箱验证码")
    @ApiResponses({
            @ApiResponse(code=0,message="操作成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
            @ApiResponse(code=4011,message="发送邮件失败，请核对邮箱账号"),
    })
    @GetMapping("/sendEmail")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "电子邮箱", dataType = "String" ),
    })
    public Result<Boolean> sendEmail(String email){
        return userService.sendEmail(email);
    }

    //忘记密码
    @ApiResponses({
            @ApiResponse(code=0,message="密码修改成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
            @ApiResponse(code=4403,message="密码修改失败"),
    })
    @PostMapping("/forgetPassword")
    public Result<Boolean> forgetPassword(@RequestBody ForgetPasswordParamData forgetPasswordParamData){
        return userService.forgetPassword(forgetPasswordParamData);
    }

    //登录
    @ApiOperation(value = "登录")
    @ApiResponses({
            @ApiResponse(code=0,message="密码修改成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
            @ApiResponse(code=2001,message="用户未登录"),
            @ApiResponse(code=2002,message="账号已过期"),
            @ApiResponse(code=2003,message="密码错误"),
            @ApiResponse(code=2004,message="密码过期"),
            @ApiResponse(code=2005,message="账号不可用"),
            @ApiResponse(code=2006,message="账号被锁定"),
            @ApiResponse(code=2007,message="账号不存在"),
    })
    @PostMapping("/login")
    public Result<Boolean> login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginRequest.getPhoneOrEmail(),loginRequest.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (AuthenticationException e){
            //处理登录错误
            Result<Boolean> booleanResult = authenticationFailureHandler.onAuthenticationFailure(e);
            return booleanResult;
        }
        return Result.ofSuccess(Boolean.TRUE);

    }
    @ApiOperation(value = "获取当前登录用户信息")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
            @ApiResponse(code=4401,message="当前没有登录用户"),
    })
    @GetMapping("getlogin")
    public Result<UserVTO> getLogin(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.equals("anonymousUser")){
            return Result.ofFail("4401","当前没有用户登录");
        }
        UserVTO userVTO = irUserService.findUserByphoneOrEmail(((UserDetails) principal).getUsername());
        return  Result.ofSuccess(userVTO);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
            @ApiResponse(code=4401,message="当前没有登录用户"),
    })
    @GetMapping("getUserList")
    public Result<List<UserListVTO>> getUserList(){
        return irUserService.getUserList();
    }













}
