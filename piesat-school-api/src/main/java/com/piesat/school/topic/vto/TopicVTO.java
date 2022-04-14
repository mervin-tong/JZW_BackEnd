package com.piesat.school.topic.vto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "专题数据完整信息模型")
@Data
public class TopicVTO implements Serializable {
    @ApiModelProperty(value = "专题介绍")
    private String description;
    @ApiModelProperty(value = "专题名")
    private String topicName;
    @ApiModelProperty(value = "专题图片")
    private String picture;
    @ApiModelProperty
    private int dataNum;
}
