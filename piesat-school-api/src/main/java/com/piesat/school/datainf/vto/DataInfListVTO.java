package com.piesat.school.datainf.vto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DataInfListVTO implements Serializable {

    /**
     * 数据名
     */
    @ApiModelProperty(value = "数据名")
    private String dataName;
    /**
     * 数据量
     */
    @ApiModelProperty(value = "数据量")
    private String dataAmount;
    /*
    *
     * 下载量
     */
    @ApiModelProperty(value = "下载量")
    private Integer downCount;
    /**
     * 图片
     */
    @ApiModelProperty(value = "图片地址")
    private String pic;

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

    /**
     * 公开状态（1.完全公开 2.申请获取 3.保护期内）
     */
    @ApiModelProperty(value = "公开状态，0 公开；1半公开；2不公开")
    private Integer status;






}
