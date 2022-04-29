package com.piesat.school.order.vto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author suweipeng
 * @data 2022/2/25 14:33
 */
@Data
public class OrderFromAttentionVTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 数据id
     */
    @ApiModelProperty(value = "数据id")
    private Long dataId;

    /**
     * 数据名称
     */
    @ApiModelProperty(value = "数据名称")
    private String dataName;

    /**
     * 数据时间
     */
    @ApiModelProperty(value = "数据时间")
    private Date createdAt;

    /**
     * 公开状态
     */
    @ApiModelProperty(value = "公开状态")
    private Integer status;

    /**
     * 空间地址
     */
    @ApiModelProperty(value = "空间地址")
    private String address;

    /**
     * 上传者是否被封禁
     */
    @ApiModelProperty(value = "上传者是否被封禁")
    private String publisherStatus;

    /**
     * 上传者id
     */
    @ApiModelProperty(value = "上传者id")
    private Long uploadUserId;

    /**
     * 数据开始时间
     */
    @ApiModelProperty(value = "数据开始时间")
    private Date startAt;

    /**
     * 数据结束时间
     */
    @ApiModelProperty(value = "数据结束时间")
    private Date endAt;

    /**
     * 数据是否已加入订单
     */
    @ApiModelProperty(value = "数据是否已加入订单0未加入 1加入")
    private Integer isAddOrder;

}
