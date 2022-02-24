package com.piesat.school.orderfrom.vto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author suweipeng
 * @data 2022/2/24 10:28
 */
@Data
public class OrderFromVTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 数据审核状态（1.申请下载  2.同意下载 3.拒绝下载）
     */
    private Long dataType;

    /**
     * 下载用户id
     */
    private Long downloadUserId;

    /**
     * 对应数据名称
     */
    private String dataName;

    /**
     * 图片地址
     */
    private String pic;

    /**
     * 公开状态（1.完全公开 2.申请获取 3.保护期内）
     */
    private Integer status;
}
