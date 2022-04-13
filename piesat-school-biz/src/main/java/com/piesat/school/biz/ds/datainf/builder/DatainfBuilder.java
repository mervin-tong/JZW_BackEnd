package com.piesat.school.biz.ds.datainf.builder;

import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.piesat.school.biz.ds.user.entity.User;
import com.piesat.school.datainf.vto.DataInfVTO;
import com.piesat.school.datainf.vto.MyDataInfVTO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public static List<MyDataInfVTO> toMyDataInfVTOs(List<Datainf> entitys,List<User> users){
        if(CollectionUtils.isEmpty(entitys)) return Collections.emptyList();
        Map<Long,String> userMap=users.stream().collect(Collectors.toMap(User::getId,User::getName,(key1, key2)->key2));
        List<MyDataInfVTO> vtos = new ArrayList<>();
        entitys.forEach(entity->{
            MyDataInfVTO vto=new MyDataInfVTO();
            BeanUtils.copyProperties(entity,vto);
            vto.setUploadUserName(userMap.get(entity.getUploadUserId()));
            vtos.add(vto);
        });
        return vtos;

    }

}
