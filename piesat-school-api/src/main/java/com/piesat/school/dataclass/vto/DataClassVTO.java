package com.piesat.school.dataclass.vto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @author 周悦尧
 * @since 2022/1/26 10.39
 *数据分类VTO
 */
@ApiModel(value = "数据分类完整信息模型")
@Data
public class DataClassVTO implements Serializable{
    /**
     * '第一类别'
     */
    @ApiModelProperty("第一类别")
    private String firstClass;

    /**
     * '第二类别'
     */
    @ApiModelProperty("第二类别")
    private String secClass;



}
