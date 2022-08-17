package com.piesat.school.datainf.vto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "发送key到邮箱")
@Data
public class SendKeyVTO implements Serializable{

    @ApiModelProperty(value = "邮箱")
    private String email;




}
