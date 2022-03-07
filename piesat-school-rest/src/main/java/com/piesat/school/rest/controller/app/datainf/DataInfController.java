package com.piesat.school.rest.controller.app.datainf;

import com.baomidou.mybatisplus.extension.api.R;
import com.piesat.school.datainf.iservice.IRDataInfService;
import com.piesat.school.datainf.param.DataInfSaveParamData;
import com.piesat.school.datainf.param.SearchByClassParamData;
import com.piesat.school.datainf.param.SearchByKeyParamData;
import com.piesat.school.datainf.vto.DataInfDetailVTO;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datainf.vto.DataInfVTO;
import com.piesat.school.rest.utils.FileDownloadUtils;
import com.piesat.school.rest.utils.FileUploadUtils;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.mapstruct.Context;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Api(tags = "数据信息模块")
@RestController
@RequestMapping("/app/datainf")
public class DataInfController{
    @DubboReference
    private IRDataInfService irDataInfService;
//    @Secured("ROLE_ADMIN")
    @GetMapping("/datamenu")
    public Result<List<DataInfVTO>> getAllDataInf(){return irDataInfService.getAllDatainf();}
    @ApiOperation(value = "新增数据")
    @PostMapping("/save")
    public Result<DataInfVTO> saveDataInf(@RequestBody DataInfSaveParamData paramData){
        return irDataInfService.saveDataInf(paramData);
    }
    @ApiOperation(value = "根据关键词返回数据列表")
    @PostMapping("/keySearch")
    public Result<TailPage<DataInfListVTO>> searchByKeyword(@RequestBody SearchByKeyParamData searchByKeyParamData){
        return irDataInfService.searchByKeyword(searchByKeyParamData);
    }
    @ApiOperation(value = "根据类名返回数据列表")
    @PostMapping("/classSearch")
    public Result<TailPage<DataInfListVTO>> searchByClass(@RequestBody SearchByClassParamData searchByClassParamData){
        return irDataInfService.searchByClass(searchByClassParamData);
    }
    @ApiOperation(value = "上传文件")
    @PostMapping("/uploadData")
    public Result<DataInfVTO> uploadDataInf(MultipartFile file, Long dataid) throws Exception {
        String fileLocation = FileUploadUtils.upload(file);
        return irDataInfService.uploadDataInf(fileLocation,dataid);
    }
    @ApiOperation(value = "数据详情")
    @GetMapping("/dataInfDetail")
    public Result<DataInfDetailVTO> dataInfDetail(Long dataInfId){
        return irDataInfService.dataInfDetailVTO(dataInfId);
    }

    @ApiOperation(value = "文件下载")
    @GetMapping("/download")
    public Result<Boolean> Download(Long dataId,HttpServletResponse response) throws IOException {
    
        DataInfVTO datainf = irDataInfService.getFilePath(dataId);
        Boolean isDownload = FileDownloadUtils.download(datainf,response);
        Boolean isAddDownCount = null;
        if (isDownload){
            irDataInfService.addDownCount(datainf.getDownCount()+1,dataId);
        }
        return Result.ofSuccess(isAddDownCount);
    }
}
