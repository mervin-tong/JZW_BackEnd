package com.piesat.school.order.vto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author suweipeng
 * @data 2022/3/8 13:42
 */
@Data
public class OrderFromHistoryDownLoadVTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "历史记录id")
    private Long id;
    /**
     * 数据名称
     */
    @ApiModelProperty(value ="数据名称" )
    private String dataName;

    /**
     * 数据量
     */
    @ApiModelProperty(value = "数据大小")
    private String dataAmount;

    /**
     * 公开方式
     */
    @ApiModelProperty(value = "公开状态1.完全公开 2.申请获取 3.保护期内")
    private Integer status;

    /**
     * 下载次数
     */
    @ApiModelProperty(value = "下载次数")
    private Integer downCount;

    /**
     * 下载时间时间
     */
    @ApiModelProperty(value ="下载时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createdAt;
    @ApiModelProperty(value = "是否可下载")
    private Boolean isDownloadAble;

}
