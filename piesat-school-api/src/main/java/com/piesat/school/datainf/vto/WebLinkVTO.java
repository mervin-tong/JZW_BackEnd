package com.piesat.school.datainf.vto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "web链接模型")
@Data
public class WebLinkVTO implements Serializable{
    @ApiModelProperty(value = "链接的id")
    private Long id;
    @ApiModelProperty(value = "网站名称")
    private String webName;
    @ApiModelProperty(value = "网站链接")
    private String webUrl;




}
