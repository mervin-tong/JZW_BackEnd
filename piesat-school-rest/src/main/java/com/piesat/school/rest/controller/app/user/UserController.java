package com.piesat.school.rest.controller.app.user;

import io.swagger.annotations.Api;
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
@RequestMapping("/app/user/handset")
public class UserController {
//    @DubboReference(consumer = DubboConstant.CONSUMER_NAME)
//    private IRUserService userService;
//
//    @ApiOperation(value = "用户绑定设备标记", notes = "使用场景：" +
//            "\n 1：极光推送：" +
//            "\n 用户未登录，不需要调用此接口" +
//            "\n 用户登录后，客户端极光SDK，首先设置极光Alias别名为用户ID." +
//            "\n 然后调用此接口提交极光RegistrationId（此接口可异步调用），方便服务端后续进行定点极光推送" +
//            "\n \n Gateway Api: engine.piesat.school.app.userHandsetBinding")
//    @PostMapping("/binding")
//    public Result<Boolean> refreshHandsetBinding(@RequestBody UserHandsetBindingParamData paramData){
//        return userService.refreshHandsetBinding(paramData);
//    }
}
