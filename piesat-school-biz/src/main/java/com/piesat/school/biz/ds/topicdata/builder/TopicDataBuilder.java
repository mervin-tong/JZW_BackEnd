package com.piesat.school.biz.ds.topicdata.builder;

import com.piesat.school.biz.ds.topicdata.entity.TopicData;
import com.piesat.school.topicdata.vto.TopicDataVTO;
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
public class TopicDataBuilder {
    public static TopicDataVTO toTopicDataVto(TopicData entity){

        if(entity == null) return null;

        TopicDataVTO vto = new TopicDataVTO();
        BeanUtils.copyProperties(entity, vto);
        return vto;
    }

    public static List<TopicDataVTO> toTopicDataVtos(List<TopicData> entitys){
        if(CollectionUtils.isEmpty(entitys)) return Collections.emptyList();
        List<TopicDataVTO> vtos = new ArrayList<>();
        entitys.forEach(entity->{
            vtos.add(toTopicDataVto(entity));
        });
        return vtos;

    }
}
