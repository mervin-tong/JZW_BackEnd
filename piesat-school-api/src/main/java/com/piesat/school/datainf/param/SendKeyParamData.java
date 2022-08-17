package com.piesat.school.datainf.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.piesat.school.base.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SendKeyParamData{
    @ApiModelProperty(value = "唯一标识")
    private Long id;
    @ApiModelProperty(value = "邮箱地址")
    private String email;


}
