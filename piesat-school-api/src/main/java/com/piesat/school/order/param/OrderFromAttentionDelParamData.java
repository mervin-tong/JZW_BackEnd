package com.piesat.school.order.param;

import com.smartwork.api.param.UIDParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderFromAttentionDelParamData extends UIDParamData {
    @ApiModelProperty(value = "要取消关注的关注号")
    private String ids;
}
