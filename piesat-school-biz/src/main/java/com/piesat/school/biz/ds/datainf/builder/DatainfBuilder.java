package com.piesat.school.biz.ds.datainf.builder;

import com.piesat.school.biz.ds.dataclass.entity.Dataclass;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.piesat.school.dataclass.vto.DataClassVTO;
import com.piesat.school.datainf.vto.DataInfVTO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatainfBuilder {
    public static DataInfVTO toDataInfVto(Datainf entity){

        if(entity == null) return null;

        DataInfVTO vto = new DataInfVTO();
        BeanUtils.copyProperties(entity, vto);
        return vto;
    }

    public static List<DataInfVTO> toDataInfVtos(List<Datainf> entitys){
        if(CollectionUtils.isEmpty(entitys)) return Collections.emptyList();
        List<DataInfVTO> vtos = new ArrayList<>();
        entitys.forEach(entity->{
            vtos.add(toDataInfVto(entity));
        });
        return vtos;

    }
}
