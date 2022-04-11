package com.piesat.school.biz.ds.information.builder;

import com.piesat.school.biz.ds.information.entity.Information;
import com.piesat.school.biz.ds.user.entity.User;
import com.piesat.school.information.vto.InformationVTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InformationBuilder {
    public static List<InformationVTO> toVTO(List<Information> records, List<User> users) {
        List<InformationVTO> informationVTOS=new ArrayList<>();
        Map<Long,String> userMap=users.stream().collect(Collectors.toMap(User::getId,User::getName,(key1,key2)->key2));
        for(Information i:records){
            InformationVTO vto=new InformationVTO();
            BeanUtils.copyProperties(i,vto);
            vto.setPublisherName(userMap.get(i.getPublisher()));
            informationVTOS.add(vto);
        }
        return  informationVTOS;
    }
}
