package com.piesat.school.uploadpermissions.vto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author suweipeng
 * @data 2022/2/28 16:17
 */
@Data
public class UploadPermissionsVTO implements Serializable {
    @ApiModelProperty(value = "申请id")
    private Long id ;
    @ApiModelProperty(value = "申请人id")
    private Long userId;
    //申请人
    @ApiModelProperty(value = "申请人名称")
    private String name;
    //邮箱
    @ApiModelProperty(value = "申请人邮箱")
    private String email;
    //工作地址
    @ApiModelProperty(value = "申请人工作单位")
    private String unitAddress;
    //从事专业
    @ApiModelProperty(value = "申请人从事专业")
    private String profession;
    @ApiModelProperty(value = "申请理由")
    private String reason;
    //申请时间
    @ApiModelProperty(value = "申请时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createdAt;
    @ApiModelProperty(value = "申请状态")
    private Integer applyStatus;
    @ApiModelProperty(value = "审核者id")
    private Long approver;
    @ApiModelProperty(value = "审核意见")
    private String auditMark;

}
