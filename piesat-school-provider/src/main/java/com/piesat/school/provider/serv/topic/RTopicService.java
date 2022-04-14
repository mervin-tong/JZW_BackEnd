package com.piesat.school.provider.serv.topic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.information.entity.Information;
import com.piesat.school.biz.ds.topic.service.ITopicService;
import com.piesat.school.topic.iservice.IRTopicService;
import com.piesat.school.topic.param.TopicDataAddParamData;
import com.piesat.school.topic.param.TopicDelParamData;
import com.piesat.school.topic.param.TopicQueryParamData;
import com.piesat.school.topic.param.TopicSaveParamData;
import com.piesat.school.topic.vto.TopicDetailVTO;
import com.piesat.school.topic.vto.TopicVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

@DubboService
public class RTopicService implements IRTopicService {
    @Resource
    ITopicService iTopicService;
    @Override
    public Result<TopicVTO> saveOrUpdate(TopicSaveParamData topicSaveParamData) {
        return Result.ofSuccess(iTopicService.saveOrUpdate(topicSaveParamData));
    }

    @Override
    public Result<Boolean> addTopicData(TopicDataAddParamData paramData) {
        return Result.ofSuccess(iTopicService.addTopicData(paramData));
    }

    @Override
    public Result<List<TopicDetailVTO>> detailTopic(Long topicId) {
        return Result.ofSuccess(iTopicService.detailTopic(topicId));
    }

    @Override
    public Result<List<TopicVTO>> getAllTopic() {
        return Result.ofSuccess(iTopicService.getAllTopic());
    }

    @Override
    public Result<TopicVTO> indexDetailTopic(Long topicId) {
        return Result.ofSuccess(iTopicService.indexDetailTopic(topicId));
    }

    @Override
    public Result<Boolean> del(TopicDelParamData paramData) {
        return Result.ofSuccess(iTopicService.del(paramData));
    }

    @Override
    public Result<Boolean> delTopicData(Long id) {
        return Result.ofSuccess(iTopicService.delTopicData(id));
    }

    @Override
    public Result<TailPage<TopicVTO>> list(TopicQueryParamData paramData) {
        return Result.ofSuccess(iTopicService.topicPage(paramData));
    }
}
