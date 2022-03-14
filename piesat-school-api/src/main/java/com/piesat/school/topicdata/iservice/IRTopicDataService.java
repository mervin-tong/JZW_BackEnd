package com.piesat.school.topicdata.iservice;


import com.piesat.school.topicdata.param.TopicDataSaveParamData;
import com.piesat.school.topicdata.vto.TopicDataDetailVTO;
import com.piesat.school.topicdata.vto.TopicDataVTO;
import com.smartwork.api.Result;

import java.util.List;

/**
 * @author 周悦尧
 * @since 2022/3/9
 */

public interface IRTopicDataService {
    Result<TopicDataVTO> saveTopic(TopicDataSaveParamData topicDataSaveParamData);
    Result<Boolean> addTopicData(Long topicId,Long dataId);
    Result<Boolean> delTopicData(Long topicId,Long dataId);
    Result<List<TopicDataDetailVTO>> detailTopic(Long topicId);
    Result<List<TopicDataVTO>> getAllTopic();
    Result<TopicDataVTO> indexDetailTopic(Long topicId);
}
