package com.piesat.school.topicdata.vto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "专题数据完整信息模型")
@Data
public class TopicDataVTO implements Serializable {
    @ApiModelProperty(value = "专题介绍")
    private String topicIntroduction;
    @ApiModelProperty(value = "专题名")
    private String topicName;
    @ApiModelProperty(value = "专题图片")
    private String picture;
}
