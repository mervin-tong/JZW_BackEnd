package com.piesat.school.biz.ds.orderFrom.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("t_order_from")
public class OrderFrom implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 下载的数据id
     */
    private Integer dataId;

    /**
     * 数据状态（1.申请下载  2.同意下载 3.拒绝下载）
     */
    private Integer dataType;

    /**
     * 下载用户
     */
    private Integer downloadUserId;

    /**
     * 审核人员
     */
    private Integer auditorId;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;


    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getDownloadUserId() {
        return downloadUserId;
    }

    public void setDownloadUserId(Integer downloadUserId) {
        this.downloadUserId = downloadUserId;
    }

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
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

    @Override
    public String toString() {
        return "OrderFrom{" +
        "dataId=" + dataId +
        ", dataType=" + dataType +
        ", downloadUserId=" + downloadUserId +
        ", auditorId=" + auditorId +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        "}";
    }
}
