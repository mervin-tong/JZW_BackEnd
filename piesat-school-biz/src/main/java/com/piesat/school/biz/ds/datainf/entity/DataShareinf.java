package com.piesat.school.biz.ds.datainf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_data_share")
public class DataShareinf implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long applyId;

    private Long checkId;

    private Integer apply_status;

    private String apply_content;

    private String apply_explan;

    private Long apikey;

    private Date updated_at;

    private Date created_at;


}
