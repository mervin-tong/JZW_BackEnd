package com.piesat.school.order.param;

import com.smartwork.api.param.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author suweipeng
 * @data 2022/2/28 10:28
 */
@Data
public class OrderFromAttentionParamData extends PageQueryParamData {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户id")
    private Long id;


}
