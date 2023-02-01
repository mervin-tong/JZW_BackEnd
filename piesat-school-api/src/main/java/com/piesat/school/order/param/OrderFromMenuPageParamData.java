package com.piesat.school.order.param;

import com.piesat.school.base.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author suweipeng
 * @data 2022/2/25 15:30
 */
@Data
public class OrderFromMenuPageParamData extends PageQueryParamData {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "数据状态（1.申请下载(审核中)  2.同意下载 3.拒绝下载  4、审核通过后过期的订单）")
    private Long dataType;
    @ApiModelProperty(value = "审核人员")
    private Long auditorUserId;
    @ApiModelProperty(value = "订单所属人员id")
    private Long downloadUserId;
    @ApiModelProperty(value = "数据真状态")
    private Long type;
    @ApiModelProperty(value = "申请人")
    private String downloadUserName;
    @ApiModelProperty(value = "申请开始时间")
    private String startDate;
    @ApiModelProperty(value = "申请结束时间")
    private String endDate;


}
