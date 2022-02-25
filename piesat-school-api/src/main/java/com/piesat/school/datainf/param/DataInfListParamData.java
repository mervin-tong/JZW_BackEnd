package com.piesat.school.datainf.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smartwork.api.param.PageQueryParamData;
import com.smartwork.api.param.UIDParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DataInfListParamData extends PageQueryParamData {
    @ApiModelProperty(value = "数据名")
    private String dataName;

    @ApiModelProperty(value = "数据所属开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date startAt;

    @ApiModelProperty(value = "数据所属结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date endAt;

    @ApiModelProperty(value = "下载次数")
    private Integer downCount;

    @ApiModelProperty(value = "图片")
    private String pic;

    @ApiModelProperty(value = "关键字")
    private String keyword;
}
