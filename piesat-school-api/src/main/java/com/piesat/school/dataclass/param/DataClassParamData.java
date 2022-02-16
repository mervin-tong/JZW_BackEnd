package com.piesat.school.dataclass.param;

import com.smartwork.api.param.UIDParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DataClassParamData extends UIDParamData {
    @ApiModelProperty(value = "分类id ，更新添加数据必传")
    private Long id;

    @ApiModelProperty(value = "第一类别")
    @NotBlank(message = "第一类别不能为空")
    private String firstClass;

    @ApiModelProperty(value = "第二类别")
    @NotBlank(message = "第二类别不能为空")
    private String secClass;
}
