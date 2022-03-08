package com.piesat.school.biz.ds.datareview.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author suweipeng
 * @since 2022-03-08
 */
@Data
@TableName("t_data_review")
public class DataReview implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_ID)
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


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public Long getAdminJudgeId() {
        return adminJudgeId;
    }

    public void setAdminJudgeId(Long adminJudgeId) {
        this.adminJudgeId = adminJudgeId;
    }

    public Long getUserJudgeId() {
        return userJudgeId;
    }

    public void setUserJudgeId(Long userJudgeId) {
        this.userJudgeId = userJudgeId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "DataReview{" +
        "status=" + status +
        ", dataId=" + dataId +
        ", adminJudgeId=" + adminJudgeId +
        ", userJudgeId=" + userJudgeId +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        "}";
    }
}
