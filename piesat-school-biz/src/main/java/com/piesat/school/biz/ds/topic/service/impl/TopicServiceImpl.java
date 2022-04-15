package com.piesat.school.biz.ds.topic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.datainf.builder.DatainfBuilder;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.piesat.school.biz.ds.datainf.service.IDatainfService;
import com.piesat.school.biz.ds.topic.builder.TopicBuilder;
import com.piesat.school.biz.ds.topic.entity.Topic;
import com.piesat.school.biz.ds.topic.entity.TopicDataRel;
import com.piesat.school.biz.ds.topic.mapper.TopicMapper;
import com.piesat.school.biz.ds.topic.service.ITopicDataRelService;
import com.piesat.school.biz.ds.topic.service.ITopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.biz.ds.user.entity.User;
import com.piesat.school.biz.ds.user.service.IUserService;
import com.piesat.school.datainf.vto.MyDataInfVTO;
import com.piesat.school.emuerlation.BizEnumType;
import com.piesat.school.topic.param.*;
import com.piesat.school.topic.vto.TopicDetailVTO;
import com.piesat.school.topic.vto.TopicVTO;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 周悦尧
 * @since 2022-03-09
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements ITopicService {
    @Resource
    private TopicMapper topicMapper;
    @Resource
    private ITopicDataRelService topicDataRelService;
    @Resource
    private IDatainfService dataInfService;
    @Resource
    private IUserService userService;
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
        this.saveOrUpdate(topic);
        return TopicBuilder.toTopicVto(topic);
    }

    //主题细节
    @Override
    public List<TopicDetailVTO> detailTopic(Long topicId) {
        return topicMapper.detailTopic(topicId);
    }


    @Override
    public TopicVTO indexDetailTopic(Long topicId) {
        return topicMapper.indexDetailTopic(topicId);
    }

    @Override
    public Boolean del(TopicDelParamData paramData) {
        if(paramData.getId()!=null){
            //1.专题主体逻辑删除
            Topic topic=this.getById(paramData.getId());
            topic.setDataNum(0);
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
    public Boolean delTopicData(TopicDataDelParamData paramData) {
        QueryWrapper<TopicDataRel> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(TopicDataRel::getTopicId,paramData.getTopicId()).eq(TopicDataRel::getDataId,paramData.getDataId());
        TopicDataRel topicDataRel=this.topicDataRelService.getOne(queryWrapper);
        this.topicDataRelService.removeById(topicDataRel.getId());
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

    @Override
    public TailPage<MyDataInfVTO> topicDatalist(TopicQueryParamData paramData) {
        QueryWrapper<TopicDataRel> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(TopicDataRel::getTopicId,paramData.getTopicId());
        Page<TopicDataRel> topicDataRels=this.topicDataRelService.page(new Page<>(paramData.getPn(), paramData.getPs()), queryWrapper );

        List<Long> dataIds=topicDataRels.getRecords().stream().map(TopicDataRel::getDataId).collect(Collectors.toList());
        List<Datainf> dataInfos=this.dataInfService.listByIds(dataIds);
        List<MyDataInfVTO> myDataInfVTOS=new ArrayList<>();
        if(dataInfos.size()>0){
            List<Long> userIds=dataInfos.stream().map(Datainf::getUploadUserId).collect(Collectors.toList());
            List<User> users=this.userService.listByIds(userIds);
            myDataInfVTOS= DatainfBuilder.toMyDataInfVTOs(dataInfos,users);
        }
        return CommonPage.buildPage(topicDataRels.getCurrent(), topicDataRels.getSize(), topicDataRels.getTotal(), myDataInfVTOS);
    }

    @Override
    public TopicVTO detail(TopicQueryParamData paramData) {
        Topic topic=this.getById(paramData.getTopicId());
        return TopicBuilder.toTopicVto(topic);
    }
}
