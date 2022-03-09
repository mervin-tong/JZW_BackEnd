package com.piesat.school.biz.ds.topicdata.service;

import com.piesat.school.biz.ds.topicdata.entity.TopicData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.topicdata.param.TopicDataSaveParamData;
import com.piesat.school.topicdata.vto.TopicDataVTO;

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


}
