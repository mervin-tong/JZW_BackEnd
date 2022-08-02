package com.piesat.school.datainf.vto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ShareInfVTO implements Serializable {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "apply-id")
    private Long applyId;
    @ApiModelProperty(value = "check-id")
    private Long checkId;
    @ApiModelProperty(value = "apply-status")
    private Integer apply_status;
    @ApiModelProperty(value = "apply-content")
    private String apply_content;
    @ApiModelProperty(value = "apply-explan")
    private String apply_explan;
    @ApiModelProperty(value = "apikey")
    private Long apikey;
    @ApiModelProperty(value = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date updated_at;
    @ApiModelProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date created_at;


}
