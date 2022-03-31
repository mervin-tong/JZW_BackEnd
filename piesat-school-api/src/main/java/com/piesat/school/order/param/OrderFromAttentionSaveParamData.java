package com.piesat.school.order.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderFromAttentionSaveParamData implements Serializable {
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "数据id")
    private Long dataId;
}
