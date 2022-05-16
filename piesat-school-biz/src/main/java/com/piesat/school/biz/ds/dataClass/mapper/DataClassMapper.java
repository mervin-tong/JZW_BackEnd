package com.piesat.school.biz.ds.dataClass.mapper;

import com.piesat.school.biz.ds.dataClass.entity.DataClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lapaus
 * @since 2022-04-13
 */
public interface DataClassMapper extends BaseMapper<DataClass> {

    Boolean updateDataClassification(@Param("list") List<DataClass> dataClasses);
}
