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
    @ApiModelProperty(value = "apply_id")
    private Long applyId;
    @ApiModelProperty(value = "check_id")
    private Long checkId;
    @ApiModelProperty(value = "apply_status")
    private Integer applyStatus;
    @ApiModelProperty(value = "apply-content")
    private String applyContent;
    @ApiModelProperty(value = "apply-explain")
    private String applyExplain;
    @ApiModelProperty(value = "api_key")
    private Long apiKey;
    @ApiModelProperty(value = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date updatedAt;
    @ApiModelProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date createdAt;


}
