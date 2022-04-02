package com.piesat.school.rest.controller.app.datainf;

import com.baomidou.mybatisplus.extension.api.R;
import com.piesat.school.datainf.iservice.IRDataInfService;
import com.piesat.school.datainf.param.*;
import com.piesat.school.datainf.vto.DataInfDetailVTO;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datainf.vto.DataInfVTO;
import com.piesat.school.datainf.vto.MyDataInfVTO;
import com.piesat.school.rest.utils.FileDownloadUtils;
import com.piesat.school.rest.utils.FileUploadUtils;
import com.piesat.school.rest.utils.FileUtils;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
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
    @GetMapping("/dataMenu")
    public Result<TailPage<DataInfListVTO>> getAllDataInf(){return irDataInfService.getAllDatainf();}

    @ApiOperation(value = "新增数据")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/myDataMenu")
    public Result<TailPage<MyDataInfVTO>> myDataMenu(Long userId){
        return irDataInfService.myDataMenu(userId);
    }
    @ApiOperation(value = "我的数据")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/save")
    public Result<DataInfVTO> saveDataInf(@RequestBody DataInfSaveParamData paramData){
        return irDataInfService.saveDataInf(paramData);
    }
    @ApiOperation(value = "逻辑删除数据")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/del")
    public Result<Boolean> delDataInf(@RequestParam(value = "dataId",required = true)String dataId,Long userId){
        return irDataInfService.delDataInf(dataId,userId);
    }
    @ApiOperation(value = "根据关键词返回数据列表")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/keySearch")
    public Result<TailPage<DataInfListVTO>> searchByKeyword(@RequestBody SearchByKeyParamData searchByKeyParamData){
        return irDataInfService.searchByKeyword(searchByKeyParamData);
    }
    @ApiOperation(value = "根据类名返回数据列表")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/classSearch")

    public Result<TailPage<DataInfListVTO>> searchByClass(@RequestBody SearchByClassParamData searchByClassParamData){
        return irDataInfService.searchByClass(searchByClassParamData);
    }
    @ApiOperation(value = "根据时间范围返回数据列表")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/timeSearch")
    public Result<TailPage<DataInfListVTO>> searchByTime(@RequestBody SearchByTimeParamData searchByTimeParamData){
        return irDataInfService.searchByTime(searchByTimeParamData);
    }
    @ApiOperation(value = "混合搜索返回数据列表")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/allSearch")
    public Result<TailPage<DataInfListVTO>> searchAll(@RequestBody SearchAllParamData searchAllParamData){
        return irDataInfService.searchAll(searchAllParamData);
    }
    @ApiOperation(value = "上传文件")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/uploadData")
    public Result<Boolean> uploadDataInf(MultipartFile file, @RequestParam(value = "dataId",required = true) Long dataId) throws Exception {
        String fileLocation = FileUploadUtils.upload(file);
        String amount = FileUtils.getAmount(file.getSize());
        return irDataInfService.uploadDataInf(fileLocation,amount,dataId);
    }
    @ApiOperation(value = "上传缩略图")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/uploadPic")
    public Result<Boolean> uploadPic(MultipartFile picture, Long dataId) throws IOException {
        String pic = FileUploadUtils.uploadPicture(picture);
        return irDataInfService.uploadPic(pic, dataId);
    }
    @ApiOperation(value = "数据详情")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @GetMapping("/dataInfDetail")
    public Result<DataInfDetailVTO> dataInfDetail(@RequestParam(value = "dataId",required = true) Long dataId){
        return irDataInfService.dataInfDetailVTO(dataId);
    }
    Boolean isAddDownCount;

    @ApiOperation(value = "文件下载")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @GetMapping("/download")
    public Result<Boolean> Download(Long dataId,HttpServletResponse response,Long userId) throws IOException {

        DataInfVTO datainf = irDataInfService.getFilePath(dataId);
        Boolean isDownload = FileDownloadUtils.download(datainf,response);


        if (isDownload){
            irDataInfService.addhistory(dataId,userId);
            isAddDownCount = irDataInfService.addDownCount(datainf.getDownCount()+1,dataId);
        }
        return Result.ofSuccess(isAddDownCount);
    }
}
