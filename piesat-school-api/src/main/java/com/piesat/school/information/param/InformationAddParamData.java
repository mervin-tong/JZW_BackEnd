package com.piesat.school.information.param;

import com.smartwork.api.param.ParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class InformationAddParamData extends ParamData {
    @ApiModelProperty(value = "修改的资讯id,不传为新增")
    private Long id;
    @ApiModelProperty(value = "资讯类型")
    private Integer type;

    /**
     * 咨询标题
     */
    @ApiModelProperty(value = "资讯标题")
    private String title;

    /**
     * 咨询内容
     */
    @ApiModelProperty(value = "资讯内容")
    private String content;

    /**
     * 咨询简介
     */
    @ApiModelProperty(value = "咨询简介")
    private String synopsis;

    /**
     * 发布者id
     */
    @ApiModelProperty(value = "资讯发布者id")
    private Long publisher;
}
