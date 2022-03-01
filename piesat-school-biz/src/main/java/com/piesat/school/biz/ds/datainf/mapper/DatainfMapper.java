package com.piesat.school.biz.ds.datainf.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piesat.school.datainf.param.SearchByKeyParamData;
import com.piesat.school.datainf.vto.DataInfListVTO;
import org.apache.ibatis.annotations.Param;

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
    List<DataInfListVTO> searchByKeyword(@Param("searchByKeyParamData")SearchByKeyParamData searchByKeyParamData,
                                         Page<DataInfListVTO> page);

}
