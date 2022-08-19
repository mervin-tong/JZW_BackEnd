package com.piesat.school.biz.ds.datainf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_system_email")
public class SystemEmail implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String email;
    private String hotCode;
    private Date updatedAt;
    private Date createdAt;






}
