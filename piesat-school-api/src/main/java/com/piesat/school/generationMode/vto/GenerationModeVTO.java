package com.piesat.school.generationMode.vto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "生成方式模型")
@Data
public class GenerationModeVTO {
    @ApiModelProperty(value = "序号")
    private Long id;

    @ApiModelProperty(value = "生成方式")
    private Integer generationMode;

    @ApiModelProperty(value = "标注")
    private String comment;
}
