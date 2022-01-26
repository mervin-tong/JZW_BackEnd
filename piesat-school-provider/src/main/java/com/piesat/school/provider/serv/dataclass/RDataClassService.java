package com.piesat.school.provider.serv.dataclass;

import com.piesat.school.biz.ds.dataclass.service.IDataclassService;
import com.piesat.school.dataclass.iservice.IRDataClassService;
import com.piesat.school.dataclass.vto.DataClassVTO;
import com.smartwork.api.Result;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
/**
 * @author 周悦尧
 * @since 2022/1/26
 *
 */
@DubboService
public class RDataClassService implements IRDataClassService {
    @Autowired
    private IDataclassService iDataclassService;


    @Override
    public Result<List<DataClassVTO>> getAllDataClass() {
        return Result.ofSuccess(iDataclassService.getAllDataClass());
    }
}
