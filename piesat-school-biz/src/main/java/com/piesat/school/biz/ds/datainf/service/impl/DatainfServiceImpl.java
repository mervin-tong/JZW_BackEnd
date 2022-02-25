package com.piesat.school.biz.ds.datainf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mysql.cj.util.StringUtils;
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
import com.piesat.school.datainf.vto.DataInfVTO;
import com.smartwork.api.support.page.TailPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private DatainfMapper datainfMapper;
    @Autowired
    private KeyMapper keyMapper;
    @Autowired
    private ContactMapper contactMapper;

    @Override
    public List<DataInfVTO> getAllDatainf() {
        List<Datainf> datas = datainfMapper.getAllDatainf();
        return DatainfBuilder.toDataInfVtos(datas);
    }

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
        datainf.setDataClass(paramData.getFirstClass()+","+paramData.getSecClass());

    //        BeanUtils.copyProperties(datainf,paramData);

        Key key = new Key();
        key.setKeyword(paramData.getKeywords().getKeyword());
        keyMapper.insert(key);
        datainf.setKeyId(key.getId());

        Contact contact = new Contact();
        BeanUtils.copyProperties(contact,paramData.getContact());
        contactMapper.insert(contact);
        datainf.setConId(contact.getId());

        this.save(datainf);
        return DatainfBuilder.toDataInfVto(datainf);


    }

    @Override
    public TailPage<DataInfVTO> listDataInf(DataInfListParamData paramData) {
        QueryWrapper<Datainf> wrapper = new QueryWrapper<Datainf>();
//                .like(!StringUtils.isNullOrEmpty(paramData.getKeyword()),"keyword",paramData.getKeyword())

        return null;
    }
}
