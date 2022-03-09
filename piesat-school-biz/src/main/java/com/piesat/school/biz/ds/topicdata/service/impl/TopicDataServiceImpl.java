package com.piesat.school.biz.ds.topicdata.service.impl;

import com.piesat.school.biz.ds.topicdata.builder.TopicDataBuilder;
import com.piesat.school.biz.ds.topicdata.entity.TopicData;
import com.piesat.school.biz.ds.topicdata.mapper.TopicDataMapper;
import com.piesat.school.biz.ds.topicdata.service.ITopicDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.emuerlation.BizEnumType;
import com.piesat.school.topicdata.param.TopicDataSaveParamData;
import com.piesat.school.topicdata.vto.TopicDataVTO;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 周悦尧
 * @since 2022-03-09
 */
@Service
public class TopicDataServiceImpl extends ServiceImpl<TopicDataMapper, TopicData> implements ITopicDataService {

    @Override
    public TopicDataVTO saveTopic(TopicDataSaveParamData topicDataSaveParamData) {
        TopicData topicData = new TopicData();
        topicData.setTopicIntroduction(topicDataSaveParamData.getTopicIntroduction());
        topicData.setTopicName(topicDataSaveParamData.getTopicName());
        topicData.setStatus(BizEnumType.CommonStatus.Valid.getKey());
        this.save(topicData);
        return TopicDataBuilder.toTopicDataVto(topicData);
    }
}
