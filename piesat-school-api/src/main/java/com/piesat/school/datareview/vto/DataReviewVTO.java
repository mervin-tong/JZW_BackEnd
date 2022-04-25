package com.piesat.school.datareview.vto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author suweipeng
 * @data 2022/3/8 15:36
 */
@Data
public class DataReviewVTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 数据名称
     */
    @ApiModelProperty(value = "数据名称")
    private String dataName;

    /**
     * 数据所属开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @ApiModelProperty(value = "数据所属开始时间")
    private Date startAt;

    /**
     * 数据所属结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @ApiModelProperty(value = "数据所属结束时间")
    private Date endAt;

    /**
     * 上传用户id
     */
    @ApiModelProperty(value = "上传用户id")
    private Long uploadUserId;

    /**
     * 上传者姓名
     */
    @ApiModelProperty(value = "上传者姓名")
    private String uploadName;

    /**
     * 数据地址
     */
    @ApiModelProperty(value = "数据地址")
    private String address;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    private String conName;

    /**
     * 数据单位
     */
    @ApiModelProperty(value = "数据单位")
    private String dataUnit;

    /**
     * 数据创建时间
     */
    @ApiModelProperty(value = "数据创建时间")
    private String createdAt;

    /**
     * 评审状态
     */
    @ApiModelProperty(value = "评审状态")
    private String status;

    /**
     *  数据id
     */
    @ApiModelProperty(value = "数据id")
    private String id;

    /**
     *  未通过原因
     */
    @ApiModelProperty(value = "未通过原因")
    private String noPassReason;

    /**
     *  评审人 (一审)
     */
    @ApiModelProperty(value = "评审人 (一审) ")
    private Long adminJudgeId;

    /**
     *  指定专家（二审）
     */
    @ApiModelProperty(value = "指定专家（二审）")
    private Long userJudgeId;
}
