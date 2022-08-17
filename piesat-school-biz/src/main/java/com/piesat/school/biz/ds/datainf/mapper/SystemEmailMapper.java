package com.piesat.school.biz.ds.datainf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.datainf.entity.SystemEmail;
import com.piesat.school.biz.ds.datainf.entity.WebLink;
import com.piesat.school.datainf.param.WebLinkParamData;
import com.piesat.school.datainf.vto.SystemEmailVTO;
import com.piesat.school.datainf.vto.WebLinkVTO;
import com.smartwork.api.support.page.TailPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 申请Key表 Mapper 接口
 * </p>
 *
 */
public interface SystemEmailMapper extends BaseMapper<SystemEmail> {

}
