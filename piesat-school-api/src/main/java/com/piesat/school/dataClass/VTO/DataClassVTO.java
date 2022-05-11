package com.piesat.school.dataClass.VTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class DataClassVTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String name;

    /**
     * 菜单级别
     */
    @ApiModelProperty(value = "菜单级别")
    private Integer level;

    /**
     * 父级菜单id
     */
    @ApiModelProperty(value = "父级菜单id")
    private Integer parentId;

    /**
     * 菜单路径
     */
    @ApiModelProperty(value = "菜单路径")
    private String path;

    /**
     * 菜单排序号
     */
    @ApiModelProperty(value = "菜单排序号")
    private Integer orderNumber;

    /**
     * 下属数据数量
     */
    @ApiModelProperty(value = "下属数据数量")
    private Integer dataNum;

    /**
     * 菜单状态
     */
    @ApiModelProperty(value = "菜单状态 0未删除  1删除")
    private Integer status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    @ApiModelProperty(value = "子列表")
    private List<DataClassVTO> chuildrenList;
}
