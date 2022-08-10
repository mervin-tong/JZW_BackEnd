package com.piesat.school.biz.ds.datainf.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_user")
public class AuditApply implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer applyStatus;
    private String workUnit;
    private Long phone;
    private String email;
    private String profession;
    private String unitAddress;
    private String highEducation;
    private String applyExplain;
    @JsonFormat(timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;


}
