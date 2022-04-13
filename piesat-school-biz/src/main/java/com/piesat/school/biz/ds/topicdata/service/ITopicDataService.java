package com.piesat.school.biz.ds.topicdata.service;

import com.piesat.school.biz.ds.topicdata.entity.TopicData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.topicdata.param.TopicDataSaveParamData;
import com.piesat.school.topicdata.vto.TopicDataDetailVTO;
import com.piesat.school.topicdata.vto.TopicDataVTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 周悦尧
 * @since 2022-03-09
 */
public interface ITopicDataService extends IService<TopicData> {
    TopicDataVTO saveTopic(TopicDataSaveParamData topicDataSaveParamData);
    List<TopicDataDetailVTO> detailTopic(Long topicId);
    Boolean delTopic(Long topicId);
    List<TopicDataVTO> getAllTopic();
    TopicDataVTO indexDetailTopic(Long topicId);




}
