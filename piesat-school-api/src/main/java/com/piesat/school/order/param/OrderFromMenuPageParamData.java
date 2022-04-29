package com.piesat.school.order.param;

import com.piesat.school.base.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author suweipeng
 * @data 2022/2/25 15:30
 */
@Data
public class OrderFromMenuPageParamData extends PageQueryParamData {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "数据状态（1.申请下载(审核中)  2.同意下载 3.拒绝下载）")
    private Long dataType;
    @ApiModelProperty(value = "审核人员")
    private Long auditorUserId;
    @ApiModelProperty(value = "订单所属人员id")
    private Long downloadUserId;


}
