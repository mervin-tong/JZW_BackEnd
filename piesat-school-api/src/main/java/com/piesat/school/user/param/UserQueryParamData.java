package com.piesat.school.user.param;

import com.piesat.school.base.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQueryParamData extends PageQueryParamData {
    @ApiModelProperty(value = "查询条件")
    private String condition;

}
