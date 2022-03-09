package com.piesat.school.rest.controller.app.topicdata;

import com.piesat.school.rest.utils.FileUploadUtils;
import com.piesat.school.topicdata.iservice.IRTopicDataService;
import com.piesat.school.topicdata.param.TopicDataSaveParamData;
import com.piesat.school.topicdata.vto.TopicDataVTO;
import com.smartwork.api.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    @ApiOperation(value = "上传专题图片")
    public Result<Boolean> uploadTopicPicture(MultipartFile file, Long id){
        return null;
    }
    @ApiOperation(value = "添加专题数据")
    @PostMapping("/addTopicData")
    public Result<Boolean> addTopicData(Long dataId, Long topicId){
        return irTopicDataService.addTopicData(dataId,topicId);
    }
}
