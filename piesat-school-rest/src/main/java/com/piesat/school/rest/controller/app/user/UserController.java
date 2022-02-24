package com.piesat.school.rest.controller.app.user;

import com.piesat.school.rest.constants.DubboConstant;
import com.piesat.school.security.JsonResult;
import com.piesat.school.security.ResultTool;
import com.piesat.school.user.iservice.IRUserService;
import com.piesat.school.user.param.ForgetPasswordParamData;
import com.piesat.school.user.param.UserParamData;
import com.piesat.school.user.vto.UserVTO;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.smartwork.api.Result;
import java.util.List;

/**
 * @author suweipeng
 * @data 2022/2/15 16:13
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/app/user")
public class UserController {
    @DubboReference(consumer = DubboConstant.CONSUMER_NAME)
    private IRUserService userService;
    @Secured({"ROLE_ADMIN","ROLE_EGCADMIN"})
    @GetMapping("/getUserInfo")
    //获取用户
    public Result<UserVTO> getUser() {
        return Result.ofSuccess(userService.findUserByphoneOrEmail("zhangsan@163.com"));
    }

    //注册用户
    @PostMapping("/addUser")
    public Result<UserVTO> addUser(@RequestBody UserParamData userParamData){
        return userService.addUser(userParamData);
    }

    //发送邮箱验证码
    @GetMapping("/sendEmail")
    public Result<Boolean> sendEmail(String email){
        return userService.sendEmail(email);
    }

    //忘记密码
    @PostMapping("/forgetPassword")
    public Result<Boolean> forgetPassword(@RequestBody ForgetPasswordParamData forgetPasswordParamData){
        return userService.forgetPassword(forgetPasswordParamData);
    }








}
