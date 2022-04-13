package com.piesat.school.biz.ds.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
 * @since 2022-01-16
 */
@Data
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 姓名（实名）
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;
    @ApiModelProperty(value = "用户头像链接")
    private String avatar;
    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 密码：数字+英文字母（大小写敏感，不少于 8 位）
     */
    private String password;

    /**
     * 工作单位
     */
    private String workUnit;

    /**
     * 单位地址
     */
    private String unitAddress;

    /**
     * 最高学历
     */
    private String highEducation;

    /**
     * 从事专业
     */
    private String profession;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

    /**
     * 是否有上传数据权限（0是 1否）
     */
    private Boolean isDataUpload;

    /**
     * 账户状态（0正常 1停用）
     */
    private Integer status;


    @Override
    public String toString() {
        return "User{" +
        "name=" + name +
        ", phone=" + phone +
        ", email=" + email +
        ", password=" + password +
        ", workUnit=" + workUnit +
        ", unitAddress=" + unitAddress +
        ", highEducation=" + highEducation +
        ", profession=" + profession +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        ", isDataUpload=" + isDataUpload +
        ", status=" + status +
        "}";
    }
}
