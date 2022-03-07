package com.piesat.school.biz.ds.datainf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.common.helper.BizCommonValidateHelper;
import com.piesat.school.biz.ds.datainf.builder.DatainfBuilder;
import com.piesat.school.biz.ds.datainf.entity.Contact;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.piesat.school.biz.ds.datainf.entity.Key;
import com.piesat.school.biz.ds.datainf.mapper.ContactMapper;
import com.piesat.school.biz.ds.datainf.mapper.DatainfMapper;
import com.piesat.school.biz.ds.datainf.mapper.KeyMapper;
import com.piesat.school.biz.ds.datainf.service.IDatainfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.datainf.param.DataInfListParamData;
import com.piesat.school.datainf.param.DataInfSaveParamData;
import com.piesat.school.datainf.param.SearchByClassParamData;
import com.piesat.school.datainf.param.SearchByKeyParamData;
import com.piesat.school.datainf.vto.DataInfDetailVTO;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datainf.vto.DataInfVTO;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.apache.commons.io.IOUtils;
import org.mapstruct.Context;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
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
    private KeyMapper keyMapper;
    @Resource
    private ContactMapper contactMapper;

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
        datainf.setStatus(paramData.getStatus().getKey());
        datainf.setEndAt(paramData.getEndAt());
        datainf.setFirstClass(paramData.getFirstClass());
        datainf.setSecClass(paramData.getSecClass());
        datainf.setTopic(paramData.getTopic());
    //        BeanUtils.copyProperties(datainf,paramData);



        Contact contact = new Contact();
        BeanUtils.copyProperties(contact,paramData.getContact());
        contactMapper.insert(contact);
        datainf.setConId(contact.getId());

        this.save(datainf);
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

    //上传文件
    @Override
    public DataInfVTO uploadDataInf(String file, Long dataid) throws IOException {

        Datainf datainf = BizCommonValidateHelper.valdiateGetById(dataid,this);
//        String fileLocation = FileUploadUtils.upload(file);
        datainf.setContent(file);
        this.updateById(datainf);
        return DatainfBuilder.toDataInfVto(datainf);
    }


    @Override
    public DataInfVTO getFilePath(Long dataId) {
        Datainf datainf = BizCommonValidateHelper.valdiateGetById(dataId,this);
        return DatainfBuilder.toDataInfVto(datainf);
    }

    @Override
    public Boolean addDownCount(int downCount,Long dataId) {
        UpdateWrapper<Datainf> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("dow_count",downCount);
        updateWrapper.eq("id",dataId);
        return this.update(updateWrapper);
    }

    public DataInfDetailVTO dataInfDetail(Long dataInfId) {
        return datainfMapper.dataInfDetail(dataInfId);
    }


}
