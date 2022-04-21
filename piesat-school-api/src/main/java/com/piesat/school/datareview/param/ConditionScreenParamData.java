package com.piesat.school.datareview.param;

import com.smartwork.api.param.ParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ConditionScreenParamData extends ParamData {
    @ApiModelProperty(value = "目标参数",required = true)
    private String target;
    @ApiModelProperty(value = "标题名称")
    private String dataName;
    @ApiModelProperty(value = "起始时间")
    private String start;
    @ApiModelProperty(value = "终止时间")
    private String end;
    @ApiModelProperty(value = "评审状态")
    private String status;
}
