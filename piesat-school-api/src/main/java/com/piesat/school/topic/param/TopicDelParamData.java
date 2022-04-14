package com.piesat.school.topic.param;

import com.smartwork.api.param.ParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TopicDelParamData extends ParamData {
    @ApiModelProperty(value = "专题id")
    private Long id;
}
