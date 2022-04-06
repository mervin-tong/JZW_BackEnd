package com.piesat.school.order.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderAuditParamData implements java.io.Serializable{
    @ApiModelProperty(value = "订单id")
    private Long orderId;
    @ApiModelProperty(value = "审核意见")
    private String auditMark;
    @ApiModelProperty(value = "数据状态（2.同意下载 3.拒绝下载）")
    private Integer auditStatus;
}
