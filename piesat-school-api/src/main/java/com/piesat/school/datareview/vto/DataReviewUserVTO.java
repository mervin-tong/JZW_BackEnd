package com.piesat.school.datareview.vto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author suweipeng
 * @data 2022/3/14 9:41
 */
@Data
public class DataReviewUserVTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 评审id
     */
    @ApiModelProperty(value = "评审id")
    private Long id;

    /**
     * 数据名称
     */
    @ApiModelProperty(value = "数据名称")
    private String dataName;

    /**
     * 数据地址
     */
    @ApiModelProperty(value = "数据地址")
    private String address;

    /**
     * 公开方式
     */
    @ApiModelProperty(value = "公开方式")
    private String status;

    /**
     * 数据采集者
     */
    @ApiModelProperty(value = "数据采集者")
    private String maker;

    /**
     * 提交时间
     */
    @ApiModelProperty(value = "提交时间")
    private String createdAt;

    /**
     * 评审状态
     */
    @ApiModelProperty(value = "评审状态")
    private String reviewStatus;

    /**
     * 审核时间
     */
    @ApiModelProperty(value = "审核时间")
    private String updatedAt;

    /**
    * 二审时间
    * */

    @ApiModelProperty(value = "二审时间")
    private String recheckedAt;

}
