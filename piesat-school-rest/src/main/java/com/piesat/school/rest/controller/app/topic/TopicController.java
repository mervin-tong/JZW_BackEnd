package com.piesat.school.rest.controller.app.topic;

import com.piesat.school.topic.iservice.IRTopicService;
import com.piesat.school.topic.param.TopicDataAddParamData;
import com.piesat.school.topic.param.TopicDelParamData;
import com.piesat.school.topic.param.TopicQueryParamData;
import com.piesat.school.topic.param.TopicSaveParamData;
import com.piesat.school.topic.vto.TopicDetailVTO;
import com.piesat.school.topic.vto.TopicVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "专题数据模块")
@RestController
@RequestMapping("/app/topic")
public class TopicController {
    @DubboReference
    private IRTopicService irTopicService;
    @ApiOperation(value = "新增/修改数据专题")
    @PostMapping("/saveOrUpdate")
    public Result<TopicVTO> saveOrUpdate(TopicSaveParamData topicSaveParamData) {
        return irTopicService.saveOrUpdate(topicSaveParamData);
    }

    @ApiOperation(value = "删除专题")
    @PostMapping("/del")
    public Result<Boolean> del(TopicDelParamData paramData) {
        return irTopicService.del(paramData);
    }

    @ApiOperation(value = "添加专题数据")
    @PostMapping("/addTopicData")
    public Result<Boolean> addTopicData( TopicDataAddParamData paramData){
        return irTopicService.addTopicData(paramData);
    }

    @ApiOperation(value = "删除专题数据")
    @PostMapping("/delTopicData")
    @ApiImplicitParam(value = "id",name = "专题数据id",dataType = "Long")
    public Result<Boolean> delTopicData( Long id){
        return irTopicService.delTopicData(id);
    }
    @ApiOperation(value = "专题列表")
    @PostMapping("/list")
    public Result<TailPage<TopicVTO>> topicList(TopicQueryParamData paramData){
        return irTopicService.list(paramData);
    }
//    @ApiOperation(value = "专题数据列表")
//    @GetMapping("/dataList")
//    public Result<TailPage<TopicVTO>> dataList(TopicQueryParamData paramData){
//        return irTopicService.getAllTopic();
//    }
    @ApiOperation(value = "专题详情")
    @GetMapping("/detailTopic")
    public Result<List<TopicDetailVTO>> detailTopic(Long topicId){
        return irTopicService.detailTopic(topicId);
    }
    @ApiOperation(value = "首页显示专题详情")
    @GetMapping("/indexDetailTopic")
    public Result<TopicVTO> indexDetailTopic(Long topicId){
        return irTopicService.indexDetailTopic(topicId);
    }



}
