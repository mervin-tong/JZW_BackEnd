package com.piesat.school.information.vto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class InformationVTO implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "资讯id")
    private Long id;
    /**
     * 咨询类别
     */
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
     * 发布者id
     */
    @ApiModelProperty(value = "资讯发布者id")
    private Long publisher;
    @ApiModelProperty(value = "资讯发布者名称")
    private String publisherName;
    @ApiModelProperty(value = "资讯创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createdAt;
    @ApiModelProperty(value = "资讯更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updatedAt;
}
