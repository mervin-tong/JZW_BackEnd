package com.piesat.school.rest.controller.app.user;

import com.piesat.school.rest.constants.DubboConstant;
import com.piesat.school.security.JsonResult;
import com.piesat.school.security.ResultTool;
import com.piesat.school.user.iservice.IRUserService;
import com.piesat.school.user.vto.UserVTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author suweipeng
 * @data 2022/2/15 16:13
 */
@RestController
public class UserController {
    @DubboReference(consumer = DubboConstant.CONSUMER_NAME)
    private IRUserService userService;
    @GetMapping("/getUser")
    public JsonResult getUser() {
        UserVTO userByPhone = userService.findUserByPhone("1234567");
        return ResultTool.success(userByPhone);
    }


}
