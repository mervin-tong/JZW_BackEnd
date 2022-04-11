package com.piesat.school.biz.ds.information.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author suweipeng
 * @since 2022-04-11
 */
@TableName("t_information")
@Data
public class Information  implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 咨询类别
     */
    private Integer type;

    /**
     * 咨询标题
     */
    private String title;

    /**
     * 咨询内容
     */
    private String content;

    /**
     * 发布者id
     */
    private Long publisher;
    /**
     * 状态 0无效 1有效
     */
    private Integer status;
    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;


    @Override
    public String toString() {
        return "Information{" +
        "type=" + type +
        ", title=" + title +
        ", content=" + content +
        ", publisher=" + publisher +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        "}";
    }
}
