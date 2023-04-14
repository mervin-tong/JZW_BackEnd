package com.piesat.school.order.vto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author suweipeng
 * @data 2022/2/24 10:28
 */
@Data
public class OrderFromVTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private Long id;

    /**
     * 数据审核状态（1.申请下载  2.同意下载 3.拒绝下载）
     */
    @ApiModelProperty(value = "数据审核状态，1.申请下载  2.同意下载 3.拒绝下载 4.已过期")
    private Long dataType;

    /**
     * 下载用户id
     */
    @ApiModelProperty(value = "下载用户id")
    private Long downloadUserId;

    /**
     * 对应数据名称
     */
    @ApiModelProperty(value = "对应数据名称")
    private String dataName;
    @ApiModelProperty(value = "数据id")
    private Long dataInfoId;

    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址")
    private String pic;

    /**
     * 公开状态（1.完全公开 2.申请获取 3.保护期内）
     */
    @ApiModelProperty(value = "公开状态")
    private Integer status;

    /**
     * 订单说明
     */
    @ApiModelProperty(value = "订单说明")
    private String mark;

    @ApiModelProperty(value = "数据所属开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date startAt;

    /**
     * 数据所属结束时间
     */
    @ApiModelProperty(value = "数据所属结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date endAt;


    @ApiModelProperty(value = "订单创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createdAt;

    @ApiModelProperty(value = "订单更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updatedAt;
    /**
     * 数据量
     */
    @ApiModelProperty(value = "数据大小")
    private String dataAmount;
    @ApiModelProperty(value = "申请人名称")
    private String downloadUserName;
    @ApiModelProperty(value = "申请人工作单位")
    private String downloadUserWorkUnit;
    @ApiModelProperty(value = "申请人ID")
    private Long auditorUserId;

    @ApiModelProperty(value = "签入者")
    private String checkMan;
    @ApiModelProperty(value = "签入状态")
    private Integer checkStatus;
}
