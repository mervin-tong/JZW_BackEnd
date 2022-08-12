package com.piesat.school.biz.ds.datainf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.datainf.entity.DataShareinf;
import com.piesat.school.biz.ds.datainf.entity.WebLink;
import com.piesat.school.datainf.param.AuditApplyListParamData;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.param.WebLinkParamData;
import com.piesat.school.datainf.vto.AuditApplyListVTO;
import com.piesat.school.datainf.vto.ShareInfVTO;
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
public interface WebLinkMapper extends BaseMapper<WebLink> {
    List<WebLinkVTO> linkList(@Param("webLink") WebLinkParamData webLinkParamData,Page<WebLinkVTO> page);

//    void addLink(@Param("webLink") WebLinkParamData webLinkParamData);
//
//    void updateLink(@Param("webLink") WebLinkParamData webLinkParamData);
//
//    void deleteLink(@Param("webLink") WebLinkParamData webLinkParamData);


}
