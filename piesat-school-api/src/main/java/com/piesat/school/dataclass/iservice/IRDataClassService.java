package com.piesat.school.dataclass.iservice;

import com.piesat.school.dataclass.vto.DataClassVTO;
import com.smartwork.api.Result;

import java.util.List;

/**
 * @author 周悦尧
 * @since 2022/1/26 10.39
 */

public interface IRDataClassService {
    Result<List<DataClassVTO>> getAllDataClass();
}
