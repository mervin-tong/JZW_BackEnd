package com.piesat.school.uploadpermissions.param;

import com.smartwork.api.param.ParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UploadPermissionCloseUpParamData extends ParamData {

    @ApiModelProperty(value = "申请id")
    private Long userId;

    @ApiModelProperty(value = "是否关闭")
    private Boolean isClose=true;
}
