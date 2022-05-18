package com.piesat.school.biz.ds.generationmode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.biz.ds.generationmode.entity.GenerationMode;
import com.piesat.school.generationMode.param.GenerationModeParam;

public interface IGenerationModeService  extends IService<GenerationMode> {
    Boolean saveGeneration(GenerationModeParam param);

    Boolean deleteGenerationMode(Integer id);

    Boolean mergeGenerationMode(String ids, String name);
}
