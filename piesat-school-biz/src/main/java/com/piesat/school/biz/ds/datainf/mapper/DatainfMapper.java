package com.piesat.school.biz.ds.datainf.mapper;

import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 数据信息表 Mapper 接口
 * </p>
 *
 * @author 周悦尧
 * @since 2022-01-17
 */
public interface DatainfMapper extends BaseMapper<Datainf> {
    List<Datainf> getAllDatainf();

}
