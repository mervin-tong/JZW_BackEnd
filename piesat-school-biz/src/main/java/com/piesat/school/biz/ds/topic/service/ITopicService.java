package com.piesat.school.biz.ds.topic.service;

import com.piesat.school.biz.ds.topic.entity.Topic;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.topic.param.TopicDataAddParamData;
import com.piesat.school.topic.param.TopicDelParamData;
import com.piesat.school.topic.param.TopicQueryParamData;
import com.piesat.school.topic.param.TopicSaveParamData;
import com.piesat.school.topic.vto.TopicDetailVTO;
import com.piesat.school.topic.vto.TopicVTO;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 周悦尧
 * @since 2022-03-09
 */
public interface ITopicService extends IService<Topic> {
    TopicVTO saveOrUpdate(TopicSaveParamData topicSaveParamData);
    List<TopicDetailVTO> detailTopic(Long topicId);
    List<TopicVTO> getAllTopic();
    TopicVTO indexDetailTopic(Long topicId);


    Boolean del(TopicDelParamData paramData);

    Boolean addTopicData(TopicDataAddParamData paramData);

    Boolean delTopicData(Long id);

    TailPage<TopicVTO> topicPage(TopicQueryParamData paramData);
}
