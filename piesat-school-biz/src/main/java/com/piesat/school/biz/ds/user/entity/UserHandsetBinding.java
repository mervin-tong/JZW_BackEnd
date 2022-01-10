package com.piesat.school.biz.ds.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * @author lawliet
 * @date 2021-06-09
 * <p>
 * 用于存放用户移动端设备绑定的标识
 * 极光RegistrationId等
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@TableName("t_user_handset_binding")
public class UserHandsetBinding implements java.io.Serializable{
    private static final long serialVersionUID=1L;

    @TableId(value = "id",type = IdType.INPUT)//指定自增策略
    private String id;
    //极光推送的设备ID
    private String jpushRegistrationId;

    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;
}
