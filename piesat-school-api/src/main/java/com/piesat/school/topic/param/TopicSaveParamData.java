package com.piesat.school.topic.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TopicSaveParamData implements Serializable {
    @ApiModelProperty(value = "专题id")
    private Long id;
    @ApiModelProperty(value = "专题介绍")
    private String description;
    @ApiModelProperty(value = "专题名")
    private String name;
    @ApiModelProperty(value = "专题图片")
    private String picture;
}
