package com.piesat.school.provider.serv.datainf;

import com.piesat.school.biz.ds.datainf.service.IDatainfService;
import com.piesat.school.datainf.iservice.IRDataInfService;
import com.piesat.school.datainf.vto.DataInfVTO;
import com.smartwork.api.Result;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@DubboService
public class RDataInfService implements IRDataInfService {
    @Autowired
    IDatainfService iDatainfService;
    @Override
    public Result<List<DataInfVTO>> getAllDatainf() {
        return Result.ofSuccess(iDatainfService.getAllDatainf());
    }
}
