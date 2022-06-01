package com.piesat.school.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author suweipeng
 * @data 2022/2/23 15:24
 */
@Data
public class ForgetPasswordParamData implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "注册邮箱")
    private String email;
    @ApiModelProperty(value = "验证码",example = "0")
    private Integer userVerificationCode;
    @ApiModelProperty(value = "新密码")
    private String newPassword;
    @ApiModelProperty(value = "确认密码")
    private String reNewPassword;

}
