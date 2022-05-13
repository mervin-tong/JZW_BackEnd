package com.piesat.school.biz.ds.dataClass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.base.PageQueryParamData;
import com.piesat.school.biz.ds.dataClass.entity.DataClass;
import com.piesat.school.biz.ds.dataClass.mapper.DataClassMapper;
import com.piesat.school.biz.ds.dataClass.service.IDataClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.piesat.school.biz.ds.datainf.mapper.DatainfMapper;
import com.piesat.school.biz.ds.datainf.service.IDatainfService;
import com.piesat.school.dataClass.VTO.DataClassVTO;
import com.piesat.school.dataClass.param.DataClassParam;
import com.piesat.school.datainf.param.MenuDataParam;
import com.piesat.school.datainf.vto.DataInfDetailVTO;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.smartwork.api.param.ParamData;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    private IDatainfService datainfService;
    @Resource
    private DatainfMapper datainfMapper;

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
        List<DataClass> dataClasses =new ArrayList<>();
        for (int i=0;i<param.size();i++){
            DataClass dataClass =new DataClass();
            dataClass.setId(Integer.parseInt(param.get(i).split(",")[0]));
            dataClass.setOrderNumber(Integer.parseInt(param.get(i).split(",")[1]));
            dataClasses.add(dataClass);
        }
        return baseMapper.updateDataClassification(dataClasses);
    }

    @Override
    public Boolean mergeFirstClass(String ids, DataClassParam param) {
        //新建分类
        DataClass dataClass=new DataClass();
        BeanUtils.copyProperties(param, dataClass);
        dataClass.setId(this.count()+1);
        dataClass.setParentId(0);
        dataClass.setLevel(1);
        dataClass.setStatus(0);
        dataClass.setCreatedAt(new Date());

        //更改原分类下二次菜单的parent_id
        List<Integer> id = Arrays.stream(ids.split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        List<DataClass> dataClasses=baseMapper.selectList(new QueryWrapper<DataClass>().in("parent_id", id));
        dataClasses.forEach(e->{
            e.setParentId(dataClass.getId());
        });
        Boolean a=this.updateBatchById(dataClasses);
        //逻辑删除原一级分类
        this.update(new UpdateWrapper<DataClass>().set("status", 1).in("id", id));
        //更改原一级分类所属数据
        List<Datainf> datainfs=datainfService.list(new QueryWrapper<Datainf>().in("first_class", id));
        datainfs.forEach(infs->{
            infs.setFirstClass(String.valueOf(dataClass.getId()));
        });
        Boolean b = datainfService.updateBatchById(datainfs);

        Boolean c=this.save(dataClass);
        return a && b && c ;
    }

    @Override
    public Boolean mergeSecClass(String ids, DataClassParam param) {
        //新建分类
        DataClass dataClass=new DataClass();
        BeanUtils.copyProperties(param, dataClass);
        dataClass.setId(this.count()+1);
        dataClass.setLevel(2);
        dataClass.setStatus(0);
        dataClass.setCreatedAt(new Date());

        List<Integer> id = Arrays.stream(ids.split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        Boolean a=this.update(new UpdateWrapper<DataClass>().set("status", 1).in("id", id));

        List<Datainf> datainfs=datainfService.list(new QueryWrapper<Datainf>().in("sec_class", id));
        datainfs.forEach(infs->{
            infs.setSecClass(String.valueOf(dataClass.getId()));
        });
        Boolean b = datainfService.updateBatchById(datainfs);
        Boolean c=this.save(dataClass);
        return a && b && c;
    }

    @Override
    public TailPage<DataInfDetailVTO> queryClassData(Integer firstClass, Integer secClass, PageQueryParamData param) {
        MenuDataParam menuDataParam=new MenuDataParam();
        menuDataParam.setFirstClass(String.valueOf(firstClass));
        menuDataParam.setSecClass(String.valueOf(secClass));
        Page<DataInfListVTO> page = new Page<>(param.getPn(),param.getPs());
        page.setOptimizeCountSql(false);
        List<DataInfDetailVTO> dataInfDetailVTOS = datainfMapper.menuDataList(menuDataParam,page);
        TailPage<DataInfDetailVTO> e = CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),dataInfDetailVTOS);
        return e;
    }

    @Override
    public Boolean moveData(Long id, Integer firstClass, Integer secClass) {
        Datainf datainf=datainfService.getById(id);
        DataClass formDataClass = this.getById(datainf.getFirstClass());
        DataClass formDataClass2 = this.getById(datainf.getSecClass());
        formDataClass.setDataNum(formDataClass.getDataNum()-1);
        formDataClass2.setDataNum(formDataClass2.getDataNum()-1);
        this.updateById(formDataClass);
        this.updateById(formDataClass2);
        datainf.setFirstClass(String.valueOf(firstClass));
        datainf.setSecClass(String.valueOf(secClass));
        return datainfService.updateById(datainf);
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
