package com.piesat.school.dataclass.param;

import com.smartwork.api.param.UIDParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;
@Data
public class DataClassDelParamData {
    @ApiModelProperty(value = "删除id")
    private List<Long> ids;

    @ApiModelProperty(value = "是否清零")
    private boolean clear;

}
