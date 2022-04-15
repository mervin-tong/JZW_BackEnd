package com.piesat.school.topic.iservice;


import com.piesat.school.datainf.vto.MyDataInfVTO;
import com.piesat.school.topic.param.*;
import com.piesat.school.topic.vto.TopicDetailVTO;
import com.piesat.school.topic.vto.TopicVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

/**
 * @author 周悦尧
 * @since 2022/3/9
 */

public interface IRTopicService {
    Result<TopicVTO> saveOrUpdate(TopicSaveParamData paramData);
    Result<Boolean> addTopicData(TopicDataAddParamData paramData);
    Result<List<TopicDetailVTO>> detailTopic(Long topicId);
    Result<TopicVTO> indexDetailTopic(Long topicId);

    Result<Boolean> del(TopicDelParamData paramData);

    Result<Boolean> delTopicData(TopicDataDelParamData paramData);

    Result<TailPage<TopicVTO>> list(TopicQueryParamData paramData);

    Result<TailPage<MyDataInfVTO>> topicDatalist(TopicQueryParamData paramData);
}
