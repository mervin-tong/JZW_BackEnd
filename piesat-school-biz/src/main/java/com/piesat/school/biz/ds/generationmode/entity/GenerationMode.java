package com.piesat.school.biz.ds.generationmode.entity;

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
@TableName("t_generation_mode")
public class GenerationMode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "序号")
    private Integer id;

    @ApiModelProperty(value = "生成方式")
    private Integer generationMod;

    @ApiModelProperty(value = "标注")
    private String comment;
}
