package com.piesat.school.user.param;

import com.smartwork.api.param.ParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LimitUserParamData extends ParamData {
    @ApiModelProperty(value = "被操作用户id")
    private Long userId;
    @ApiModelProperty(value = "封禁状态 0解封 1封禁")
    private Integer limitStatus;
}
