package com.piesat.school.datainf.param;

import com.piesat.school.base.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MenuDataParam extends PageQueryParamData {
    @ApiModelProperty(value = "数据名")
    private String dataName;

    @ApiModelProperty(value = "第一分类")
    private String firstClass;

    @ApiModelProperty(value = "第二分类")
    private String secClass;

    @ApiModelProperty(value = "生产方式")
    private String comment;

    @ApiModelProperty(value = "左上坐标")
    private String leftUp;

    @ApiModelProperty(value = "右下坐标")
    private String rightDown;

    @ApiModelProperty(value = "数据地址")
    private String address;

    @ApiModelProperty(value = "开始日期")
    private String startDate;

    @ApiModelProperty(value = "结束日期")
    private String endDate;
}
