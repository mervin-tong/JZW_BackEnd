package com.piesat.school.biz.ds.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author suweipeng
 * @data 2022/2/23 10:04
 */
@Data
@TableName("t_email")
public class Email implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 邮箱号
     */
    private String email;

    /**
     * 验证码
     */
    private Integer userVerificationCode;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

}
