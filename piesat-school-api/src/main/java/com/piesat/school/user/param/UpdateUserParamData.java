package com.piesat.school.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateUserParamData implements java.io.Serializable{
    @ApiModelProperty(value = "用户id")
    private Long id;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "工作单位")
    private String workUnit;
    @ApiModelProperty(value = "单位地址")
    private String unitAddress;
    @ApiModelProperty(value = "最高学历")
    private String highEducation;
    @ApiModelProperty(value = "从事专业")
    private String profession;
    @ApiModelProperty(value = "用户头像链接")
    private String avatar;
}
