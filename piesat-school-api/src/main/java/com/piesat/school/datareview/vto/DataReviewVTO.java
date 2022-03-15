package com.piesat.school.datareview.vto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author suweipeng
 * @data 2022/3/8 15:36
 */
@Data
public class DataReviewVTO implements Serializable {
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

    /**
     * 联系人
     */
    private String conName;

    /**
     * 数据单位
     */
    private String dataUnit;

    /**
     * 数据创建时间
     */
    private String createdAt;

    /**
     * 评审状态
     */
    private String status;



}
