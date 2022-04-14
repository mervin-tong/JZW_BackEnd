package com.piesat.school.biz.ds.topic.builder;

import com.piesat.school.biz.ds.topic.entity.Topic;
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
public class TopicBuilder {
    public static TopicVTO toTopicVto(Topic entity){

        if(entity == null) return null;

        TopicVTO vto = new TopicVTO();
        BeanUtils.copyProperties(entity, vto);
        return vto;
    }

    public static List<TopicVTO> toTopicVTOs(List<Topic> entitys){
        if(CollectionUtils.isEmpty(entitys)) return Collections.emptyList();
        List<TopicVTO> vtos = new ArrayList<>();
        entitys.forEach(entity->{
            vtos.add(toTopicVto(entity));
        });
        return vtos;

    }
}
