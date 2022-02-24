package com.piesat.school.biz.ds.datainf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 周悦尧
 * @since 2022-02-22
 */
@Data
@TableName("t_key")
public class Key implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 关键字
     */
    private String keyword;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;


}
