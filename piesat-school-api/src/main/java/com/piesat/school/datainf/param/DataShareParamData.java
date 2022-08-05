package com.piesat.school.datainf.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.piesat.school.base.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DataShareParamData extends PageQueryParamData {
    @ApiModelProperty(value = "唯一标识")
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
    @ApiModelProperty(value = "接口钥匙")
    private Long apiKey;
    @ApiModelProperty(value = "最新更新的时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date updatedAt;
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date createdAt;


}
