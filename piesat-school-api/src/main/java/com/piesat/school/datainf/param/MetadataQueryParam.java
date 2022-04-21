package com.piesat.school.datainf.param;

import com.piesat.school.base.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MetadataQueryParam  extends PageQueryParamData {
    @ApiModelProperty(value = "专题id")
    private Long topicId;
    @ApiModelProperty(value = "数据名称")
    private String dataName;
}
