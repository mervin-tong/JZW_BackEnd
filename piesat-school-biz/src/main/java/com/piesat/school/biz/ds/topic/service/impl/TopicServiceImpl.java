package com.piesat.school.biz.ds.topic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.datainf.builder.DatainfBuilder;
import com.piesat.school.biz.ds.datainf.entity.Contact;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.piesat.school.biz.ds.datainf.service.IContactService;
import com.piesat.school.biz.ds.datainf.service.IDatainfService;
import com.piesat.school.biz.ds.topic.builder.TopicBuilder;
import com.piesat.school.biz.ds.topic.entity.Topic;
import com.piesat.school.biz.ds.topic.entity.TopicDataRel;
import com.piesat.school.biz.ds.topic.mapper.TopicDataRelMapper;
import com.piesat.school.biz.ds.topic.mapper.TopicMapper;
import com.piesat.school.biz.ds.topic.service.ITopicDataRelService;
import com.piesat.school.biz.ds.topic.service.ITopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.biz.ds.user.entity.User;
import com.piesat.school.biz.ds.user.service.IUserService;
import com.piesat.school.datainf.vto.DataInfListVTO;
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
import java.util.Map;
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
    private TopicDataRelMapper topicDataRelMapper;
    @Resource
    private ITopicDataRelService topicDataRelService;
    @Resource
    private IDatainfService dataInfService;
    @Resource
    private IUserService userService;
    @Resource
    private IContactService contactService;
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
            TopicDataRel topicDataRel1=topicDataRelMapper.selectOne(new QueryWrapper<TopicDataRel>().eq("data_id", topicDataRel.getDataId()).eq("topic_id", topicDataRel.getTopicId()));
            if(topicDataRel1 ==null) {
                topicDataRels.add(topicDataRel);
            }
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
        List<TopicVTO> topicVTOS=TopicBuilder.toTopicVTOs(page.getRecords());
        if(paramData.getTopicId().equals(-1L)){
            for(TopicVTO topicVTO:topicVTOS){
                paramData.setTopicId(topicVTO.getId());
                topicVTO.setMyDataInfVTO(topicDataRelMapper.getTopicDatalist(paramData,new Page<>(paramData.getPn(),paramData.getPs())));
            }
        }
        return CommonPage.buildPage(page.getCurrent(), page.getSize(), page.getTotal(), topicVTOS);
    }

    @Override
    public TailPage<MyDataInfVTO> topicDatalist(TopicQueryParamData paramData) {
//        List<MyDataInfVTO> myDataInfVTOS=new ArrayList<>();
//        QueryWrapper<TopicDataRel> queryWrapper=new QueryWrapper<>();
//        queryWrapper.lambda().eq(TopicDataRel::getTopicId,paramData.getTopicId());
//        Page<TopicDataRel> topicDataRels=this.topicDataRelService.page(new Page<>(paramData.getPn(), paramData.getPs()), queryWrapper );
//
//        List<Long> dataIds=topicDataRels.getRecords().stream().map(TopicDataRel::getDataId).collect(Collectors.toList());
//        if(dataIds!=null&&dataIds.size()>0){
//            List<Datainf> dataInfos=this.dataInfService.listByIds(dataIds);
//            List<Long> contactIds=dataInfos.stream().filter(e->e.getConId()!=null).map(Datainf::getConId).collect(Collectors.toList());
//            List<Contact> contacts=null;
//            if(contactIds!=null&&contactIds.size()>0){
//                contacts=this.contactService.listByIds(contactIds);
//            }
//            if(dataInfos.size()>0){
//                List<Long> userIds=dataInfos.stream().map(Datainf::getUploadUserId).collect(Collectors.toList());
//                List<User> users=this.userService.listByIds(userIds);
//                Map<Long,String> contactMap=null;
//                if(contacts!=null){
//                    contactMap=contacts.stream().collect(Collectors.toMap(Contact::getId,Contact::getConName,(key1, key2)->key2));
//                }
//                myDataInfVTOS= DatainfBuilder.toMyDataInfVTOs(dataInfos,users,contactMap);
//            }
//        }
        Page<MyDataInfVTO> page = new Page<>(paramData.getPn(),paramData.getPs());
        page.setOptimizeCountSql(false);
        List<MyDataInfVTO> myDataInfVTOList=topicDataRelMapper.getTopicDatalist(paramData,page);
        return CommonPage.buildPage(page.getCurrent(), page.getSize(), page.getTotal(), myDataInfVTOList);
    }

    @Override
    public TopicVTO detail(TopicQueryParamData paramData) {
        Topic topic=this.getById(paramData.getTopicId());
        return TopicBuilder.toTopicVto(topic);
    }
}
