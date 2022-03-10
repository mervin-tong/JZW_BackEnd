package com.piesat.school.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author suweipeng
 * @data 2022/3/10 10:15
 */
@Data
public class LoginRequest {
    //手机号或邮箱
    @ApiModelProperty(value = "手机号或邮箱")
    private String phoneOrEmail;
    //密码
    @ApiModelProperty(value = "密码")
    private String password;
}
