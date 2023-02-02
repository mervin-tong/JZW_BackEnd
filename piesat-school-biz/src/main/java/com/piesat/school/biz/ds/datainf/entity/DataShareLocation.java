package com.piesat.school.biz.ds.datainf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/02/02/16:40
 * @Description:
 */
@Data
@TableName("t_data_share_location")
public class DataShareLocation {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String apiKey;

    private String fileName;
}
