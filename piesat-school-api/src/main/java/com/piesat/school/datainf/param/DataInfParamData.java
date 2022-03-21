package com.piesat.school.datainf.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smartwork.api.param.UIDParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DataInfParamData implements Serializable {
    /**
     * 数据名
     */
    @ApiModelProperty(value = "数据名")
    private String dataName;

    @ApiModelProperty(value = "数据所属开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date startAt;

    @ApiModelProperty(value = "数据地址")
    private String address;

    /**
     * 数据所属单位
     */
    @ApiModelProperty(value = "数据所属单位")
    private String dataUnit;
    /**
     * 数据所属结束时间
     */
    @ApiModelProperty(value = "数据所属结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date endAt;

    @ApiModelProperty(value = "联系人")
    private ContactParamData contactParamData;
}
