package com.piesat.school.information.param;

import com.piesat.school.base.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class InformationPageParam extends PageQueryParamData {
    @ApiModelProperty(value = "资讯类别")
    private Integer type;
}
