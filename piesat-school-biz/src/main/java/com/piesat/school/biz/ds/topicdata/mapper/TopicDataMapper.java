package com.piesat.school.biz.ds.topicdata.mapper;

import com.piesat.school.biz.ds.topicdata.entity.TopicData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piesat.school.topicdata.vto.TopicDataDetailVTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 周悦尧
 * @since 2022-03-09
 */
public interface TopicDataMapper extends BaseMapper<TopicData> {
    List<TopicDataDetailVTO> detailTopic(@Param("topicId") Long topicId);


}
