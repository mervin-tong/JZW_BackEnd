package com.piesat.school.provider.serv.topicdata;

import com.piesat.school.biz.ds.datainf.service.IDatainfService;
import com.piesat.school.biz.ds.topicdata.service.ITopicDataService;
import com.piesat.school.topicdata.iservice.IRTopicDataService;
import com.piesat.school.topicdata.param.TopicDataSaveParamData;
import com.piesat.school.topicdata.vto.TopicDataVTO;
import com.smartwork.api.Result;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class RTopicDataService implements IRTopicDataService {
    @Autowired
    ITopicDataService iTopicDataService;
    @Autowired
    IDatainfService iDatainfService;
    @Override
    public Result<TopicDataVTO> saveTopic(TopicDataSaveParamData topicDataSaveParamData) {
        return Result.ofSuccess(iTopicDataService.saveTopic(topicDataSaveParamData));
    }

    @Override
    public Result<Boolean> addTopicData(Long topicId, Long dataId) {
        return Result.ofSuccess(iDatainfService.addTopicData(topicId,dataId));
    }
}
