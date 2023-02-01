package com.piesat.school.banner.param;

import com.piesat.school.base.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/01/04/18:05
 * @Description:
 */
@Data
public class BannerParam extends PageQueryParamData {
    private static final long serialVersionUID = 1L;
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
