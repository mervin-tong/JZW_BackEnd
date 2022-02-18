package com.piesat.school.dataclass.iservice;

import com.piesat.school.dataclass.param.DataClassDelParamData;
import com.piesat.school.dataclass.param.DataClassParamData;
import com.piesat.school.dataclass.param.DataClassQueryParamData;
import com.piesat.school.dataclass.vto.DataClassVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

/**
 * @author 周悦尧
 * @since 2022/1/26 10.39
 */

public interface IRDataClassService {
    Result<List<DataClassVTO>> getAllDataClass();
    Result<TailPage<DataClassVTO>> pageDataClass(DataClassQueryParamData paramData);
    Result<DataClassVTO> saveDataClass(DataClassParamData paramData);
    Result<Boolean> delDataClass(DataClassDelParamData paramData);
    Result<DataClassVTO> updataDataClass(DataClassParamData paramData);
}
