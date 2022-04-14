package com.piesat.school.biz.ds.topic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.information.builder.InformationBuilder;
import com.piesat.school.biz.ds.topic.builder.TopicBuilder;
import com.piesat.school.biz.ds.topic.entity.Topic;
import com.piesat.school.biz.ds.topic.entity.TopicDataRel;
import com.piesat.school.biz.ds.topic.mapper.TopicDataMapper;
import com.piesat.school.biz.ds.topic.service.ITopicDataRelService;
import com.piesat.school.biz.ds.topic.service.ITopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.emuerlation.BizEnumType;
import com.piesat.school.topic.param.TopicDataAddParamData;
import com.piesat.school.topic.param.TopicDelParamData;
import com.piesat.school.topic.param.TopicQueryParamData;
import com.piesat.school.topic.param.TopicSaveParamData;
import com.piesat.school.topic.vto.TopicDetailVTO;
import com.piesat.school.topic.vto.TopicVTO;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class TopicServiceImpl extends ServiceImpl<TopicDataMapper, Topic> implements ITopicService {
    @Resource
    private TopicDataMapper topicDataMapper;
    @Resource
    private ITopicDataRelService topicDataRelService;
    //新增主题
    @Override
    public TopicVTO saveOrUpdate(TopicSaveParamData topicSaveParamData) {
        Topic topic = new Topic();
        if(topicSaveParamData.getId()!=null){
            topic =this.getById(topicSaveParamData.getId());
        }
        if(StringUtils.isNotBlank(topicSaveParamData.getDescription())){
            topic.setDescription(topicSaveParamData.getDescription());
        }
        if(StringUtils.isNotBlank(topicSaveParamData.getName())){
            topic.setName(topicSaveParamData.getName());
        }
        if(StringUtils.isNotBlank(topicSaveParamData.getPicture())){
            topic.setPicture(topicSaveParamData.getPicture());
        }
        topic.setStatus(BizEnumType.CommonStatus.Valid.getKey());
        this.save(topic);
        return TopicBuilder.toTopicVto(topic);
    }

    //主题细节
    @Override
    public List<TopicDetailVTO> detailTopic(Long topicId) {
        return topicDataMapper.detailTopic(topicId);
    }

    @Override
    public List<TopicVTO> getAllTopic() {
        return topicDataMapper.getAllTopic();
    }

    @Override
    public TopicVTO indexDetailTopic(Long topicId) {
        return topicDataMapper.indexDetailTopic(topicId);
    }

    @Override
    public Boolean del(TopicDelParamData paramData) {
        if(paramData.getId()!=null){
            //1.专题主体逻辑删除
            Topic topic=this.getById(paramData.getId());
            topic.setStatus(BizEnumType.CommonStatus.Invalid.getKey());
            this.updateById(topic);
            //2.其下关联数据删除
            QueryWrapper<TopicDataRel> queryWrapper=new QueryWrapper<>();
            queryWrapper.lambda().eq(TopicDataRel::getTopicId,paramData.getId());
            this.topicDataRelService.remove(queryWrapper);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean addTopicData(TopicDataAddParamData paramData) {
        Topic topic=this.getById(paramData.getTopicId());
        String[] dataIds=paramData.getDataIds().split(",");
        List<TopicDataRel> topicDataRels=new ArrayList<>();
        for(String i:dataIds){
            TopicDataRel topicDataRel=new TopicDataRel();
            topicDataRel.setDataId(Long.valueOf(i));
            topicDataRel.setTopicId(paramData.getTopicId());
            topicDataRels.add(topicDataRel);
        }
        this.topicDataRelService.saveBatch(topicDataRels);
        topic.setDataNum(topic.getDataNum()+dataIds.length);
        this.updateById(topic);
        return Boolean.TRUE;
    }

    @Override
    public Boolean delTopicData(Long id) {
        TopicDataRel topicDataRel=this.topicDataRelService.getById(id);
        this.topicDataRelService.removeById(id);
        Topic topic=this.getById(topicDataRel.getTopicId());
        topic.setDataNum(topic.getDataNum()-1);
        this.updateById(topic);
        return Boolean.TRUE;
    }

    @Override
    public TailPage<TopicVTO> topicPage(TopicQueryParamData paramData) {
        QueryWrapper<Topic> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(Topic::getStatus, BizEnumType.CommonStatus.Valid.getKey());
        Page<Topic> page = super.page(new Page<>(paramData.getPn(), paramData.getPs()), queryWrapper);
        return CommonPage.buildPage(page.getCurrent(), page.getSize(), page.getTotal(), TopicBuilder.toTopicVTOs(page.getRecords()));
    }
}
