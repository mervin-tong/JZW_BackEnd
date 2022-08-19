package com.piesat.school.datainf.vto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "系统邮箱设置")
@Data
public class SystemEmailVTO implements Serializable{

    @ApiModelProperty(value = "唯一标识")
    private Long id;
    @ApiModelProperty(value = "邮箱地址")
    private String email;
    @ApiModelProperty(value = "动态码")
    private String hotCode;
    @JsonFormat(timezone="GMT+8")
    private Date createdAt;
    @ApiModelProperty(value = "更新后的时间")
    @JsonFormat(timezone="GMT+8")
    private Date updatedAt;

}
