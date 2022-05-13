package com.piesat.school.biz.ds.generationmode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.biz.ds.generationmode.entity.GenerationMode;
import com.piesat.school.biz.ds.generationmode.mapper.GenerationModeMapper;
import com.piesat.school.biz.ds.generationmode.service.IGenerationModeService;
import com.piesat.school.generationMode.param.GenerationModeParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class GenerationModeServiceImpl extends ServiceImpl<GenerationModeMapper, GenerationMode> implements IGenerationModeService {

    @Override
    public Boolean saveGeneration(GenerationModeParam param) {
        GenerationMode generationMode =new GenerationMode();
        int i=0;
        if(param.getId()!=null){
            BeanUtils.copyProperties(param, generationMode);
            i =baseMapper.updateById(generationMode);
        }else {
            generationMode.setGenerationMode(param.getGenerationMode());
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

}
