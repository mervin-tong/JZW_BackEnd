package com.piesat.school.user.param;

import com.smartwork.api.param.UIDParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author suweipeng
 * @data 2022/2/16 22:41
 */
@Data
public class UserParamData extends UIDParamData {
    @ApiModelProperty(value = "密码")
    String password;
    @ApiModelProperty(value = "手机号")
    String phone;
    @ApiModelProperty(value = "姓名")
    String name;
    @ApiModelProperty(value = "邮箱")
    String email;
    @ApiModelProperty(value = "账号状态")
    String status;
    @ApiModelProperty(value = "工作单位")
    String workUnit;
    @ApiModelProperty(value = "单位地址")
    String unitAddress;
    @ApiModelProperty(value = "最高学历")
    String highEducation;
    @ApiModelProperty(value = "从事专业")
    String profession;
    @ApiModelProperty(value = "验证码")
    Integer userVerificationCode;
}
