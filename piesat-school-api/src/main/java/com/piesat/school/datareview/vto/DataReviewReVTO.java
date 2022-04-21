package com.piesat.school.datareview.vto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class DataReviewReVTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    /**
     * 评审状态
     */
    private Integer status;

    /**
     * 申请数据id
     */
    private Long dataId;

    /**
     * 管理员评审id  （没有为0）
     */
    private Long adminJudgeId;

    /**
     * 指定用户评审id（没有为0）
     */
    private Long userJudgeId;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

    /**
     *  未通过原因
     */
    @ApiModelProperty(value = "未通过原因")
    private String noPassReason;
}
