package com.piesat.school.topicdata.vto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "专题详细信息模型")
public class TopicDataDetailVTO implements Serializable {
    @ApiModelProperty(value = "专题介绍")
    private String topicIntroduction;
    @ApiModelProperty(value = "专题名")
    private String topicName;
    @ApiModelProperty(value = "专题图片")
    private String picture;

    private List<DataInfTopicVTO> results;
//    @ApiModelProperty(value = "数据名")
//    private String dataName;
//    @ApiModelProperty(value = "数据量")
//    private String dataAmount;
//    @ApiModelProperty(value = "下载量")
//    private Integer downCount;
//    @ApiModelProperty(value = "图片地址")
//    private String pic;
//    @ApiModelProperty(value = "公开状态，1.完全公开 2.申请获取 3.保护期内")
//    private Integer status;
//    @ApiModelProperty(value = "数据摘要")
//    private String introduction;

}
