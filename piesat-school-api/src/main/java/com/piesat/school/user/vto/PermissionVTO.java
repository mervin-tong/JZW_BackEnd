package com.piesat.school.user.vto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author suweipeng
 * @data 2022/2/11 15:15
 */
@Data
public class PermissionVTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限关键字，用于权限控制
     */
    private String keyword;
    /**
     * 描述
     */
    private String description;

}
