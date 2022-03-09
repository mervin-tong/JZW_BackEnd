package com.piesat.school.biz.ds.datainf.service.impl;

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
import com.piesat.school.biz.ds.orderfrom.entity.HistoryDownload;
import com.piesat.school.biz.ds.orderfrom.mapper.HistoryDownloadMapper;

import com.piesat.school.biz.ds.user.service.IRoleService;
import com.piesat.school.datainf.param.DataInfListParamData;

import com.piesat.school.datainf.param.DataInfSaveParamData;
import com.piesat.school.datainf.param.SearchByClassParamData;
import com.piesat.school.datainf.param.SearchByKeyParamData;
import com.piesat.school.datainf.param.*;
import com.piesat.school.datainf.vto.DataInfDetailVTO;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datainf.vto.DataInfVTO;
import com.piesat.school.emuerlation.BizEnumType;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 数据信息表 服务实现类
 * </p>
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

    @Override
    public List<DataInfVTO> getAllDatainf() {
        List<Datainf> datas = datainfMapper.getAllDatainf();
        return DatainfBuilder.toDataInfVtos(datas);
    }

    //新增数据
    @Override
    public DataInfVTO saveDataInf(DataInfSaveParamData paramData) {
        Datainf datainf = new Datainf();
        datainf.setDataName(paramData.getDataName());
        datainf.setMaker(paramData.getMaker());
        datainf.setDoi(paramData.getDoi());
        datainf.setDataUnit(paramData.getDataUnit());
        datainf.setAddress(paramData.getAddress());
        datainf.setDoi(paramData.getDoi());
        datainf.setMeas(paramData.getMeas());
        datainf.setIntroduction(paramData.getIntroduction());
        datainf.setOrigin(paramData.getOrigin());
        datainf.setSolution(paramData.getSolution());
        datainf.setRatio(paramData.getRatio());
        datainf.setStartAt(paramData.getStartAt());
        datainf.setStatus(paramData.getStatus());
        datainf.setEndAt(paramData.getEndAt());
        datainf.setFirstClass(paramData.getFirstClass());
        datainf.setSecClass(paramData.getSecClass());
        datainf.setKeyword(paramData.getKeyword());
        datainf.setThroughReview(BizEnumType.ThroughReview.NOTPASS.getKey());
        datainf.setUploadUserId(paramData.getUploadUserId());
    //        BeanUtils.copyProperties(datainf,paramData);



        Contact contact = new Contact();
        BeanUtils.copyProperties(contact,paramData.getContact());
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
        return DatainfBuilder.toDataInfVto(datainf);
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

    //上传文件
    @Override
    public DataInfVTO uploadDataInf(String file, Long dataId) throws IOException {

        Datainf datainf = BizCommonValidateHelper.valdiateGetById(dataId,this);
//        String fileLocation = FileUploadUtils.upload(file);
        datainf.setContent(file);
        this.updateById(datainf);
        return DatainfBuilder.toDataInfVto(datainf);
    }

    @Override
    public DataInfDetailVTO dataInfDetail(Long dataInfId) {
        return datainfMapper.dataInfDetail(dataInfId);
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

    @Override
    public Boolean addTopicData(Long topicId, Long dataId) {
        Datainf datainf = BizCommonValidateHelper.valdiateGetById(dataId,this);
        if(topicId != null){
            datainf.setTopicId(topicId);
            return this.updateById(datainf);
        }
        return Boolean.FALSE;
    }
}
