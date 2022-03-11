package com.piesat.school.rest.controller.app.topicdata;

import com.piesat.school.rest.utils.FileUploadUtils;
import com.piesat.school.topicdata.iservice.IRTopicDataService;
import com.piesat.school.topicdata.param.TopicDataSaveParamData;
import com.piesat.school.topicdata.vto.TopicDataDetailVTO;
import com.piesat.school.topicdata.vto.TopicDataVTO;
import com.smartwork.api.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api(tags = "专题数据模块")
@RestController
@RequestMapping("/app/topicdata")
public class TopicDataController {
    @DubboReference
    private IRTopicDataService irTopicDataService;
    @ApiOperation(value = "新增数据专题")
    @PostMapping("/saveTopic")
    public Result<TopicDataVTO> saveTopic(@RequestBody TopicDataSaveParamData topicDataSaveParamData){
        return irTopicDataService.saveTopic(topicDataSaveParamData);
    }
//    @ApiOperation(value = "上传专题图片")
//    public Result<Boolean> uploadTopicPicture(MultipartFile file, Long id){
//        return null;
//    }
    @ApiOperation(value = "添加专题数据")
    @PostMapping("/addTopicData")
    public Result<Boolean> addTopicData( Long topicId, Long dataId){
        return irTopicDataService.addTopicData(topicId,dataId);
    }
    @ApiOperation(value = "删除专题数据")
    @PostMapping("/delTopicData")
    public Result<Boolean> delTopicData(Long topicId, Long dataId){
        return irTopicDataService.delTopicData(topicId, dataId);
    }
    @ApiOperation(value = "专题详情")
    @GetMapping("/detailTopic")
    public Result<List<TopicDataDetailVTO>> detailTopic(Long topicId){
        return irTopicDataService.detailTopic(topicId);
    }
    @ApiOperation(value = "首页显示专题详情")
    @GetMapping("/indexDetailTopic")
    public Result<TopicDataVTO> indexDetailTopic(Long topicId){
        return irTopicDataService.indexDetailTopic(topicId);
    }
    @ApiOperation(value = "专题列表")
    @GetMapping("/menu")
    public Result<List<TopicDataVTO>> getAllTopic(){
        return irTopicDataService.getAllTopic();
    }

}
