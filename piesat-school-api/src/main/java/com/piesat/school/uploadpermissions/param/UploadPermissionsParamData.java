package com.piesat.school.uploadpermissions.param;

import com.piesat.school.base.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @author suweipeng
 * @data 2022/2/28 16:32
 */
@Data
public class UploadPermissionsParamData extends PageQueryParamData {
    @ApiModelProperty(value = "上传权限状态")
    private Integer uploadPermissionsStatus;
    @ApiModelProperty(value = "搜索条件:姓名")
    private String condition;

    @ApiModelProperty(value = "申请开始时间")
    private String startAt;

    @ApiModelProperty(value = "申请结束时间")
    private String endAt;
}
