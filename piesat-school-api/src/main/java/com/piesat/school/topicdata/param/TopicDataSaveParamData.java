package com.piesat.school.topicdata.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TopicDataSaveParamData implements Serializable {
    @ApiModelProperty(value = "专题id")
    private Long id;
    @ApiModelProperty(value = "专题介绍")
    private String topicIntroduction;
    @ApiModelProperty(value = "专题名")
    private String topicName;
    @ApiModelProperty(value = "专题图片")
    private String picture;
}
