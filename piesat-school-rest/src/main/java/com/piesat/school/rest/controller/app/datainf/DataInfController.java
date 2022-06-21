package com.piesat.school.rest.controller.app.datainf;

import com.piesat.school.base.PageQueryParamData;
import com.piesat.school.dataClass.VTO.DataClassVTO;
import com.piesat.school.dataClass.param.DataClassParam;
import com.piesat.school.datainf.iservice.IRDataInfService;
import com.piesat.school.datainf.param.*;
import com.piesat.school.datainf.vto.*;
import com.piesat.school.generationMode.param.GenerationModeParam;
import com.piesat.school.generationMode.vto.GenerationModeVTO;
import com.piesat.school.rest.aspect.AroundRecord;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

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

    @AroundRecord
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

//    @ApiOperation(value = "文件下载")
//    @GetMapping("/download")
//    public Result<String> Download(Long dataId) throws IOException {
////
////        DataInfVTO datainf = irDataInfService.getFilePath(dataId);
////        Boolean isDownload = FileDownloadUtils.download(datainf,response);
////
////        if (isDownload){
////            irDataInfService.addhistory(dataId,userId);
////            isAddDownCount = irDataInfService.addDownCount(datainf.getDownCount()+1,dataId);
////        }
//        DataInfVTO datainf = irDataInfService.getFilePath(dataId,null);
//        return Result.ofSuccess(datainf.getContent());
//    }

    @ApiOperation(value = "文件下载")
    @GetMapping("/historyDownload")
    public Result<String> historyDownload(Long dataId,Long userId,Long id) throws IOException {
        DataInfVTO datainf = irDataInfService.getFilePath(dataId,userId,id);
        if(datainf!=null) {
            return Result.ofSuccess(datainf.getContent());
        }else {
            return null;
        }
    }

    @ApiOperation(value = "获取元数据")
    @PostMapping("/thematicData")
    public Result<TailPage<DataInfListVTO>> thematicData(MetadataQueryParam paramData){
        return irDataInfService.thematicData(paramData);
    }

    @ApiOperation(value = "数据数量统计")
    @GetMapping("/statistics")
    public Result<StatisticsVTO> statistics(){
        return irDataInfService.statistics();
    }

    @ApiOperation(value = "热度数据")
    @PostMapping("/highAttention")
    public Result<TailPage<DataInfListVTO>> highAttention(@RequestBody PageQueryParamData paramData){
        return irDataInfService.highAttention(paramData);
    }

    @ApiOperation(value = "最新数据")
    @GetMapping("/upToDateAttention")
    public Result<TailPage<DataInfListVTO>> upToDateAttention(PageQueryParamData paramData){
        return irDataInfService.upToDateAttention(paramData);
    }

    @ApiOperation(value = "新增/修改生成方式")
    @PostMapping("/saveGenerationMode")
    public Result<Boolean> saveGenerationMode(GenerationModeParam param){
        return irDataInfService.saveGeneration(param);
    }

    @ApiOperation(value = "合并生成方式")
    @PostMapping("/mergeGenerationMode")
    public Result<Boolean> mergeGenerationMode(@RequestParam(value = "ids")String ids, @RequestParam(value = "name")String name ){
        return irDataInfService.mergeGenerationMode(ids,name);
    }

    @ApiOperation(value = "删除生成方式")
    @GetMapping("/deleteGenerationMode")
    public Result<Boolean> deleteGenerationMode(Integer id){
        return irDataInfService.deleteGenerationMode(id);
    }

    @ApiOperation(value = "生成方式详情")
    @GetMapping("/generationModeDetail")
    public Result<List<GenerationModeVTO>> generationModeDetail(){
        return irDataInfService.generationModeDetail();
    }

    @ApiOperation(value = "根据菜单返回数据列表")
    @PostMapping("/menuDataList")
    public Result<TailPage<DataInfDetailVTO>> menuDataList(@RequestBody MenuDataParam param){
        return irDataInfService.menuDataList(param);
    }

    @ApiOperation(value = "首页展示")
    @PostMapping("/firstPage")
    public Result<TailPage<FirstPageVTO>> firstPage(@RequestBody MenuDataParam param){
        return irDataInfService.firstPage(param);
    }

    @ApiOperation(value = "新增数据分类")
    @PostMapping("/saveDataClassification")
    public Result<Boolean> saveDataClassification(@RequestBody DataClassParam param){
        return irDataInfService.saveDataClassification(param);
    }

    @ApiOperation(value = "修改数据分类排序")
    @PostMapping("/updateDataClassification")
    public Result<Boolean> updateDataClassification(@RequestBody List<String> param){
        return irDataInfService.updateDataClassification(param);
    }

    @ApiOperation(value = "修改数据分类内容")
    @PostMapping("/updateDataClassInfo")
    public Result<Boolean> updateDataClassInfo(@RequestBody DataClassParam param){
        return irDataInfService.updateDataClassInfo(param);
    }

    @ApiOperation(value = "删除分类")
    @GetMapping("/deleteDataClassification")
    public Result<Boolean> deleteDataClassification(Integer id){
        return irDataInfService.deleteDataClassification(id);
    }

    @ApiOperation(value = "分类列表")
    @GetMapping("/dataClassList")
    public Result<List<DataClassVTO>> dataClassList(Integer id){
        return irDataInfService.dataClassList(id);
    }

    @ApiOperation(value = "合并一级分类")
    @PostMapping("/mergeFirstClass")
    public Result<Boolean> mergeFirstClass(@RequestParam(value = "ids",required = true)  String ids,DataClassParam param){
        return irDataInfService.mergeFirstClass(ids,param);
    }

    @ApiOperation(value = "合并二级分类")
    @PostMapping("/mergeSecClass")
    public Result<Boolean> mergeSecClass(@RequestParam(value = "ids",required = true) String ids,DataClassParam param){
        return irDataInfService.mergeSecClass(ids,param);
    }

    @ApiOperation(value = "查询分类下数据")
    @PostMapping("/queryClassData")
    public Result<TailPage<DataInfDetailVTO>> queryClassData(MenuDataParam param){
        return irDataInfService.queryClassData(param);
    }

    @ApiOperation(value = "移动数据")
    @GetMapping("/moveData")
    public Result<Boolean> moveData(String ids,Integer firstClass, Integer secClass ){
        return irDataInfService.moveData(ids,firstClass,secClass);
    }
}
