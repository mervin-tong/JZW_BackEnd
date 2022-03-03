package com.piesat.school.provider.serv.datainf;

import com.piesat.school.biz.ds.datainf.service.IDatainfService;
import com.piesat.school.datainf.iservice.IRDataInfService;
import com.piesat.school.datainf.param.DataInfSaveParamData;
import com.piesat.school.datainf.param.SearchByKeyParamData;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datainf.vto.DataInfVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@DubboService
public class RDataInfService implements IRDataInfService {
    @Autowired
    IDatainfService iDatainfService;
    @Override
    public Result<List<DataInfVTO>> getAllDatainf() {
        return Result.ofSuccess(iDatainfService.getAllDatainf());
    }

    @Override
    public Result<DataInfVTO> saveDataInf(DataInfSaveParamData paramData) {
        return Result.ofSuccess(iDatainfService.saveDataInf(paramData));
    }

    @Override
    public Result<TailPage<DataInfListVTO>> searchByKeyword(SearchByKeyParamData searchByKeyParamData) {
        return Result.ofSuccess(iDatainfService.searchByKeyword(searchByKeyParamData));
    }

    @Override
    public Result<DataInfVTO> uploadDataInf(String file, Long dataid) throws IOException {
        return Result.ofSuccess(iDatainfService.uploadDataInf(file,dataid));
    }
}
