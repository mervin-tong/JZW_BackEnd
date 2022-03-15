package com.piesat.school.orderfrom.vto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author suweipeng
 * @data 2022/2/25 14:33
 */
@Data
public class OrderFromAttentionVTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据id
     */
    private Long id;

    /**
     * 数据名称
     */
    private String dataName;

    /**
     * 数据时间
     */
    private Date created_at;

    /**
     * 公开状态
     */
    private Integer status;

    /**
     * 空间地址
     */
    private String address;

    /**
     * 上传者名称
     */
    private String name;






}
