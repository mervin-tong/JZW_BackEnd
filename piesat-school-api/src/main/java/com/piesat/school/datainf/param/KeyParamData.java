package com.piesat.school.datainf.param;

import com.smartwork.api.param.UIDParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class KeyParamData extends UIDParamData {
    @ApiModelProperty(value = "关键字")
    private String keyword;
}
