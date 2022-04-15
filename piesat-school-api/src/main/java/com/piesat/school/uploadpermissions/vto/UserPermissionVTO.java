package com.piesat.school.uploadpermissions.vto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserPermissionVTO implements java.io.Serializable{
    @ApiModelProperty(value = "用户id")
    private Long id;
    @ApiModelProperty(value = "用户封禁状态 0未封禁 1封禁")
    private Integer status;
    @ApiModelProperty(value = "是否有上传数据权限（0否 1是）")
    private Boolean isDataUpload;
    @ApiModelProperty(value = "当前是否有上传数据权限（0否 1是） ")
    private Boolean currentUpload;
    @ApiModelProperty(value = "申请拒绝信息")
    private String rejectMark;
}
