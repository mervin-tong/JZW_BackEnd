package com.piesat.school.datainf.iservice;

import com.piesat.school.datainf.vto.DataInfVTO;
import com.smartwork.api.Result;

import java.util.List;

public interface IRDataInfService {
    Result<List<DataInfVTO>> getAllDatainf();
}
