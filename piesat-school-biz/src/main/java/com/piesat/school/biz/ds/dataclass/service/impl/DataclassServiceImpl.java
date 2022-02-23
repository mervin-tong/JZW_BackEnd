package com.piesat.school.biz.ds.dataclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.common.helper.BizCommonValidateHelper;
import com.piesat.school.biz.ds.dataclass.bulider.DataClassBuilder;
import com.piesat.school.biz.ds.dataclass.entity.Dataclass;
import com.piesat.school.biz.ds.dataclass.mapper.DataclassMapper;
import com.piesat.school.biz.ds.dataclass.service.IDataclassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.dataclass.param.DataClassDelParamData;
import com.piesat.school.dataclass.param.DataClassParamData;
import com.piesat.school.dataclass.param.DataClassQueryParamData;
import com.piesat.school.dataclass.param.DataClassSaveParamData;
import com.piesat.school.dataclass.vto.DataClassVTO;
import com.piesat.school.emuerlation.BizEnumType;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

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
    public TailPage<DataClassVTO> pageDataClass(DataClassQueryParamData paramData) {
        QueryWrapper<Dataclass> wrapper = new QueryWrapper<Dataclass>().
                eq("id", paramData.getId()).
                eq(paramData.getStatus() != 0,"status", paramData.getStatus()).
                orderByDesc("updated_at");
//        Optional.ofNullable(paramData.getPointType()).ifPresent(type->{
//            wrapper.eq("point_type", type.getId());
//        });

        return null;
    }

    @Override
    public DataClassVTO saveDataClass(DataClassSaveParamData paramData) {
        Dataclass dataclass = new Dataclass();
        dataclass.setFirstClass(paramData.getFirstClass());
        dataclass.setSecClass(paramData.getSecClass());
        dataclass.setStatus(BizEnumType.CommonStatus.Valid.getKey());
        //dataclassMapper.insert(dataclass);
        this.save(dataclass);


        return DataClassBuilder.toDataClassVto(dataclass);
    }

    @Override
    public Boolean delDataClass(DataClassDelParamData paramData) {
        if(CollectionUtils.isEmpty(paramData.getIds()) && !paramData.isClear()){
            return Boolean.FALSE;
        }
        UpdateWrapper<Dataclass> wrapper = new UpdateWrapper<Dataclass>()
                .set("status", BizEnumType.CommonStatus.Invalid.getKey())
//                .eq("id", paramData.getUserId())
                .eq("status", BizEnumType.CommonStatus.Valid.getKey())
                .in(!paramData.isClear(), "id", paramData.getIds());
        return this.update(wrapper);
    }

    @Override
    public DataClassVTO updataDataClass(DataClassParamData paramData) {
        Dataclass dataclass = BizCommonValidateHelper.valdiateGetById(paramData.getId(),this);
        dataclass.setFirstClass(paramData.getFirstClass());
        dataclass.setSecClass(paramData.getSecClass());
        this.updateById(dataclass);
        return DataClassBuilder.toDataClassVto(dataclass);
    }

}
