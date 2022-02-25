package com.piesat.school.orderfrom.vto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author suweipeng
 * @data 2022/2/25 14:33
 */
public class OrderFromAttentionVTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 数据名称
     */
    private String dataName;
    /**
     * 数据所属开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date startAt;

    /**
     * 数据所属结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date endAt;

    /**
     * 数据地址
     */
    private String address;






}
