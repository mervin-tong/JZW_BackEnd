package com.piesat.school.biz.ds.dataclass.bulider;

import com.piesat.school.biz.ds.dataclass.entity.Dataclass;
import com.piesat.school.dataclass.vto.DataClassVTO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 实体类转vto类
 * </p>
 *
 * @author 周悦尧
 * @since 2022-01-26
 */
public class DataClassBuilder {
    public static DataClassVTO toDataClassVto(Dataclass entity){

        if(entity == null) return null;

        DataClassVTO vto = new DataClassVTO();
        BeanUtils.copyProperties(entity, vto);
        return vto;
    }

    public static List<DataClassVTO> toDataClassVtos(List<Dataclass> entitys){
        if(CollectionUtils.isEmpty(entitys)) return Collections.emptyList();
        List<DataClassVTO> vtos = new ArrayList<>();
        entitys.forEach(entity->{
            vtos.add(toDataClassVto(entity));
        });
        return vtos;

    }
}
