package com.piesat.school.biz.ds.uploadpermissions.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("t_upload_permisssions")
public class UploadPermisssions implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 申请时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
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
     * 审批人
     */
    private Long approver;


}
