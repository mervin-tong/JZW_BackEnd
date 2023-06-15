package com.piesat.school.biz.ds.datainf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.base.PageQueryParamData;
import com.piesat.school.biz.ds.dataClass.entity.DataClass;
import com.piesat.school.biz.ds.dataClass.service.IDataClassService;
import com.piesat.school.biz.ds.datainf.builder.DatainfBuilder;
import com.piesat.school.biz.ds.datainf.builder.PageBuilder;
import com.piesat.school.biz.ds.datainf.entity.Contact;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.piesat.school.biz.ds.datainf.mapper.ContactMapper;
import com.piesat.school.biz.ds.datainf.mapper.DatainfMapper;
import com.piesat.school.biz.ds.datainf.service.IContactService;
import com.piesat.school.biz.ds.datainf.service.IDatainfService;
import com.piesat.school.biz.ds.datareview.entity.DataReview;
import com.piesat.school.biz.ds.datareview.mapper.DataReviewMapper;
import com.piesat.school.biz.ds.datareview.service.IDataReviewService;
import com.piesat.school.biz.ds.order.entity.HistoryDownload;
import com.piesat.school.biz.ds.order.mapper.HistoryDownloadMapper;
import com.piesat.school.biz.ds.order.service.IOrderFromService;
import com.piesat.school.biz.ds.topic.entity.Topic;
import com.piesat.school.biz.ds.topic.entity.TopicDataRel;
import com.piesat.school.biz.ds.topic.mapper.TopicDataRelMapper;
import com.piesat.school.biz.ds.topic.mapper.TopicMapper;
import com.piesat.school.biz.ds.topic.service.ITopicService;
import com.piesat.school.biz.ds.user.entity.User;
import com.piesat.school.biz.ds.user.mapper.UserMapper;
import com.piesat.school.biz.ds.user.service.IRoleService;
import com.piesat.school.datainf.param.*;
import com.piesat.school.datainf.vto.*;
import com.piesat.school.emuerlation.BizEnumType;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.PageHelper;
import com.smartwork.api.support.page.TailPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import sun.applet.Main;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 数据信息表 服务实现类
 * </p>x
 *
 * @author 周悦尧
 * @since 2022-01-27
 */
@Service
public class DatainfServiceImpl extends ServiceImpl<DatainfMapper, Datainf> implements IDatainfService {

    @Resource
    private DatainfMapper datainfMapper;

    @Resource
    private ContactMapper contactMapper;
    @Resource
    private HistoryDownloadMapper historyDownloadMapper;
    @Resource
    private IRoleService iRoleService;
    @Resource
    private IDataReviewService iDataReviewService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private DataReviewMapper dataReviewMapper;
    @Resource
    private IContactService contactService;
    @Resource
    private TopicDataRelMapper topicDataRelMapper;
    @Resource
    private TopicMapper topicMapper;
    @Resource
    private ITopicService topicService;
    @Resource
    private IDataClassService dataClassService;
    @Resource
    private IOrderFromService orderFromService;


    @Override
    public TailPage<DataInfListVTO> getAllDatainf() {
        Page<DataInfListVTO> page = new Page<>();
        page.setOptimizeCountSql(false);
        List<DataInfListVTO> list = datainfMapper.getAllDatainf(page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }

//    @Override
//    public TailPage<MyDataInfVTO> myDataMenu(Long userId) {
//        Page<MyDataInfVTO> page = new Page<>();
//        page.setOptimizeCountSql(false);
//        List<MyDataInfVTO> list = datainfMapper.myDataMenu(userId,page);
//
//        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
//
//    }

    //新增数据
    @Override
    public DataInfVTO saveDataInf(DataInfSaveParamData paramData) {
        Datainf datainf = new Datainf();
        if(paramData.getId()!=null){
            datainf=datainfMapper.selectById(paramData.getId());
            if(StringUtils.isNotBlank(paramData.getDoi())){
                datainf.setDoi(paramData.getDoi());
            }
            if(StringUtils.isNotBlank(paramData.getAddress())){
                datainf.setAddress(paramData.getAddress());
            }
            if(StringUtils.isNotBlank(paramData.getDataName())){
                datainf.setDataName(paramData.getDataName());
            }
            if(StringUtils.isNotBlank(paramData.getDataUnit())){
                datainf.setDataUnit(paramData.getDataUnit());
            }
            if(StringUtils.isNotBlank(paramData.getFirstClass())){
                datainf.setFirstClass(paramData.getFirstClass());
            }
            if(StringUtils.isNotBlank(paramData.getIntroduction())){
                datainf.setIntroduction(paramData.getIntroduction());
            }
            if(StringUtils.isNotBlank(paramData.getKeyword())){
                datainf.setKeyword(paramData.getKeyword());
            }
            if(StringUtils.isNotBlank(paramData.getMaker())){
                datainf.setMaker(paramData.getMaker());
            }
            if(StringUtils.isNotBlank(paramData.getMeas())){
                datainf.setMeas(paramData.getMeas());
            }
            if(StringUtils.isNotBlank(paramData.getOrigin())){
                datainf.setOrigin(paramData.getOrigin());
            }
            if(StringUtils.isNotBlank(paramData.getRatio())){
                datainf.setRatio(paramData.getRatio());
            }
            if(StringUtils.isNotBlank(paramData.getSecClass())){
                datainf.setSecClass(paramData.getSecClass());
            }
            if(StringUtils.isNotBlank(paramData.getSolution())){
                datainf.setSolution(paramData.getSolution());
            }
            if(StringUtils.isNotBlank(paramData.getContent())){
                datainf.setContent(paramData.getContent());
            }
            if(StringUtils.isNotBlank(paramData.getDataAmount())){
                datainf.setDataAmount(paramData.getDataAmount());
            }
            if(StringUtils.isNotBlank(paramData.getPic())){
                datainf.setPic(paramData.getPic());
            }
            if(StringUtils.isNotBlank(paramData.getLeftUp())){
                datainf.setLeftUp(paramData.getLeftUp());
            }
            if(StringUtils.isNotBlank(paramData.getRightDown())){
                datainf.setRightDown(paramData.getRightDown());
            }
            if(paramData.getGenerationMode()!=null){
                datainf.setGenerationMode(paramData.getGenerationMode());
            }
            if(paramData.getContact() != null ) {
                Contact contact = new Contact();
                if(datainf.getConId()!=null){
                    contact=contactService.getById(datainf.getConId());
                }
                if (StringUtils.isNotBlank(paramData.getContact().getConAddress())) {
                    contact.setConAddress(paramData.getContact().getConAddress());
                }
                if (StringUtils.isNotBlank(paramData.getContact().getConName())) {
                    contact.setConName(paramData.getContact().getConName());
                }
                if (StringUtils.isNotBlank(paramData.getContact().getEmail())) {
                    contact.setEmail(paramData.getContact().getEmail());
                }
                if (StringUtils.isNotBlank(paramData.getContact().getMobile())) {
                    contact.setMobile(paramData.getContact().getMobile());
                }
                if (StringUtils.isNotBlank(paramData.getContact().getConUnit())) {
                    contact.setConUnit(paramData.getContact().getConUnit());
                }
                contactService.saveOrUpdate(contact);
                if(datainf.getConId()==null){
                    datainf.setConId(contact.getId());
                }
            }
            datainfMapper.updateById(datainf);
        }else{
            if(paramData.getUploadUserId() != null) {
                BeanUtils.copyProperties(paramData,datainf);
                datainf.setThroughReview(BizEnumType.ThroughReview.NOTPASS.getKey());
                datainf.setDeleted(BizEnumType.CommonStatus.Invalid.getKey());
                Contact contact = new Contact();
                BeanUtils.copyProperties(paramData.getContact(),contact);
                contactMapper.insert(contact);
                datainf.setConId(contact.getId());
                boolean saveDatainf = this.save(datainf);
                if (saveDatainf){
                    DataReview dataReview = new DataReview();
                    dataReview.setDataId(datainf.getId());
                    dataReview.setStatus(BizEnumType.ReviewStatus.TOREVIEW.getKey());
                    dataReview.setAdminJudgeId(BizEnumType.Default.NULL.getKey());
                    dataReview.setUserJudgeId(BizEnumType.Default.NULL.getKey());
                    iDataReviewService.createReview(dataReview);
                }
            }
            if(paramData.getFirstClass()!=null){
                DataClass dataClass =dataClassService.getById(paramData.getFirstClass());
                dataClass.setDataNum(dataClass.getDataNum()+1);
                dataClass.setUpdatedAt(new Date());
                dataClassService.updateById(dataClass);
            }
            if(paramData.getSecClass()!=null){
                DataClass dataClass =dataClassService.getById(paramData.getSecClass());
                dataClass.setDataNum(dataClass.getDataNum()+1);
                dataClass.setUpdatedAt(new Date());
                dataClassService.updateById(dataClass);
            }
        }

        return DatainfBuilder.toDataInfVto(datainf);
    }

    //逻辑删除数据
    @Override
    public Boolean delDataInf(String dataIds) {
        List<Long> longs = new ArrayList<>();
        String[] split = dataIds.split(",");
        for (String s : split) {
            longs.add(Long.valueOf(s));
        }
        List<Datainf> datainfs=this.listByIds(longs);
        List<Topic> topics=new ArrayList<>();
        List<TopicDataRel> topicDataRels = topicDataRelMapper.selectList(new QueryWrapper<TopicDataRel>().in("data_id",longs));
        if(topicDataRels.size()!=0) {
            List<Long> topicIds = topicDataRels.stream().map(TopicDataRel::getTopicId).collect(Collectors.toList());
            topics = topicMapper.selectList(new QueryWrapper<Topic>().in("id", topicIds));
        }
        for (Datainf i:datainfs){
            i.setDeleted(BizEnumType.CommonStatus.Valid.getKey());
            if(topicDataRels.stream().anyMatch(e -> e.getDataId().equals(i.getId()))){
                Long topicId=topicDataRels.stream().filter(e -> e.getDataId().equals(i.getId())).findFirst().get().getTopicId();
                for (Topic topic:topics){
                    if(topic.getId().equals(topicId)){
                        topic.setDataNum(topic.getDataNum()-1);
                    }
                }
            }
        }
        topicService.updateBatchById(topics);

        //分类数量处理
        List<Integer> firId=datainfs.stream().map(Datainf::getFirstClass).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> secId=datainfs.stream().map(Datainf::getSecClass).map(Integer::parseInt).collect(Collectors.toList());
        firId.addAll(secId);
        List<DataClass> dataClasses = dataClassService.list(new QueryWrapper<DataClass>().in("id",firId));
        for (Datainf i:datainfs){
            for(DataClass d :dataClasses){
                if(d.getId().equals(Integer.parseInt(i.getFirstClass())) || d.getId().equals(Integer.parseInt(i.getSecClass()))){
                    d.setDataNum(d.getDataNum()-1);
                }
            }
        }
        this.updateBatchById(datainfs);
        return Boolean.TRUE;
    }

    //关键词查找
    @Override
    public TailPage<DataInfListVTO> searchByKeyword(SearchByKeyParamData searchByKeyParamData) {
        Page<DataInfListVTO> page = new Page<>(searchByKeyParamData.getPn(),searchByKeyParamData.getPs());
        page.setOptimizeCountSql(false);
        List<DataInfListVTO> list = datainfMapper.searchByKeyword(searchByKeyParamData,page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }

    //根据类名查找
    @Override
    public TailPage<DataInfListVTO> searchByClass(SearchByClassParamData searchByClassParamData) {
        Page<DataInfListVTO> page = new Page<>(searchByClassParamData.getPn(),searchByClassParamData.getPs());
        page.setOptimizeCountSql(false);
        List<DataInfListVTO> list = datainfMapper.searchByClass(searchByClassParamData,page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }
    //根据时间查找
    @Override
    public TailPage<DataInfListVTO> searchByTime(SearchByTimeParamData searchByTimeParamData) {
        Page<DataInfListVTO> page = new Page<>(searchByTimeParamData.getPn(),searchByTimeParamData.getPs());
        page.setOptimizeCountSql(false);
        List<DataInfListVTO> list = datainfMapper.searchByTime(searchByTimeParamData,page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }

    @Override
    public TailPage<DataInfListVTO> searchAll(SearchAllParamData searchAllParamData) {
        Page<DataInfListVTO> page = new Page<>(searchAllParamData.getPn(),searchAllParamData.getPs());
        page.setOptimizeCountSql(false);
        List<DataInfListVTO> list = datainfMapper.searchAll(searchAllParamData,page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);

        }


    @Override
    public DataInfDetailVTO dataInfDetail(Long dataId) {
        return datainfMapper.dataInfDetail(dataId);
    }


    @Override
    public DataInfVTO getFilePath(Long dataId, Long userId,Long id) {
        Datainf datainf = this.getById(dataId);
        int i=0;
        if(userId !=null) {
            if (datainf.getStatus().equals(BizEnumType.PublicStatus.NoOpen.getKey()) && userId.equals(datainf.getUploadUserId())) {
                i = 1;
            } else if (datainf.getStatus().equals(BizEnumType.PublicStatus.FullOpen.getKey())) {
                i = 1;
            } else if (datainf.getStatus().equals(BizEnumType.PublicStatus.HalfOpen.getKey()) && orderFromService.getById(id).getDataType().equals(BizEnumType.NotificationPushStatus.Pushing.getId())) {
                i = 1;
            }
        }else {
            i=1;
        }
        if(i==1&&userId != null){
            addhistory(dataId, userId);
            return DatainfBuilder.toDataInfVto(datainf);
        }else if(i==1) {
            return DatainfBuilder.toDataInfVto(datainf);
        } else {
            return null;
        }
    }

    @Override
    public Boolean addDownCount(int downCount,Long dataId) {
        UpdateWrapper<Datainf> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("down_count",downCount);
        updateWrapper.eq("id",dataId);
        return this.update(updateWrapper);
    }

    @Override
    public Boolean addhistory(Long dataId, Long userId) {
        HistoryDownload historyDownload = historyDownloadMapper.selectOne(new QueryWrapper<HistoryDownload>().eq("data_id",dataId).eq("user_id", userId));
        int i;
        if(historyDownload==null) {
            HistoryDownload historyDownload1=new HistoryDownload();
            historyDownload1.setDataId(dataId);
            historyDownload1.setUserId(userId);
            historyDownload1.setCreatedAt(new Date());
            historyDownload1.setDownloadCount(1);
            i=historyDownloadMapper.insert(historyDownload1);
        }else {
            historyDownload.setDownloadCount(historyDownload.getDownloadCount()+1);
            historyDownload.setUpdatedAt(new Date());
            i=historyDownloadMapper.updateById(historyDownload);
        }

        Datainf datainf =this.getById(dataId);
        datainf.setDownCount(datainf.getDownCount()+1);
        this.updateById(datainf);
        if(i==1) {
            return true;
        }else {
            return false;
        }
    }


    @Override
    public TailPage<MyDataInfVTO> dataList(DataQueryParamData paramData) {
        QueryWrapper<Datainf> queryWrapper=new QueryWrapper<>();
        if(paramData.getAuditStatus()!=null){
            queryWrapper.eq("through_review",paramData.getAuditStatus());
        }
        queryWrapper.lambda().eq(Datainf::getDeleted, BizEnumType.CommonStatus.Invalid.getKey());
        if(paramData.getPublisher()!=null){
            queryWrapper.eq("upload_user_id",paramData.getPublisher());
        }
        if(StringUtils.isNotBlank(paramData.getCondition())){
            queryWrapper.lambda().like(Datainf::getDataName,paramData.getCondition());
        }
        if(paramData.getLimitUserAble()!=null&&!paramData.getLimitUserAble()){
            queryWrapper.lambda().eq(Datainf::getPublisherStatus, BizEnumType.CommonStatus.Invalid.getKey());
        }
        queryWrapper.orderByDesc("created_at");

//        Page<Datainf> dataInfos1=this.page(new Page<>(paramData.getPn(), paramData.getPs()), queryWrapper);
        List<Datainf> dataInfos=baseMapper.selectList(queryWrapper);
        List<MyDataInfVTO> myDataInfVTOS=new ArrayList<>();
//        if(dataInfos.getRecords().size()>0){
        if(dataInfos.size()>0){
            List<Long> userIds=dataInfos.stream().map(Datainf::getUploadUserId).collect(Collectors.toList());
            List<Long> contactIds=dataInfos.stream().filter(e->e.getConId()!=null).map(Datainf::getConId).collect(Collectors.toList());
            List<Long> dataIds = dataInfos.stream().map(Datainf::getId).collect(Collectors.toList());
            List<Contact> contacts=new ArrayList<>();
            if(contactIds!=null&&contactIds.size()>0){
                contacts=this.contactService.listByIds(contactIds);
            }
            Map<Long,String> contactMap=null;
            if(contacts!=null){
                contactMap=contacts.stream().collect(Collectors.toMap(Contact::getId,Contact::getConName,(key1, key2)->key2));
            }
            List<User> users=this.userMapper.selectBatchIds(userIds);
            myDataInfVTOS=DatainfBuilder.toMyDataInfVTOs(dataInfos,users,contactMap);
            List<DataReview> dataReviews=dataReviewMapper.selectList(new QueryWrapper<DataReview>().in("data_id",dataIds));
            for (int i=0 ;i<myDataInfVTOS.size();i++){
                for (DataReview dataReview:dataReviews){
                    if(myDataInfVTOS.get(i).getId().equals(dataReview.getDataId())){
                        myDataInfVTOS.get(i).setStatus(dataReview.getStatus());
                    }
                }
            }
        }

        Page<MyDataInfVTO> page=new Page<>(paramData.getPn(),paramData.getPs(),myDataInfVTOS.size());
        if (paramData.getStatus().size()!=0){
            List<MyDataInfVTO> collects = myDataInfVTOS.stream().filter(o -> paramData.getStatus().contains(o.getStatus().toString())).collect(Collectors.toList());
            List<MyDataInfVTO> target= PageBuilder.page(page.getCurrent(),page.getSize(), collects);
            page.setRecords(target);
            page.setTotal(collects.size());
            return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(), target);
        }
//        page.setRecords(PageBuilder.page(page.getCurrent(),page.getSize(),myDataInfVTOS));
        List<MyDataInfVTO> vtoList=PageBuilder.page(page.getCurrent(),page.getSize(),myDataInfVTOS);
        page.setRecords(vtoList);
        page.setTotal(myDataInfVTOS.size());
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),vtoList);
    }


    @Override
    public TailPage<DataInfListVTO> thematicData(MetadataQueryParam paramData) {
        Page<DataInfListVTO> page = new Page<>(paramData.getPn(),paramData.getPs());
        page.setOptimizeCountSql(false);
        List<DataInfListVTO> list = datainfMapper.getThematicData(paramData,page);

        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }

    @Override
    public StatisticsVTO statistics() {
        StatisticsVTO statisticsVTO =new StatisticsVTO();
        List<Datainf> datainfs =datainfMapper.selectList(new QueryWrapper<Datainf>().isNotNull("id"));
        statisticsVTO.setTotal(datainfs.size());
        Long openDataNum=0L;
        Long semiopenDataNum=0L;
        Long closedDataNum=0L;
        Long pageViews=0L;
        Long downloadNum=0L;
        for (Datainf datainf:datainfs){
            if(datainf.getStatus()==BizEnumType.PublicStatus.NoOpen.getKey()){
                closedDataNum+=1;
            }else if (datainf.getStatus()==BizEnumType.PublicStatus.HalfOpen.getKey()){
                semiopenDataNum+=1;
            }else {
                openDataNum+=1;
            }
            pageViews+=datainf.getKickCount();
            downloadNum+=datainf.getDownCount();
        }
        statisticsVTO.setClosedDataNum(closedDataNum);
        statisticsVTO.setDownloadNum(downloadNum);
        statisticsVTO.setOpenDataNum(openDataNum);
        statisticsVTO.setPageViews(pageViews);
        statisticsVTO.setSemiopenDataNum(semiopenDataNum);
        statisticsVTO.setRegistereMembers(userMapper.selectCount(new QueryWrapper<User>().isNotNull("id")));
        return statisticsVTO;
    }

    @Override
    public TailPage<DataInfListVTO> highAttention(PageQueryParamData paramData) {
        QueryWrapper<Datainf> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("kick_count");
        Page<Datainf> dataInfos=this.page(new Page<>(paramData.getPn(), paramData.getPs()), queryWrapper);
        List<DataInfListVTO> list=new ArrayList<>();
        for(Datainf datainf:dataInfos.getRecords()) {
            DataInfListVTO dataInfListVTO =new DataInfListVTO();
            if(!datainf.getDeleted().equals(1) && !datainf.getPublisherStatus().equals(1)) {
                BeanUtils.copyProperties(datainf, dataInfListVTO);
                list.add(dataInfListVTO);
            }
        }
        return CommonPage.buildPage(dataInfos.getCurrent(),dataInfos.getSize(),dataInfos.getTotal(),list);
    }

    @Override
    public TailPage<DataInfListVTO> upToDateAttention(PageQueryParamData paramData) {
        QueryWrapper<Datainf> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("through_review", BizEnumType.ThroughReview.PASS.getKey()).orderByDesc("created_at");
        Page<Datainf> dataInfos=this.page(new Page<>(paramData.getPn(), paramData.getPs()), queryWrapper);
        List<DataInfListVTO> list=new ArrayList<>();
        for(Datainf datainf:dataInfos.getRecords()) {
            DataInfListVTO dataInfListVTO =new DataInfListVTO();
            if(!datainf.getDeleted().equals(1) && !datainf.getPublisherStatus().equals(1)) {
                BeanUtils.copyProperties(datainf, dataInfListVTO);
                list.add(dataInfListVTO);
            }
        }
        return CommonPage.buildPage(dataInfos.getCurrent(),dataInfos.getSize(),dataInfos.getTotal(),list);
    }

    @Override
    public TailPage<DataInfDetailVTO> menuDataList(MenuDataParam param) {
//        Page<DataInfDetailVTO> page = new Page<>(param.getPn(),param.getPs());
//        page.setOptimizeCountSql(false);
        String orderStr="";
        if(StringUtils.isNotBlank(param.getAscAttributes())){
            String[] istr=param.getAscAttributes().split(",");
            for(String i:istr){
                if(StringUtils.isNotBlank(orderStr)&&orderStr.split(",").length>=1){
                    orderStr+=",";
                }
                orderStr+=" a."+i+" asc";
            }
        }
        if(StringUtils.isNotBlank(param.getDescAttributes())){
            String[] istr=param.getDescAttributes().split(",");
            for(String i:istr){
                if(StringUtils.isNotBlank(orderStr)&&orderStr.split(",").length>=1){
                    orderStr+=",";
                }
                orderStr+=" a."+i+" desc";
            }
        }
        List<DataInfDetailVTO> dataInfDetailVTOS = baseMapper.menuDataListDetail(param,null,orderStr);
        List<DataInfDetailVTO> result = new ArrayList<>();
        if(StringUtils.isNotBlank(param.getLeftUp()) && StringUtils.isNotBlank(param.getRightDown())) {
            double leftX = Double.parseDouble(param.getLeftUp().split(",")[0]);
            double leftY = Double.parseDouble(param.getLeftUp().split(",")[1]);
            double rightX = Double.parseDouble(param.getRightDown().split(",")[0]);
            double rightY = Double.parseDouble(param.getRightDown().split(",")[1]);
            for (DataInfDetailVTO dataInfDetailVTO:dataInfDetailVTOS){
                double leftxx = Double.parseDouble(dataInfDetailVTO.getLeftUp().split(",")[0]);
                double leftyy = Double.parseDouble(dataInfDetailVTO.getLeftUp().split(",")[1]);
                double rightxx = Double.parseDouble(dataInfDetailVTO.getRightDown().split(",")[0]);
                double rightyy = Double.parseDouble(dataInfDetailVTO.getRightDown().split(",")[1]);
                if(leftxx>leftX && leftyy<leftY && rightxx<rightX && rightyy>rightY){
                    result.add(dataInfDetailVTO);
                }
            }
        }else {
//            results=page1.getRecords();
            result=dataInfDetailVTOS;
        }
        List<DataInfDetailVTO> results = new ArrayList<>();
        if(param.getRelease()!=null) {
            for (DataInfDetailVTO detailVTOS : result) {
                if (param.getRelease() == 0 && !detailVTOS.getThroughReview().equals(BizEnumType.ReviewStatus.RELEASE.getKey())) {
                    results.add(detailVTOS);
                } else if (param.getRelease() == 1 && detailVTOS.getThroughReview().equals(BizEnumType.ReviewStatus.RELEASE.getKey())) {
                    results.add(detailVTOS);
                }
            }
        }else{
            results=result;
        }
        List<DataInfDetailVTO> detailVTOS= PageHelper.pageList(results, param.getPn(), param.getPs());
        return CommonPage.buildPage(param.getPn(),param.getPs(),results.size(),detailVTOS);
    }
}
