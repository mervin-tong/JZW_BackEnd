package com.piesat.school.datainf.vto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DataInfDetailVTO implements Serializable {
    /**
     * 数据名
     */
    @ApiModelProperty(value = "数据名")
    private String dataName;

    /**
     * 制作者
     */
    @ApiModelProperty(value = "制作者")
    private String maker;
    /**
     * 数据所属单位
     */
    @ApiModelProperty(value = "数据所属单位")
    private String dataUnit;

    /**
     * 数据地址
     */
    @ApiModelProperty(value = "数据地址")
    private String address;

    /**
     * 主题词
     */
    @ApiModelProperty(value = "主题词")
    private String keyword;

    /**
     * 数据第一分类
     */
    @ApiModelProperty(value = "数据第一分类")
    private String firstClass;

    /**
     * 数据第二分类
     */
    @ApiModelProperty(value = "数据第二分类")
    private String secClass;
    /**
     * 尺度
     */
    @ApiModelProperty(value = "尺度")
    private String meas;

    /**
     * 分辨率
     */
    @ApiModelProperty(value = "分辨率")
    private String ratio;

    /**
     * 唯一标识符
     */
    @ApiModelProperty(value = "唯一标识符")
    private String doi;
    /**
     * 公开状态：0 公开；1半公开；2不公开
     */
    @ApiModelProperty(value = "公开状态，0 公开；1半公开；2不公开")
    private Integer status;

    /**
     * 数据摘要
     */
    @ApiModelProperty(value = "数据摘要")
    private String introduction;

    /**
     * 数据来源
     */
    @ApiModelProperty(value = "数据来源")
    private String origin;

    /**
     * 数据制作方法
     */
    @ApiModelProperty(value = "数据制作方法")
    private String solution;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "联系人姓名")
    private String conName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "联系人手机号")
    private String mobile;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "联系人邮箱")
    private String email;

    /**
     *
     * 单位
     */
    @ApiModelProperty(value = "联系人单位")
    private String conUnit;

    /**
     * 地址
     */
    @ApiModelProperty(value = "联系人地址")
    private String conAddress;

    /**
     * 数据所属开始时间
     */
    @ApiModelProperty(value = "数据所属开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date startAt;

    /**
     * 数据所属结束时间
     */
    @ApiModelProperty(value = "数据所属结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date endAt;


}