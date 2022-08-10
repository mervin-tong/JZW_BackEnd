package com.piesat.school.datainf.param;

import com.piesat.school.base.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WebLinkParamData extends PageQueryParamData {
    @ApiModelProperty(value = "链接的id")
    private Long id;
    @ApiModelProperty(value = "网站名称")
    private String webName;
    @ApiModelProperty(value = "网站链接")
    private String webUrl;

}
