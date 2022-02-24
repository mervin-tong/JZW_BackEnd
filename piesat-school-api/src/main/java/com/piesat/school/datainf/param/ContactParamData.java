package com.piesat.school.datainf.param;

import com.smartwork.api.param.UIDParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ContactParamData extends UIDParamData{
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String conName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

}
