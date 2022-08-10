package com.piesat.school.biz.ds.datainf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.datainf.entity.DataShareinf;
import com.piesat.school.datainf.param.*;
import com.piesat.school.datainf.vto.AuditApplyListVTO;
import com.piesat.school.datainf.vto.DataInfDetailVTO;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.generationMode.vto.GenerationModeVTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 申请Key表 Mapper 接口
 * </p>
 *
 */
public interface DataShareinfMapper extends BaseMapper<DataShareinf> {

    List<ShareInfVTO> searchAll(@Param("dataShareParamData") DataShareParamData dataShareParamData, Page<ShareInfVTO> page);

    List<ShareInfVTO> checkStatus(@Param("dataShareParamdata") DataShareParamData dataShareParamData,Page<ShareInfVTO> page);

    List<AuditApplyListVTO> auditApplyList(@Param("dataShareParamData") AuditApplyListParamData auditApplyListParamData, Page<AuditApplyListVTO> page);

    List<AuditApplyListVTO> detail(@Param("dataShareParamData") AuditApplyListParamData auditApplyListParamData);

}
