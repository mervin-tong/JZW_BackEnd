package com.piesat.school.biz.ds.generationmode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.piesat.school.biz.ds.datainf.mapper.DatainfMapper;
import com.piesat.school.biz.ds.datainf.service.IDatainfService;
import com.piesat.school.biz.ds.generationmode.entity.GenerationMode;
import com.piesat.school.biz.ds.generationmode.mapper.GenerationModeMapper;
import com.piesat.school.biz.ds.generationmode.service.IGenerationModeService;
import com.piesat.school.generationMode.param.GenerationModeParam;
import com.piesat.school.generationMode.vto.GenerationModeVTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenerationModeServiceImpl extends ServiceImpl<GenerationModeMapper, GenerationMode> implements IGenerationModeService {

    @Resource
    private IDatainfService iDatainfService;
    @Resource
    private DatainfMapper datainfMapper;

    @Override
    public Boolean saveGeneration(GenerationModeParam param) {
        GenerationMode generationMode =new GenerationMode();
        int i=0;
        if(param.getId()!=null){
            BeanUtils.copyProperties(param, generationMode);
            i =baseMapper.updateById(generationMode);
        }else {
            generationMode.setComment(param.getComment());
            i=baseMapper.insert(generationMode);
        }
        if(i==0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Boolean deleteGenerationMode(Integer id) {
        return baseMapper.deleteById(id) != 0;
    }

    @Override
    public Boolean mergeGenerationMode(String ids, String name) {
        List<Integer> id = Arrays.stream(ids.split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        this.removeByIds(id);
        GenerationMode generationMode = new GenerationMode();
        generationMode.setComment(name);
        this.saveOrUpdate(generationMode);
        generationMode=this.getOne(new QueryWrapper<GenerationMode>().eq("comment",name));
        List<Datainf> datainfs=iDatainfService.list(new QueryWrapper<Datainf>().in("generation_mode", id));
        for(Datainf datainf:datainfs){
            datainf.setGenerationMode(Math.toIntExact(generationMode.getId()));
        }
        return iDatainfService.updateBatchById(datainfs);
    }

    @Override
    public List<GenerationModeVTO> getDetail() {
        List<GenerationModeVTO> generationModes=datainfMapper.getGenerationModeDetail();
        generationModes.forEach(e-> {
            if(e.getNumber()==null)
                e.setNumber(0);
                }
        );
        return generationModes ;
    }

}
