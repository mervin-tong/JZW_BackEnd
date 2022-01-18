package com.piesat.school.rest.controller.app.user;

import com.piesat.school.user.iservice.IRUserService;
import com.piesat.school.user.vto.UserVTO;
import com.smartwork.api.Result;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lawliet
 * @date 2021-06-07
 * <p>
 * 用户设备功能相关
 */

@Api(tags = "用户设备模块")
@RestController
@RequestMapping("/app/user")
public class UserController
{
    @DubboReference
    private IRUserService userService;
    @GetMapping("/getuser")
    public Result<UserVTO> getUser(){
        return userService.getUser();
    }


}
