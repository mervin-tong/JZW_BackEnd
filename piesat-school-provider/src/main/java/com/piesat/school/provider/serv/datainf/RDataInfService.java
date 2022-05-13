package com.piesat.school.provider.serv.datainf;

import com.piesat.school.base.PageQueryParamData;
import com.piesat.school.biz.ds.dataClass.service.IDataClassService;
import com.piesat.school.biz.ds.datainf.facade.DataFacadeService;
import com.piesat.school.biz.ds.datainf.service.IDatainfService;
import com.piesat.school.biz.ds.generationmode.builder.GenerationBuilder;
import com.piesat.school.biz.ds.generationmode.service.IGenerationModeService;
import com.piesat.school.dataClass.VTO.DataClassVTO;
import com.piesat.school.dataClass.param.DataClassParam;
import com.piesat.school.datainf.iservice.IRDataInfService;
import com.piesat.school.datainf.param.*;
import com.piesat.school.datainf.vto.*;
import com.piesat.school.generationMode.param.GenerationModeParam;
import com.piesat.school.generationMode.vto.GenerationModeVTO;
import com.smartwork.api.Result;
import com.smartwork.api.param.ParamData;
import com.smartwork.api.support.page.TailPage;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

@DubboService
public class RDataInfService implements IRDataInfService {
    @Autowired
    IDatainfService iDatainfService;
    @Resource
    private DataFacadeService dataFacadeService;
    @Resource
    private IGenerationModeService generationModeService;
    @Resource
    private IDataClassService dataClassService;

    @Override
    public Result<TailPage<DataInfListVTO>> getAllDatainf() {
        return Result.ofSuccess(iDatainfService.getAllDatainf());
    }

//    @Override
//    public Result<TailPage<MyDataInfVTO>> myDataMenu(Long userId) {
//        return Result.ofSuccess(iDatainfService.myDataMenu(userId));
//    }

    @Override
    public Result<DataInfVTO> saveDataInf(DataInfSaveParamData paramData) {
        return Result.ofSuccess(iDatainfService.saveDataInf(paramData));
    }

    @Override
    public Result<Boolean> delDataInf(String dataIds) {
        return Result.ofSuccess(iDatainfService.delDataInf(dataIds));
    }

    @Override
    public Result<TailPage<DataInfListVTO>> searchByKeyword(SearchByKeyParamData searchByKeyParamData) {
        return Result.ofSuccess(iDatainfService.searchByKeyword(searchByKeyParamData));
    }

    @Override

    public Result<TailPage<DataInfListVTO>> searchByClass(SearchByClassParamData searchByClassParamData) {
        return Result.ofSuccess(iDatainfService.searchByClass(searchByClassParamData));
    }

    @Override
    public Result<TailPage<DataInfListVTO>> searchByTime(SearchByTimeParamData searchByTimeParamData) {
        return Result.ofSuccess(iDatainfService.searchByTime(searchByTimeParamData));
    }

    @Override
    public Result<TailPage<DataInfListVTO>> searchAll(SearchAllParamData searchAllParamData) {
        return Result.ofSuccess(iDatainfService.searchAll(searchAllParamData));
    }


//    @Override
//
//    public Result<Boolean> uploadDataInf(String file,String amount) throws IOException {
//        return Result.ofSuccess(iDatainfService.uploadDataInf(file,amount));
//    }

//    @Override
//    public Result<Boolean> uploadPic(String pic, Long dataId) {
//        return Result.ofSuccess(iDatainfService.uploadPic(pic,dataId));
//    }

    @Override
    public Result<DataInfDetailVTO> dataInfDetailVTO(DataDetailParamData paramData) {
        return Result.ofSuccess(dataFacadeService.dataInfDetail(paramData));
    }

    @Override
    public DataInfVTO getFilePath(Long dataId, Long userId) {
        return iDatainfService.getFilePath(dataId,userId);
    }

    @Override
    public Boolean addDownCount(int downCount,Long dataId) {
        return iDatainfService.addDownCount(downCount,dataId);
    }

    @Override
    public Boolean addhistory(Long dataId, Long userId) {
        return iDatainfService.addhistory(dataId,userId);
    }

    @Override
    public Result<TailPage<MyDataInfVTO>> dataList(DataQueryParamData paramData) {
        return Result.ofSuccess(iDatainfService.dataList(paramData));
    }

    @Override
    public Result<TailPage<DataInfListVTO>> thematicData(MetadataQueryParam paramData) {
        return Result.ofSuccess(iDatainfService.thematicData(paramData));
    }

    @Override
    public Result<StatisticsVTO> statistics() {
        return Result.ofSuccess(iDatainfService.statistics());
    }

    @Override
    public Result<TailPage<DataInfListVTO>> highAttention(PageQueryParamData paramData) {
        return Result.ofSuccess(iDatainfService.highAttention(paramData));
    }

    @Override
    public Result<Boolean> saveGeneration(GenerationModeParam param) {
        return Result.ofSuccess(generationModeService.saveGeneration(param));
    }

    @Override
    public Result<Boolean> deleteGenerationMode(Integer id) {
        return Result.ofSuccess(generationModeService.deleteGenerationMode(id));
    }

    @Override
    public Result<List<GenerationModeVTO>> generationModeDetail() {
        return Result.ofSuccess(GenerationBuilder.toVTO(generationModeService.list()));
    }

    @Override
    public Result<TailPage<DataInfListVTO>> upToDateAttention(PageQueryParamData paramData) {
        return Result.ofSuccess(iDatainfService.upToDateAttention(paramData));
    }

    @Override
    public Result<TailPage<DataInfDetailVTO>> menuDataList(MenuDataParam param) {
        return Result.ofSuccess(iDatainfService.menuDataList(param));
    }

    @Override
    public Result<Boolean> saveDataClassification(DataClassParam param) {
        return Result.ofSuccess(dataClassService.saveDataClassification(param));
    }

    @Override
    public Result<Boolean> deleteDataClassification(Integer id) {
        return  Result.ofSuccess(dataClassService.deleteDataClassification(id));
    }

    @Override
    public Result<List<DataClassVTO>> dataClassList(Integer id) {
        return Result.ofSuccess(dataClassService.dataClassList(id));
    }

    @Override
    public Result<Boolean> updateDataClassification(List<String> param) {
        return Result.ofSuccess(dataClassService.updateDataClassification(param));
    }

    @Override
    public Result<Boolean> mergeFirstClass(String ids, DataClassParam param) {
        return Result.ofSuccess(dataClassService.mergeFirstClass(ids,param));
    }

    @Override
    public Result<Boolean> mergeSecClass(String ids, DataClassParam param) {
        return Result.ofSuccess(dataClassService.mergeSecClass(ids,param));
    }

    @Override
    public Result<TailPage<DataInfDetailVTO>> queryClassData(Integer firstClass, Integer secClass, PageQueryParamData param) {
        return Result.ofSuccess(dataClassService.queryClassData(firstClass,secClass,param));
    }

    @Override
    public Result<Boolean> moveData(Long id, Integer firstClass, Integer secClass) {
        return Result.ofSuccess(dataClassService.moveData(id,firstClass,secClass));
    }
}
