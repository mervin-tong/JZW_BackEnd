package com.piesat.school.provider.serv.dataclass;

import com.baomidou.mybatisplus.extension.api.R;
import com.piesat.school.biz.ds.dataclass.service.IDataclassService;
import com.piesat.school.dataclass.iservice.IRDataClassService;
import com.piesat.school.dataclass.param.DataClassDelParamData;
import com.piesat.school.dataclass.param.DataClassParamData;
import com.piesat.school.dataclass.param.DataClassQueryParamData;
import com.piesat.school.dataclass.vto.DataClassVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
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

    @Override
    public Result<TailPage<DataClassVTO>> pageDataClass(DataClassQueryParamData paramData) {
        return null;
    }

    @Override
    public Result<DataClassVTO> saveDataClass(DataClassParamData paramData) {
        return Result.ofSuccess(iDataclassService.saveDataClass(paramData));
    }

    @Override
    public Result<Boolean> delDataClass(DataClassDelParamData paramData) {
        return Result.ofSuccess(iDataclassService.delDataClass(paramData));
    }

    @Override
    public Result<DataClassVTO> updataDataClass(DataClassParamData paramData) {
        return Result.ofSuccess(iDataclassService.updataDataClass(paramData));
    }
}
