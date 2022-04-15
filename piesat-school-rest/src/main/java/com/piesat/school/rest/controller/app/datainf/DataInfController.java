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
import io.swagger.annotations.*;
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
@RequestMapping("/app/dataInf")
public class DataInfController{
    @DubboReference
    private IRDataInfService irDataInfService;
//    @Secured("ROLE_ADMIN")
    @GetMapping("/dataMenu")
    public Result<TailPage<DataInfListVTO>> getAllDataInf(){return irDataInfService.getAllDatainf();}


    @ApiOperation(value = "会员数据列表")
    @PostMapping("/dataList")
    public Result<TailPage<MyDataInfVTO>> dataList(DataQueryParamData paramData){
        return irDataInfService.dataList(paramData);
    }

    @ApiOperation(value = "新增/修改数据")
    @PostMapping("/save")
    public Result<DataInfVTO> saveDataInf(DataInfSaveParamData paramData){
        return irDataInfService.saveDataInf(paramData);
    }
    @ApiOperation(value = "逻辑删除数据")
    @PostMapping("/del")
    @ApiImplicitParam(value = "dataIds",name = "数据id，逗号分割")
    public Result<Boolean> delDataInf(@RequestParam(value = "dataId",required = true)String dataIds){
        return irDataInfService.delDataInf(dataIds);
    }
    @ApiOperation(value = "根据关键词返回数据列表")
    @PostMapping("/keySearch")
    public Result<TailPage<DataInfListVTO>> searchByKeyword(SearchByKeyParamData searchByKeyParamData){
        return irDataInfService.searchByKeyword(searchByKeyParamData);
    }
    @ApiOperation(value = "根据类名返回数据列表")
    @PostMapping("/classSearch")
    public Result<TailPage<DataInfListVTO>> searchByClass(@RequestBody SearchByClassParamData searchByClassParamData){
        return irDataInfService.searchByClass(searchByClassParamData);
    }
    @ApiOperation(value = "根据时间范围返回数据列表")
    @PostMapping("/timeSearch")
    public Result<TailPage<DataInfListVTO>> searchByTime(@RequestBody SearchByTimeParamData searchByTimeParamData){
        return irDataInfService.searchByTime(searchByTimeParamData);
    }
    @ApiOperation(value = "混合搜索返回数据列表")
    @PostMapping("/allSearch")
    public Result<TailPage<DataInfListVTO>> searchAll(@RequestBody SearchAllParamData searchAllParamData){
        return irDataInfService.searchAll(searchAllParamData);
    }

//    @ApiOperation(value = "上传缩略图")
//    @ApiResponses({
//            @ApiResponse(code=0,message="访问成功"),
//            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
//            @ApiResponse(code=500,message="后台报错"),
//    })
//    @PostMapping("/uploadPic")
//    public Result<Boolean> uploadPic(MultipartFile picture, Long dataId) throws IOException {
//        String pic = FileUploadUtils.uploadPicture(picture);
//        return irDataInfService.uploadPic(pic, dataId);
//    }
    @ApiOperation(value = "数据详情")
    @PostMapping("/dataInfDetail")
    public Result<DataInfDetailVTO> dataInfDetail(DataDetailParamData paramData){
        return irDataInfService.dataInfDetailVTO(paramData);
    }
    Boolean isAddDownCount;

    @ApiOperation(value = "文件下载")
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
