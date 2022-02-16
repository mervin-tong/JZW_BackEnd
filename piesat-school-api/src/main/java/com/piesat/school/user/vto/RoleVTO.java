package com.piesat.school.user.vto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author suweipeng
 * @data 2022/2/11 14:36
 */
@Data
public class RoleVTO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 角色编号
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色关键字，用于权限控制
     */
    private String keyword;

    /**
     * 描述
     */
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
