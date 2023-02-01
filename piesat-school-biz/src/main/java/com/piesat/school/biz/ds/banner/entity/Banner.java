package com.piesat.school.biz.ds.banner.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/01/04/17:48
 * @Description:
 */
@Data
@TableName("t_banner")
public class Banner implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 图片名称
     */
    private String name;
    /**
     * 图片url
     */
    private String url;
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
}
