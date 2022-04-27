package com.piesat.school.datainf.vto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "数据统计")
public class StatisticsVTO {

    /**
     * 数据集总数
     */
    @ApiModelProperty(value = "数据集总数")
    private Integer total;

    /**
     * 开放数据集数
     */
    @ApiModelProperty(value = "开放数据集数")
    private Long openDataNum;

    /**
     * 半开放数据集数
     */
    @ApiModelProperty(value = "半开放数据集数")
    private Long semiopenDataNum;

    /**
     * 非开放数据集数
     */
    @ApiModelProperty(value = "非开放数据集数")
    private Long closedDataNum;

    /**
     * 注册会员数
     */
    @ApiModelProperty(value = "注册会员数")
    private Integer registereMembers;

    /**
     * 浏览数
     */
    @ApiModelProperty(value = "浏览数")
    private Long pageViews;

    /**
     * 下载数
     */
    @ApiModelProperty(value = "下载数")
    private Long downloadNum;
}
