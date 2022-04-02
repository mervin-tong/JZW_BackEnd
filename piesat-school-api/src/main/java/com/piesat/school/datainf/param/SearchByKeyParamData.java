package com.piesat.school.datainf.param;

import com.piesat.school.base.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchByKeyParamData extends PageQueryParamData {
    @ApiModelProperty(value = "关键字")
    private String keyword;
}
