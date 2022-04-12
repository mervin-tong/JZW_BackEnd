package com.piesat.school.datainf.param;

import com.smartwork.api.param.ParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UploadFileParamData extends ParamData {
    @ApiModelProperty(value = "上传文件路径")
    private String uploadPath;
    @ApiModelProperty(value = "文件大小")
    private String amount;
}
