package com.piesat.school.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdatePasswordParamData implements java.io.Serializable{
    @ApiModelProperty(value = "用户id")
    private Long id;
    @ApiModelProperty(value = "原密码")
    private String formerPassword;
    @ApiModelProperty(value = "新密码")
    private String newPassword;
    @ApiModelProperty(value = "确认密码")
    private String reNewPassword;
}
