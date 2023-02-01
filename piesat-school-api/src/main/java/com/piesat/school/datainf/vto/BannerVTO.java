package com.piesat.school.datainf.vto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/01/04/17:35
 * @Description:
 */
@Data

public class BannerVTO implements Serializable {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "图片url")
    private String url;
    @ApiModelProperty(value = "图片名称")
    private String name;
    @ApiModelProperty("创建时间")
    private Date createdAt;
    @ApiModelProperty("更新时间")
    private Date updatedAt;


}
