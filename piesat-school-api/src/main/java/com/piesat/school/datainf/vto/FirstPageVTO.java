package com.piesat.school.datainf.vto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "首页信息模型")
public class FirstPageVTO implements Serializable {

    /**
     * 一级菜单名称
     */
    @ApiModelProperty(value = "一级菜单名称")
    private String name;

    /**
     * 详情列表
     */
    @ApiModelProperty(value = "详情列表")
    private List<DataInfDetailVTO> detailVTOS;
}
