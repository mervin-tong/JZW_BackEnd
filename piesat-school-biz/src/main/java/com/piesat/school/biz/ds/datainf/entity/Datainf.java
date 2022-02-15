package com.piesat.school.biz.ds.datainf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 数据信息表
 * </p>
 *
 * @author 周悦尧
 * @since 2022-01-17
 */
@Data
@TableName("t_data_inf")
public class Datainf implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 数据名
     */
    private String dataName;

    /**
     * 制作者
     */
    private String maker;

    /**
     * 数据所属单位
     */
    private String dataUnit;

    /**
     * 数据地址
     */
    private String address;

    /**
     * 主题词
     */
    private String topic;

    /**
     * 类别编号
     */
    private Long classId;

    /**
     * 数据存放地址
     */
    private String content;

    /**
     * 尺度
     */
    private String meas;

    /**
     * 分辨率
     */
    private String ratio;

    /**
     * 唯一标识符
     */
    private String doi;

    /**
     * 图片地址
     */
    private String pic;

    /**
     * 公开状态：0 公开；1半公开；2不公开
     */
    private Integer status;

    /**
     * 数据摘要
     */
    private String introduction;

    /**
     * 数据来源
     */
    private String origin;

    /**
     * 数据制作方法
     */
    private String solution;

    /**
     * 联系人编号
     */
    private Long conId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

    /**
     * 点击次数
     */
    private Integer kickCount;

    /**
     * 下载次数
     */
    private Integer dowCount;

}
