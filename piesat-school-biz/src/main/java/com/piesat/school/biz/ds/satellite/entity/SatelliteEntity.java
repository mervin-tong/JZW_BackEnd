package com.piesat.school.biz.ds.satellite.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/03/22/17:39
 * @Description:
 */
@Data
@TableName("t_satellite")
public class SatelliteEntity {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 地址
     */
    private String location;
    /**
     * 名称
     */
    private String date;
    /**
     * 尺寸
     */
    private String size;
}
