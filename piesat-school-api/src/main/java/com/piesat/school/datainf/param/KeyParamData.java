package com.piesat.school.datainf.param;

import com.smartwork.api.param.UIDParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class KeyParamData implements Serializable {
    @ApiModelProperty(value = "关键字")
    private String keyword;
}
