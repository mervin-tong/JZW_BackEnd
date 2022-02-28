package com.piesat.school.biz.ds.uploadpermissions.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("t_upload_permisssions")
public class UploadPermisssions implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 申请者
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 工作地址
     */
    private String workAddress;

    /**
     * 从事专业
     */
    private String major;

    /**
     * 申请时间
     */
    private Date applicationTime;

    /**
     * 状态（0 = 为操作，1 = 通过，2 = 拒绝）
     */
    private Integer status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UploadPermisssions{" +
        "name=" + name +
        ", email=" + email +
        ", workAddress=" + workAddress +
        ", major=" + major +
        ", applicationTime=" + applicationTime +
        ", status=" + status +
        "}";
    }
}
