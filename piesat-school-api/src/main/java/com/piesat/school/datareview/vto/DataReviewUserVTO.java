package com.piesat.school.datareview.vto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author suweipeng
 * @data 2022/3/14 9:41
 */
@Data
public class DataReviewUserVTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 评审id
     */
    private Long id;

    /**
     * 数据名称
     */
    private String dataName;

    /**
     * 数据地址
     */
    private String address;

    /**
     * 公开方式
     */
    private String status;

    /**
     * 数据采集者
     */
    private String maker;

    /**
     * 提交时间
     */
    private String createdAt;


}
