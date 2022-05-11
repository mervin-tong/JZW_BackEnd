package com.piesat.school.biz.ds.generationmode.builder;

import com.piesat.school.biz.ds.generationmode.entity.GenerationMode;
import com.piesat.school.generationMode.vto.GenerationModeVTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class GenerationBuilder {
    public static List<GenerationModeVTO> toVTO(List<GenerationMode> generationModes){
        List<GenerationModeVTO> generationModeVTOS=new ArrayList<>();
        for(GenerationMode generationMode:generationModes){
            GenerationModeVTO generationModeVTO =new GenerationModeVTO();
            BeanUtils.copyProperties(generationMode,generationModeVTO);
            generationModeVTOS.add(generationModeVTO);
        }
        return generationModeVTOS;
    }
}
