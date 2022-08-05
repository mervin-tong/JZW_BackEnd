package com.piesat.school.biz.ds.datainf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.datainf.entity.DataShareinf;
import com.piesat.school.datainf.param.*;
import com.piesat.school.datainf.vto.DataInfDetailVTO;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.generationMode.vto.GenerationModeVTO;
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
public interface DataShareinfMapper extends BaseMapper<DataShareinf> {

    List<ShareInfVTO> searchAll(@Param("dataShareParamData") DataShareParamData dataShareParamData, Page<ShareInfVTO> page);
    List<ShareInfVTO> applyForKey(@Param("dataShareParamdata") DataShareParamData dataShareParamData,Page<ShareInfVTO> page);
    List<ShareInfVTO> checkStatus(@Param("dataShareParamdata") DataShareParamData dataShareParamData,Page<ShareInfVTO> page);


}
