package com.piesat.school.satellite.vto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/03/28/13:46
 * @Description:
 */
@Data
public class SatelliteVTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String location;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String date;
    /**
     * 尺寸
     */
    @ApiModelProperty(value = "尺寸")
    private String size;
}
