package com.piesat.school.biz.ds.generationmode.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 韦晔
 * @since 2022-04-10
 */
@Data
@TableName("t_generation")
public class GenerationMode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value = "序号")
    private Long id;
//
//    @ApiModelProperty(value = "生成方式")
//    private Integer generationMode;

    @ApiModelProperty(value = "标注")
    private String comment;
}
