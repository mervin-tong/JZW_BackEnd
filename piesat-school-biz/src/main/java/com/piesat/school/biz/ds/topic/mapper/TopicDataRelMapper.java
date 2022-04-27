package com.piesat.school.biz.ds.topic.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.topic.entity.TopicDataRel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datainf.vto.MyDataInfVTO;
import com.piesat.school.topic.param.TopicQueryParamData;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lapaus
 * @since 2022-04-14
 */
public interface TopicDataRelMapper extends BaseMapper<TopicDataRel> {

    List<MyDataInfVTO> getTopicDatalist(TopicQueryParamData paramData, Page<MyDataInfVTO> page);
}
