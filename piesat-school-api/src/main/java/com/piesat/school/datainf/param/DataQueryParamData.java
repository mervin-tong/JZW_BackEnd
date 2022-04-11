package com.piesat.school.datainf.param;

import com.smartwork.api.param.ParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DataQueryParamData extends ParamData {
    @ApiModelProperty(value = "审核状态，0未通过，1通过")
    private Integer auditStatus=1;
    @ApiModelProperty(value = "发布者id")
    private Long publisher;
}
