package com.piesat.school.uploadpermissions.param;

import com.smartwork.api.param.ParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UploadPermissionOperateParamData extends ParamData {
    @ApiModelProperty(value = "申请id")
    private Long id;
    @ApiModelProperty(value = "是否允许")
    private Boolean isAllow;
    @ApiModelProperty(value = "审批意见")
    private String mark;
    @ApiModelProperty(value = "审核者id")
    private Long approver;
}
