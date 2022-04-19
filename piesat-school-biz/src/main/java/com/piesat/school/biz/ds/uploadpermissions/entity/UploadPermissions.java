package com.piesat.school.biz.ds.uploadpermissions.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author suweipeng
 * @since 2022-02-28
 */
//上传申请权限表
@Data
@TableName("t_upload_permissions")
public class UploadPermissions implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 申请时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

    /**
     * 状态（0 = 为操作，1 = 通过，2 = 拒绝）
     */
    private Integer status;

    /**
     * 申请者id
     */
    private Long applicatId;
    /**
     * 申请理由
     */
    private String reason;
    /**
     * 审批人
     */
    private Long approver;
    /**
     * 审批意见
     */
    private String auditMark;


}
