package com.piesat.school.biz.ds.datainf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 联系人表
 * </p>
 *
 * @author 周悦尧
 * @since 2022-01-17
 */
@Data
@TableName("t_contact")
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    private String conName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 单位
     */
    private String conUnit;

    /**
     * 地址
     */
    private String conAddress;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;


}
