package com.piesat.school.uploadpermissions.param;

import com.smartwork.api.param.ParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UploadPermissionAddParamData extends ParamData {
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "申请理由")
    private String reason;
}
