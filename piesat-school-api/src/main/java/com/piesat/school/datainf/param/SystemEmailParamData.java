package com.piesat.school.datainf.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.piesat.school.base.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SystemEmailParamData implements Serializable {
    @ApiModelProperty(value = "唯一标识")
    private Long id;
    @ApiModelProperty(value = "邮箱地址")
    private String email;
    @ApiModelProperty(value = "动态码")
    private String code;
    @JsonFormat(timezone="GMT+8")
    private Date createdAt;
    @ApiModelProperty(value = "更新后的时间")
    @JsonFormat(timezone="GMT+8")
    private Date updatedAt;


}
