package com.piesat.school.biz.ds.aspect.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("t_record")
@Data
public class AspectEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 菜单名称
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 请求参数
     */
    private String param;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 请求时间
     */
    private Date time;
}
