package com.piesat.school.generationMode.vto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "生成方式模型")
@Data
public class GenerationModeVTO implements Serializable {
    @ApiModelProperty(value = "序号")
    private Long id;

    @ApiModelProperty(value = "标注")
    private String comment;

    @ApiModelProperty(value = "标注")
    private Integer number;
}
