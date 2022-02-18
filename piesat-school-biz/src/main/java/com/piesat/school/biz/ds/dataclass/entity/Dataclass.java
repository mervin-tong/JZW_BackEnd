package com.piesat.school.biz.ds.dataclass.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 数据分类表
 * </p>
 *
 * @author 周悦尧
 * @since 2022-01-17
 */
@Data
@TableName("t_data_class")
public class Dataclass implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.INPUT)
    private Long id;
    /**
     * '第一类别'
     */
    private String firstClass;

    /**
     * '第二类别'
     */
    private String secClass;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    /**
     * '逻辑删除'
     */
    private Integer status;



    @Override
    public String toString() {
        return "Dataclass{" +
        "firstClass=" + firstClass +
        ", secClass=" + secClass +
        ", updatedAt=" + updatedAt +
        ", createdAt=" + createdAt +
        "}";
    }
}
