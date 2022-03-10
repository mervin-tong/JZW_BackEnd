package com.piesat.school.biz.ds.topicdata.service.impl;

import com.piesat.school.biz.common.helper.BizCommonValidateHelper;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.piesat.school.biz.ds.topicdata.builder.TopicDataBuilder;
import com.piesat.school.biz.ds.topicdata.entity.TopicData;
import com.piesat.school.biz.ds.topicdata.mapper.TopicDataMapper;
import com.piesat.school.biz.ds.topicdata.service.ITopicDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.emuerlation.BizEnumType;
import com.piesat.school.topicdata.param.TopicDataSaveParamData;
import com.piesat.school.topicdata.vto.TopicDataDetailVTO;
import com.piesat.school.topicdata.vto.TopicDataVTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private TopicDataMapper topicDataMapper;

    //新增主题
    @Override
    public TopicDataVTO saveTopic(TopicDataSaveParamData topicDataSaveParamData) {
        TopicData topicData = new TopicData();
        topicData.setTopicIntroduction(topicDataSaveParamData.getTopicIntroduction());
        topicData.setTopicName(topicDataSaveParamData.getTopicName());
        topicData.setStatus(BizEnumType.CommonStatus.Valid.getKey());
        this.save(topicData);
        return TopicDataBuilder.toTopicDataVto(topicData);
    }

    //主题细节
    @Override
    public List<TopicDataDetailVTO> detailTopic(Long topicId) {
        return topicDataMapper.detailTopic(topicId);
    }

    //删除主题
    @Override
    public Boolean delTopic(Long topicId) {
        TopicData topicData = BizCommonValidateHelper.valdiateGetById(topicId,this);
        topicData.setStatus(BizEnumType.CommonStatus.Invalid.getKey());
        return this.updateById(topicData);
    }
}
