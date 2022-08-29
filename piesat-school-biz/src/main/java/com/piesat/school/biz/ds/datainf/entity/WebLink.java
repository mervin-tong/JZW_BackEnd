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
@TableName("t_web_link")
public class WebLink implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String webName;

    private String webUrl;



}
