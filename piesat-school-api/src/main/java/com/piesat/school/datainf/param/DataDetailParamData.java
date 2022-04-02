package com.piesat.school.datainf.param;

import com.smartwork.api.param.ParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DataDetailParamData extends ParamData {
    @ApiModelProperty(value = "数据id")
    private Long id;
    @ApiModelProperty(value = "订单id")
    private Long orderId;
}
