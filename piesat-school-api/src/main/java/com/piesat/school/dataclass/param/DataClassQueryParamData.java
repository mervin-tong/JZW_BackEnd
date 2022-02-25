package com.piesat.school.dataclass.param;

import com.smartwork.api.param.PageQueryParamData;
import com.smartwork.api.param.UIDParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class DataClassQueryParamData extends PageQueryParamData{

    @ApiModelProperty(value = "分类id ，更新添加数据必传")
    private Long id;

    @ApiModelProperty(value = "第一类别")
    private String firstClass;

    @ApiModelProperty(value = "第二类别")
    private String secClass;

    @ApiModelProperty(value = "是否被删除")
    private Integer status;
}
