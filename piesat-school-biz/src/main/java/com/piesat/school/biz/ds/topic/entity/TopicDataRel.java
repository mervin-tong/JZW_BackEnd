package com.piesat.school.biz.ds.topic.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

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
@Data
public class TopicDataRel implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 数据id
     */
    private Long dataId;

    /**
     * 专题id
     */
    private Long topicId;
    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

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
