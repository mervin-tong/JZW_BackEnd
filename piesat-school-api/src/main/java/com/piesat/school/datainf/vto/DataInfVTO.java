package com.piesat.school.datainf.vto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;
/**
 * @author 周悦尧
 * @since 2022/1/27
 *数据分类VTO
 */
@ApiModel(value = "数据信息模型")
@Data
public class DataInfVTO implements Serializable{
    @ApiModelProperty("数据名称")
    private String dataName;
    @ApiModelProperty("图片地址")
    private String pic;
    @ApiModelProperty("创建时间")
    private Date createdAt;
    @ApiModelProperty("下载次数")
    private Integer downCount;
    @ApiModelProperty("公开状态")
    private Integer status;


}
