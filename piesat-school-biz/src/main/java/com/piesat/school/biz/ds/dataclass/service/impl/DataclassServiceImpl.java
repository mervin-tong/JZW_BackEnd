package com.piesat.school.biz.ds.dataclass.service.impl;

import com.piesat.school.biz.common.helper.BizCommonValidateHelper;
import com.piesat.school.biz.ds.dataclass.bulider.DataClassBuilder;
import com.piesat.school.biz.ds.dataclass.entity.Dataclass;
import com.piesat.school.biz.ds.dataclass.mapper.DataclassMapper;
import com.piesat.school.biz.ds.dataclass.service.IDataclassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.dataclass.param.DataClassParamData;
import com.piesat.school.dataclass.vto.DataClassVTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 数据分类表 服务实现类
 * </p>
 *
 * @author 周悦尧
 * @since 2022-01-17
 */
@Service
public class DataclassServiceImpl extends ServiceImpl<DataclassMapper, Dataclass> implements IDataclassService {

    @Autowired
    private DataclassMapper dataclassMapper;
    @Override
    public List<DataClassVTO> getAllDataClass() {
        List<Dataclass> datas = dataclassMapper.getAllDataClass();
        return DataClassBuilder.toDataClassVtos(datas);

    }

    @Override
    public DataClassVTO saveDataClass(DataClassParamData paramData) {
        Long id = paramData.getId();
        Dataclass dataclass = null;
        if(id == null){
            dataclass = new Dataclass();
            dataclass.setFirstClass(paramData.getFirstClass());
            dataclass.setSecClass(paramData.getFirstClass());
            this.save(dataclass);
        }else{
            dataclass = BizCommonValidateHelper.valdiateGetById(id,this);
            dataclass.setFirstClass(paramData.getFirstClass());
            dataclass.setSecClass(paramData.getSecClass());
            this.updateById(dataclass);

        }

        return DataClassBuilder.toDataClassVto(dataclass);
    }
}
