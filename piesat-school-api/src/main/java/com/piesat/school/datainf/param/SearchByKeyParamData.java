package com.piesat.school.datainf.param;

import com.smartwork.api.param.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchByKeyParamData extends PageQueryParamData {
    @ApiModelProperty(value = "关键字")
    private String keyword;
}
