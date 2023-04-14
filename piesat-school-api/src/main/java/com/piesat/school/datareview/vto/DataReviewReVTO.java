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
     * 签入者
     */
    private String checkMan;

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
     *  初审未通过原因
     */
    @ApiModelProperty(value = "初审未通过原因")
    private String noPassReason;

    /**
     *  复审未通过原因
     */
    @ApiModelProperty(value = "复审未通过原因")
    private String recheckPassReason;

    /**
     * 复审时间
     */
    private Date recheckedAt;

    /**
     * 初审时间
     */
    private Date checkedAt;
}
