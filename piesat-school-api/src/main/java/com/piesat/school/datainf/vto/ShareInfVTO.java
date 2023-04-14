package com.piesat.school.datainf.vto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

@Data
public class ShareInfVTO implements Serializable {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "申请者id")
    private Long applyId;
    @ApiModelProperty(value = "审核者id")
    private Long checkId;
    @ApiModelProperty(value = "申请状态")
    private Integer applyStatus;
    @ApiModelProperty(value = "申请内容")
    private String applyContent;
    @ApiModelProperty(value = "申请说明")
    private String applyExplain;
    @ApiModelProperty(value = "不通过理由")
    private String mark;
    @ApiModelProperty(value = "api_key")
    private String apiKey;
    @ApiModelProperty(value = "url")
    private String url;
    @ApiModelProperty(value = "邮箱地址")
    private String email;
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(timezone="GMT+8")
    private Date updatedAt;
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone="GMT+8")
    private Date createdAt;
    @ApiModelProperty(value = "签入者")
    private String checkMan;
    @ApiModelProperty(value = "签入者Id")
    private Long checkManId;


}
