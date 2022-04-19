package com.piesat.school.rest.controller.app.user;

import com.piesat.school.rest.constants.DubboConstant;
import com.piesat.school.security.handler.CustomizeAuthenticationFailureHandler;
import com.piesat.school.user.iservice.IRUserService;
import com.piesat.school.user.param.*;
import com.piesat.school.user.vto.UserVTO;
import com.smartwork.api.support.page.TailPage;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import com.smartwork.api.Result;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author suweipeng
 * @data 2022/2/15 16:13
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/app/user")
public class UserController {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private CustomizeAuthenticationFailureHandler authenticationFailureHandler;
    @DubboReference
    private IRUserService irUserService;
    @DubboReference(consumer = DubboConstant.CONSUMER_NAME)
    private IRUserService userService;
//    @Secured({"ROLE_ADMIN","ROLE_EGCADMIN"})
//    @ApiOperation(value = "发送邮箱验证码")
//    @ApiResponses({
//            @ApiResponse(code=0,message="操作成功"),
//            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
//            @ApiResponse(code=500,message="后台报错"),
//            @ApiResponse(code=403,message="没有权限")
//    })
//    @GetMapping("/getUserInfo")
//    //获取用户
//    public Result<UserVTO> getUser() {
//        return Result.ofSuccess(userService.findUserByPhoneOrEmail("zhangsan@163.com"));
//    }


    //注册用户
    @ApiOperation(value = "注册用户")
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
    public Result<Boolean> forgetPassword(ForgetPasswordParamData forgetPasswordParamData){
        return userService.forgetPassword(forgetPasswordParamData);
    }

    //登录
//    @ApiOperation(value = "登录")
//    @ApiResponses({
//            @ApiResponse(code=0,message="密码修改成功"),
//            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
//            @ApiResponse(code=500,message="后台报错"),
//            @ApiResponse(code=2001,message="用户未登录"),
//            @ApiResponse(code=2002,message="账号已过期"),
//            @ApiResponse(code=2003,message="密码错误"),
//            @ApiResponse(code=2004,message="密码过期"),
//            @ApiResponse(code=2005,message="账号不可用"),
//            @ApiResponse(code=2006,message="账号被锁定"),
//            @ApiResponse(code=2007,message="账号不存在"),
//    })
//    @PostMapping("/login")
//    public Result<UserVTO> login(LoginRequest loginRequest) {
//        UsernamePasswordAuthenticationToken token =
//                new UsernamePasswordAuthenticationToken(loginRequest.getPhoneOrEmail(),loginRequest.getPassword());
////        try {
//            Authentication authentication = authenticationManager.authenticate(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
////            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////            if (principal.equals("anonymousUser")){
////                return Result.ofFail("4401","当前没有用户登录");
////            }
////            UserVTO userVTO = irUserService.findUserByPhoneOrEmail(((UserDetails) principal).getUsername());
////            return Result.ofSuccess(userVTO);
////        }catch (AuthenticationException e){
////            //处理登录错误
////            Result<Boolean> booleanResult = authenticationFailureHandler.onAuthenticationFailure(e);
////            return booleanResult;
////        }
//
//
//    }
//    @ApiOperation(value = "获取当前登录用户信息")
//    @ApiResponses({
//            @ApiResponse(code=0,message="访问成功"),
//            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
//            @ApiResponse(code=500,message="后台报错"),
//            @ApiResponse(code=4401,message="当前没有登录用户"),
//    })
//    @GetMapping("getLogin")
//    public Result<UserVTO> getLogin(){
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal.equals("anonymousUser")){
//            return Result.ofFail("4401","当前没有用户登录");
//        }
//        UserVTO userVTO = irUserService.findUserByPhoneOrEmail(((UserDetails) principal).getUsername());
//        return  Result.ofSuccess(userVTO);
//    }

    @ApiOperation(value = "获取用户列表信息")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
            @ApiResponse(code=4401,message="当前没有登录用户"),
    })
    @GetMapping("getUserList")
    public Result<TailPage<UserVTO>> getUserList(UserQueryParamData paramData){
        return irUserService.getUserList(paramData);
    }


    @ApiOperation(value = "用户信息修改")
    @PostMapping("updateUser")
    public Result<UserVTO> updateUser(UpdateUserParamData paramData){
        return irUserService.updateUser(paramData);
    }

    @ApiOperation(value = "修改密码")
    @ApiResponses({
            @ApiResponse(code=0,message="修改成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
            @ApiResponse(code=4401,message="原密码错误"),
            @ApiResponse(code=4402,message="密码修改失败"),
    })
    @PostMapping("updatePassword")
    public Result<Boolean> updatePassword(UpdatePasswordParamData paramData){
        return irUserService.updatePassword(paramData);
    }
    @ApiOperation(value = "封禁/解封用户")
    @PostMapping("limitUser")
    public Result<Boolean> limitUser(LimitUserParamData paramData){
        return irUserService.limitUser(paramData);
    }


    //发送邮箱验证码
    @ApiOperation(value = "意见反馈")
    @PostMapping("/feedback")
    public Result<Boolean> feedback(FeedBackParamData paramData){
        return userService.feedback(paramData);
    }
}
