package com.piesat.school.datareview.param;

import com.piesat.school.base.PageQueryParamData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author suweipeng
 * @data 2022/3/8 15:37
 */
@Data
public class DataReviewParamData extends PageQueryParamData {

    /**
     * 数据名称
     */
    @ApiModelProperty(value = "数据名称")
    private String dataName;

    /**
     * 起始时间
     */
    @ApiModelProperty(value = "起始时间")
    private String start;

    /**
     * 终止时间
     */
    @ApiModelProperty(value = "终止时间")
    private String end;

    /**
     * 评审状态
     */
    @ApiModelProperty(value = "评审状态")
    private String status;
}
