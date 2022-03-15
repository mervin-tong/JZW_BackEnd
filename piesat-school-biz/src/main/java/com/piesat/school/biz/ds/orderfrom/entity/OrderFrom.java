package com.piesat.school.biz.ds.orderfrom.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 唐子超
 * @since 2022-01-17
 */
@Data
@TableName("t_order_from")
public class OrderFrom implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 数据状态（1.申请下载  2.同意下载 3.拒绝下载）
     */
    private Long dataType;

    /**
     * 下载用户
     */
    private Long downloadUserId;

    /**
     * 审核人员
     */
    private Long auditorUserId;

    /**
     * 对应数据编号
     */
    private Long dataInfoId;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

    /**
     * 订单说明
     */
    private String explain;

}
