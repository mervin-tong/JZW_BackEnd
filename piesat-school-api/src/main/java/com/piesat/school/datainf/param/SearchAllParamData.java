package com.piesat.school.datainf.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smartwork.api.param.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
public class SearchAllParamData extends PageQueryParamData {
    @ApiModelProperty(value = "第一分类")
    private String firstClass;
    @ApiModelProperty(value = "第二分类")
    private String secClass;
    @ApiModelProperty(value = "关键字")
    private String keyword;
    @ApiModelProperty(value = "数据所属开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date startAt;

    @ApiModelProperty(value = "数据所属结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date endAt;


}
