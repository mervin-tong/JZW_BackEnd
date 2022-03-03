package com.piesat.school.datainf.param;

import com.smartwork.api.param.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchByClassParamData extends PageQueryParamData {
    @ApiModelProperty(value = "第一分类")
    private String firstClass;
    @ApiModelProperty(value = "第二分类")
    private String secClass;
}
