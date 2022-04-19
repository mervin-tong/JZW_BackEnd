package com.piesat.school.user.param;

import com.smartwork.api.param.ParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FeedBackParamData extends ParamData {
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "反馈内容")
    private String content;
}
