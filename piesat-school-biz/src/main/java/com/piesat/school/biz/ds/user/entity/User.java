package com.piesat.school.biz.ds.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
     * 姓名（实名）
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

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
    private String isDataUpload;

    /**
     * 账户状态（0正常 1停用）
     */
    private String status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public String getHighEducation() {
        return highEducation;
    }

    public void setHighEducation(String highEducation) {
        this.highEducation = highEducation;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getIsDataUpload() {
        return isDataUpload;
    }

    public void setIsDataUpload(String isDataUpload) {
        this.isDataUpload = isDataUpload;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
