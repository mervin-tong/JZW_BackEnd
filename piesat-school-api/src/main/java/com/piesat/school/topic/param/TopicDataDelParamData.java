package com.piesat.school.topic.param;

import com.smartwork.api.param.ParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TopicDataDelParamData extends ParamData {
    @ApiModelProperty(value = "数据id")
    private Long dataId;
    @ApiModelProperty(value = "专题id")
    private Long topicId;
}
