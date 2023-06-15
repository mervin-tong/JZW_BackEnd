package com.piesat.school.biz.ds.datainf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * @Author: liqiteng
 * @Date: 2022/8/23
 * @Description:
 */
@Data
@TableName("t_data_share")
public class DataShareinf implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long applyId;

    private Long checkId;

    private Date passDate;
    /**
     * 申请状态 0未通过，1已通过，2待审核
     */
    private Integer applyStatus;

    private Long checkManId;

    private String applyContent;

    private String applyExplain;

    private String apiKey;

    private String mark;

    private String url;

    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;


}
