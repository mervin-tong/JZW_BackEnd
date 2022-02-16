package com.piesat.school.biz.ds.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author suweipeng
 * @data 2022/2/13 23:32
 */
@Data
@TableName("t_permission")
public class Permission {
    private static final long serialVersionUID = 1L;

    /**
     * 权限编号
     */
    private Integer id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 角色关键字，用于权限控制
     */
    private String keyword;

    //描述
    private String description;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

}
