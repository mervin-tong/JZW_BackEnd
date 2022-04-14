package com.piesat.school.biz.ds.topic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Lapaus
 * @since 2022-04-14
 */
@TableName("t_topic_data_rel")
public class TopicDataRel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据id
     */
    private Long dataId;

    /**
     * 专题id
     */
    private Long topicId;

    private Date createdAt;

    private Date updatedAt;


    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
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
        return "TopicDataRel{" +
        "dataId=" + dataId +
        ", topicId=" + topicId +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        "}";
    }
}
