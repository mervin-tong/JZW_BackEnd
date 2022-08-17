package com.piesat.school.datainf.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.piesat.school.base.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AuditApplyListParamData extends PageQueryParamData{
    @ApiModelProperty(value = "申请人")
    private String name;
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "申请状态")
    private Integer applyStatus;
    @ApiModelProperty(value = "工作单位")
    private String workUnit;
    @ApiModelProperty(value = "申请时间")
    @JsonFormat(timezone="GMT+8")
    private Date createdAt;
    @ApiModelProperty(value = "更新后的时间")
    @JsonFormat(timezone="GMT+8")
    private Date updatedAt;
    @ApiModelProperty(value = "手机号")
    private Long phone;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "从事专业")
    private String profession;
    @ApiModelProperty(value = "单位地址")
    private String unitAddress;
    @ApiModelProperty(value = "最高学历")
    private String highEducation;
    @ApiModelProperty(value = "用途说明")
    private String applyExplain;


}
