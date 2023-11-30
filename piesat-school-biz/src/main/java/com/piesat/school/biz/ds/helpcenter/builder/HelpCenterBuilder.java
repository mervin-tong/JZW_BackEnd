package com.piesat.school.biz.ds.helpcenter.builder;

import com.piesat.school.biz.ds.helpcenter.entity.HelpCenter;
import com.piesat.school.biz.ds.topic.entity.Topic;
import com.piesat.school.helpcenter.vto.HelpCenterVTO;
import com.piesat.school.topic.vto.TopicVTO;
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
 * @since 2022-03-09
 */
public class HelpCenterBuilder {
    public static HelpCenterVTO toVTO (HelpCenter helpCenter){
        if(helpCenter == null) {
            return null;
        }
        HelpCenterVTO vto = new HelpCenterVTO();
        BeanUtils.copyProperties(helpCenter, vto);
        vto.setId(helpCenter.getId().toString());
        return vto;
    }

    public static List<HelpCenterVTO> toHelpCenterVTOs(List<HelpCenter> entitys){
        if(CollectionUtils.isEmpty(entitys)) return Collections.emptyList();
        List<HelpCenterVTO> vtos = new ArrayList<>();
        entitys.forEach(entity->{
            vtos.add(toVTO(entity));
        });
        return vtos;

    }
}
