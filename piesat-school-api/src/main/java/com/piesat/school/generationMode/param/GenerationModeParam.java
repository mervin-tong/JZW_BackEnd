package com.piesat.school.generationMode.param;

import com.smartwork.api.param.ParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GenerationModeParam extends ParamData {

    @ApiModelProperty(value = "序号")
    private Integer id;

    @ApiModelProperty(value = "生成方式")
    private Integer generationMod;

    @ApiModelProperty(value = "标注")
    private String comment;
}
