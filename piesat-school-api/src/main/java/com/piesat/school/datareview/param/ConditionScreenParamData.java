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
    @ApiModelProperty(value = "提交时间")
    private Date createdAt;
    @ApiModelProperty(value = "评审状态")
    private String status;
}
