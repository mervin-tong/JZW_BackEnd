package com.piesat.school.biz.ds.topic.mapper;

import com.piesat.school.biz.ds.topic.entity.Topic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piesat.school.topic.vto.TopicDetailVTO;
import com.piesat.school.topic.vto.TopicVTO;
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
public interface TopicMapper extends BaseMapper<Topic> {
    List<TopicDetailVTO> detailTopic(@Param("topicId") Long topicId);
    List<TopicVTO> getAllTopic();
    TopicVTO indexDetailTopic(@Param("topicId") Long topicId);


}
