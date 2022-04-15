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
 * @author 周悦尧
 * @since 2022-03-09
 */
@TableName("t_topic")
@Data
public class Topic implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 专题名
     */
    private String name;

    /**
     * 图片地址
     */
    private String picture;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

    /**
     * 逻辑删除
     */
    private Integer status;

    /**
     * 专题介绍
     */
    private String description;
    /**
     * 数据数量
     */
    private Integer dataNum;

//    /**
//     * 专题数据列表
//     */
//    @TableField(exist = false)
//    private List<Datainf> results;



}
