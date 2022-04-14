package com.piesat.school.topic.param;

import com.piesat.school.base.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TopicQueryParamData extends PageQueryParamData {
    @ApiModelProperty(value = "")
    private Long topicId;
}
