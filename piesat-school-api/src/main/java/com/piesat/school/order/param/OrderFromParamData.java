package com.piesat.school.order.param;

import com.smartwork.api.param.UIDParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author suweipeng
 * @data 2022/2/24 15:48
 */
@Data
public class OrderFromParamData extends UIDParamData {
    private static final long serialVersionUID = 1L;


    /**
     * 下载用户id
     */
    @ApiModelProperty(value = "下载的用户id")
    private Long downloadUserId;

    /**
     * 审核人员id
     */
    @ApiModelProperty(value = "审核人员id")
    private Long auditorUserId;

    /**
     * 文章id
     */
    @ApiModelProperty(value = "文章id")
    private Long dataInfoId;
    /**
     * 公开状态（1.完全公开 2.申请获取 3.保护期内）
     */
    @ApiModelProperty(value = "公开状态（1.完全公开 2.申请获取 3.保护期内）")
    private Integer status;

    /**
     * 订单说明
     */
    @ApiModelProperty(value = "订单说明")
    private String declare;







}
