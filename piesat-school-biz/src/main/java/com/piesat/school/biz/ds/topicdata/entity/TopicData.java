package com.piesat.school.biz.ds.topicdata.entity;

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
 * @author 周悦尧
 * @since 2022-03-09
 */
@TableName("t_topic_data")
@Data
public class TopicData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 专题名
     */
    private String topicName;

    /**
     * 图片地址
     */
    private String picture;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 逻辑删除
     */
    private Integer status;

    /**
     * 专题介绍
     */
    private String topicIntroduction;



}
