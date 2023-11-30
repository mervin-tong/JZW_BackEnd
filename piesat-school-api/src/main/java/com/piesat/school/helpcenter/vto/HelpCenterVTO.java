package com.piesat.school.helpcenter.vto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class HelpCenterVTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "文档名称")
    private String name;

    @ApiModelProperty(value = "文档内容")
    private String text;

    @ApiModelProperty(value = "上传人")
    private String uploadUser;

    @ApiModelProperty(value = "上传时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private String uploadTime;

    @ApiModelProperty(value = "是否删除  0否 1是")
    private Integer isDelete;
}
