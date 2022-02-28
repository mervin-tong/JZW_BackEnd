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
     * 数据名称
     */
    private String dataName;
    /**
     * 数据所属开始时间
     */

    /**
     * 数据地址
     */
    private String address;






}
