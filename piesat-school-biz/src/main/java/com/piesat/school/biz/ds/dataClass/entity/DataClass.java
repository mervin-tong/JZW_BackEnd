package com.piesat.school.biz.ds.dataClass.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Lapaus
 * @since 2022-04-13
 */
@TableName("t_data_class")
@Data
public class DataClass implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    @TableId
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单级别
     */
    private Integer level;

    /**
     * 父级菜单id
     */
    private Integer parentId;

    /**
     * 菜单路径
     */
    private String path;

    /**
     * 菜单排序号
     */
    private Integer orderNumber;

    /**
     * 下属数据数量
     */
    private Integer dataNum;

    /**
     * 菜单状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createdAt;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updatedAt;

    @Override
    public String toString() {
        return "DataClass{" +
        "name=" + name +
        ", level=" + level +
        ", parentId=" + parentId +
        ", path=" + path +
        ", orderNumber=" + orderNumber +
        ", dataNum=" + dataNum +
        ", status=" + status +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        "}";
    }
}
