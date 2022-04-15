package com.piesat.school.datainf.param;

import com.piesat.school.base.PageQueryParamData;
import com.smartwork.api.param.ParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DataQueryParamData extends PageQueryParamData {
    @ApiModelProperty(value = "审核状态，0未通过，1通过")
    private Integer auditStatus;
    @ApiModelProperty(value = "发布者id")
    private Long publisher;
}
