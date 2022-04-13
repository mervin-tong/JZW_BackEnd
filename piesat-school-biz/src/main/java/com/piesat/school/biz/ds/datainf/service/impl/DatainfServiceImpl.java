package com.piesat.school.biz.ds.datainf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.common.helper.BizCommonValidateHelper;
import com.piesat.school.biz.ds.datainf.builder.DatainfBuilder;
import com.piesat.school.biz.ds.datainf.entity.Contact;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.piesat.school.biz.ds.datainf.mapper.ContactMapper;
import com.piesat.school.biz.ds.datainf.mapper.DatainfMapper;
import com.piesat.school.biz.ds.datainf.service.IDatainfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.biz.ds.datareview.entity.DataReview;
import com.piesat.school.biz.ds.datareview.mapper.DataReviewMapper;
import com.piesat.school.biz.ds.datareview.service.IDataReviewService;
import com.piesat.school.biz.ds.order.entity.HistoryDownload;
import com.piesat.school.biz.ds.order.mapper.HistoryDownloadMapper;

import com.piesat.school.biz.ds.user.entity.User;
import com.piesat.school.biz.ds.user.mapper.UserMapper;
import com.piesat.school.biz.ds.user.service.IRoleService;

import com.piesat.school.datainf.param.DataInfSaveParamData;
import com.piesat.school.datainf.param.SearchByClassParamData;
import com.piesat.school.datainf.param.SearchByKeyParamData;
import com.piesat.school.datainf.param.*;
import com.piesat.school.datainf.vto.DataInfDetailVTO;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datainf.vto.DataInfVTO;
import com.piesat.school.datainf.vto.MyDataInfVTO;
import com.piesat.school.emuerlation.BizEnumType;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private DataReviewMapper dataReviewMapper;
    @Resource
    private IRoleService iRoleService;
    @Resource
    private IDataReviewService iDataReviewService;
    @Resource
    private UserMapper userMapper;

    @Override
    public TailPage<DataInfListVTO> getAllDatainf() {
        Page<DataInfListVTO> page = new Page<>();
        page.setOptimizeCountSql(false);
        List<DataInfListVTO> list = datainfMapper.getAllDatainf(page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }

    @Override
    public TailPage<MyDataInfVTO> myDataMenu(Long userId) {
        Page<MyDataInfVTO> page = new Page<>();
        page.setOptimizeCountSql(false);
        List<MyDataInfVTO> list = datainfMapper.myDataMenu(userId,page);

        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);

    }

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
            Contact contact = new Contact();
            if(datainf.getConId()!=null){
                contact=contactMapper.selectById(datainf.getConId());
            }
            if(StringUtils.isNotBlank(paramData.getContact().getAddress())){
                contact.setConAddress(paramData.getContact().getAddress());
            }
            if(StringUtils.isNotBlank(paramData.getContact().getConName())){
                contact.setConName(paramData.getContact().getConName());
            }
            if(StringUtils.isNotBlank(paramData.getContact().getEmail())){
                contact.setEmail(paramData.getContact().getEmail());
            }
            if(StringUtils.isNotBlank(paramData.getContact().getMobile())){
                contact.setMobile(paramData.getContact().getMobile());
            }
            if(StringUtils.isNotBlank(paramData.getContact().getUnit())){
                contact.setConUnit(paramData.getContact().getUnit());
            }
            if(datainf.getConId()!=null){
                contactMapper.updateById(contact);
            }else {
                contactMapper.insert(contact);
                datainf.setConId(contact.getId());
            }
            datainfMapper.updateById(datainf);
        }else{
            if(paramData.getUploadUserId() != null) {
                BeanUtils.copyProperties(paramData,datainf);
                datainf.setThroughReview(BizEnumType.ThroughReview.NOTPASS.getKey());
                datainf.setDeleted(BizEnumType.CommonStatus.Valid.getKey());
                Contact contact = new Contact();
                BeanUtils.copyProperties(contact, paramData.getContact());
                contactMapper.insert(contact);
                datainf.setConId(contact.getId());
                boolean saveDatainf = this.save(datainf);
                if (saveDatainf){
                    DataReview dataReview = new DataReview();
                    dataReview.setDataId(datainf.getId());
                    dataReview.setStatus(BizEnumType.ReviewStatus.TOREVIEW.getKey());
                    dataReview.setAdminJudgeId(BizEnumType.Default.NULL.getKey());
                    dataReview.setUserJudgeId(BizEnumType.Default.NULL.getKey());
                    dataReview.setCreatedAt(new Date());
                    if (iRoleService.isEGCAdmin(datainf.getUploadUserId())){
                        dataReview.setStatus(BizEnumType.ReviewStatus.FIRSTREVIEWPASS.getKey());
                    }
                    iDataReviewService.createReview(dataReview);
                }
            }
        }

        return DatainfBuilder.toDataInfVto(datainf);
    }

    //逻辑删除数据
    @Override
    public Boolean delDataInf(String dataId, Long userId) {
        if(userId == null){
            return Boolean.FALSE;
        }
        List<Long> longs = new ArrayList<>();
        String[] split = dataId.split(",");
        for (String s : split) {
            longs.add(Long.valueOf(s));
        }
        return datainfMapper.delDataInf(longs);
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

    //上传文件
    @Override
    public Boolean uploadDataInf(String file,String amount) throws IOException {
        if(file == null || amount == null){
            return Boolean.FALSE;
        }
//        Datainf datainf = BizCommonValidateHelper.valdiateGetById(this);
//        String fileLocation = FileUploadUtils.upload(file);
//        datainf.setContent(file);
//        datainf.setDataAmount(amount);
//        return this.updateById(datainf);
        return Boolean.TRUE;
    }

    @Override
    public Boolean uploadPic(String pic, Long dataId) {
        if(dataId == null || pic == null){
            return Boolean.FALSE;
        }
        Datainf datainf = BizCommonValidateHelper.valdiateGetById(dataId,this);
        datainf.setPic(pic);
        return this.updateById(datainf);
    }

    @Override
    public DataInfDetailVTO dataInfDetail(Long dataId) {
        return datainfMapper.dataInfDetail(dataId);
    }


    @Override
    public DataInfVTO getFilePath(Long dataId) {
        Datainf datainf = BizCommonValidateHelper.valdiateGetById(dataId,this);
        return DatainfBuilder.toDataInfVto(datainf);
    }

    @Override
    public Boolean addDownCount(int downCount,Long dataId) {
        UpdateWrapper<Datainf> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("down_count",downCount);
        updateWrapper.eq("id",dataId);
        return this.update(updateWrapper);
    }

//    @Override
//    public DataInfDetailVTO dataInfDetail(Long dataInfId) {
//        return datainfMapper.dataInfDetail(dataInfId);
//    }

    @Override
    public Boolean addhistory(Long dataId, Long userId) {
        HistoryDownload historyDownload = new HistoryDownload();
        historyDownload.setDataId(dataId);
        historyDownload.setUserId(userId);
        try {
            int insert = historyDownloadMapper.insert(historyDownload);
        }catch (DataAccessException e){
            return false;
        }
        return true;
    }
    //新增专题数据
    @Override
    public Boolean addTopicData(Long topicId, Long dataId) {
        Datainf datainf = BizCommonValidateHelper.valdiateGetById(dataId,this);
        if(topicId != null && datainf.getDeleted() != BizEnumType.CommonStatus.Invalid.getKey()){
            datainf.setTopicId(topicId);
            return this.updateById(datainf);
        }
        return Boolean.FALSE;
    }

    //删除专题数据
    @Override
    public Boolean delTopicData(Long topicId, Long dataId) {
        Datainf datainf = BizCommonValidateHelper.valdiateGetById(dataId,this);
        if(topicId == datainf.getTopicId() && datainf.getDeleted() != BizEnumType.CommonStatus.Invalid.getKey()){
            datainf.setTopicId((long)BizEnumType.CommonStatus.Invalid.getKey());
            return this.updateById(datainf);
        }
        return Boolean.FALSE;
    }

    @Override
    public List<MyDataInfVTO> dataList(DataQueryParamData paramData) {
        QueryWrapper<Datainf> queryWrapper=new QueryWrapper<>();
        if(paramData.getAuditStatus()!=null){
            queryWrapper.eq("through_review",paramData.getAuditStatus());
        }
        if(paramData.getPublisher()!=null){
            queryWrapper.eq("upload_user_id",paramData.getPublisher());
        }
        List<Datainf> dataInfos=this.list(queryWrapper);

        List<MyDataInfVTO> myDataInfVTOS=new ArrayList<>();
        if(dataInfos.size()>0){
            List<Long> userIds=dataInfos.stream().map(Datainf::getUploadUserId).collect(Collectors.toList());
            List<User> users=this.userMapper.selectBatchIds(userIds);
            myDataInfVTOS=DatainfBuilder.toMyDataInfVTOs(dataInfos,users);
        }
        return myDataInfVTOS;
    }
}
