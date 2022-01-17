package com.piesat.school.biz.ds.method.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 数据生成方法
 * </p>
 *
 * @author 周悦尧
 * @since 2022-01-17
 */
@Data
@TableName("t_method")
public class Method implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.INPUT)
    private Long id;
    /**
     * 方法名称
     */
    private String metName;

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
