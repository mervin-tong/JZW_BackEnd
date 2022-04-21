package com.piesat.school.provider.serv.datainf;

import com.piesat.school.biz.ds.datainf.facade.DataFacadeService;
import com.piesat.school.biz.ds.datainf.service.IDatainfService;
import com.piesat.school.datainf.iservice.IRDataInfService;
import com.piesat.school.datainf.param.*;
import com.piesat.school.datainf.vto.DataInfDetailVTO;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datainf.vto.DataInfVTO;
import com.piesat.school.datainf.vto.MyDataInfVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@DubboService
public class RDataInfService implements IRDataInfService {
    @Autowired
    IDatainfService iDatainfService;
    @Resource
    private DataFacadeService dataFacadeService;
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
    public DataInfVTO getFilePath(Long dataId) {
        return iDatainfService.getFilePath(dataId);
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
}
