package com.piesat.school.datainf.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.piesat.school.base.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
public class SearchByTimeParamData extends PageQueryParamData {
    @ApiModelProperty(value = "数据所属开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date startAt;

    @ApiModelProperty(value = "数据所属结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date endAt;
}
