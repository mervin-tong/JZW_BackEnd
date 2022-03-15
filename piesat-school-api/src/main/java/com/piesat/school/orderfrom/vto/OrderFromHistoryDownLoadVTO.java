package com.piesat.school.orderfrom.vto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    /**
     * 数据名称
     */
    private String dataName;

    /**
     * 数据量
     */
    private String dataAmount;

    /**
     * 公开方式
     */
    private Integer status;

    /**
     * 下载次数
     */
    private String downCount;

    /**
     * 下载时间时间
     */
    private Date createdAt;





}
