package com.piesat.school.biz.ds.dataClass.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.piesat.school.biz.ds.dataClass.entity.DataClass;
import com.piesat.school.biz.ds.dataClass.mapper.DataClassMapper;
import com.piesat.school.biz.ds.dataClass.service.IDataClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.dataClass.VTO.DataClassVTO;
import com.piesat.school.dataClass.param.DataClassParam;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lapaus
 * @since 2022-04-13
 */
@Service
public class DataClassServiceImpl extends ServiceImpl<DataClassMapper, DataClass> implements IDataClassService {

    @Override
    public Boolean saveDataClassification(DataClassParam param) {
        int i =this.count();
        DataClass dataClass =new DataClass();
        param.setCreatedAt(new Date());
        param.setId(i+1);
        BeanUtils.copyProperties(param, dataClass);
        return  this.save(dataClass);
    }

    @Override
    public Boolean deleteDataClassification(Integer id) {
        return this.update(new UpdateWrapper<DataClass>().eq("id", id).set("status", 1));
    }

    @Override
    public List<DataClassVTO> dataClassList(Integer id) {
        List<DataClassVTO> results =new ArrayList<>();
        if(id !=null) {
            DataClass dataClasses = baseMapper.selectById(id);
            DataClassVTO dataClassVTO = new DataClassVTO();
            BeanUtils.copyProperties(dataClasses, dataClassVTO);
            results.add(dataClassVTO);
        }else {
            //id为空查全部
            List<DataClass> dataClasses =this.list();
            List<DataClassVTO> dataClassVTOList =new ArrayList<>();
            //vto转换
            for(DataClass dataClass:dataClasses){
                DataClassVTO dataClassVTO = new DataClassVTO();
                BeanUtils.copyProperties(dataClass, dataClassVTO);
                dataClassVTOList.add(dataClassVTO);
            }
            //组装树形结构
            for(DataClassVTO dataClassVTO:dataClassVTOList){
                if(dataClassVTO.getParentId()==0){
                    dataClassVTO.setChuildrenList(getChildMenu(dataClassVTO.getId(),dataClassVTOList));
                    results.add(dataClassVTO);
                }
            }
        }
        return results;
    }

    @Override
    public Boolean updateDataClassification(List<String> param) {
        return null;
    }

    private List<DataClassVTO> getChildMenu(Integer parentId,List<DataClassVTO> dataClasses){
        List<DataClassVTO> childList = new ArrayList<>();

        for(DataClassVTO dataClass :dataClasses){
            if(dataClass.getParentId()!=null){
                if(dataClass.getParentId().equals(parentId)){
                    childList.add(dataClass);
                }
            }
        }
        for(DataClassVTO dataClass:childList){
            dataClass.setChuildrenList(getChildMenu(dataClass.getId(),dataClasses));
        }
        if (childList.size() ==0){
            return null;
        }
        return childList;
    }
}
