package com.piesat.school.topic.param;

import com.smartwork.api.param.ParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TopicDataAddParamData extends ParamData {
    @ApiModelProperty(value = "专题id")
    private Long topicId;
    @ApiModelProperty(value = "数据ids， id以逗号分割")
    private String dataIds;
}
