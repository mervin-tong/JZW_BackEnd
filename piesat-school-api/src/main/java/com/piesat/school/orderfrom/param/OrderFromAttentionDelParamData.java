package com.piesat.school.orderfrom.param;

import com.smartwork.api.param.UIDParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderFromAttentionDelParamData extends UIDParamData {
    @ApiModelProperty(value = "关注id")
    private Long id;
}
