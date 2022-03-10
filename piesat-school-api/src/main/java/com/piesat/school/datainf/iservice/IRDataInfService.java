package com.piesat.school.datainf.iservice;

import com.piesat.school.datainf.param.DataInfSaveParamData;
import com.piesat.school.datainf.param.SearchByClassParamData;
import com.piesat.school.datainf.param.SearchByKeyParamData;
import com.piesat.school.datainf.param.SearchByTimeParamData;
import com.piesat.school.datainf.vto.DataInfDetailVTO;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datainf.vto.DataInfVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public interface IRDataInfService {
    Result<List<DataInfVTO>> getAllDatainf();
    Result<DataInfVTO> saveDataInf(DataInfSaveParamData paramData);
    Result<Boolean> delDataInf(Long dataId);
    Result<TailPage<DataInfListVTO>> searchByKeyword(SearchByKeyParamData searchByKeyParamData);
    Result<TailPage<DataInfListVTO>> searchByClass(SearchByClassParamData searchByClassParamData);
    Result<TailPage<DataInfListVTO>> searchByTime(SearchByTimeParamData searchByTimeParamData);
    Result<DataInfVTO> uploadDataInf(String file,String amount, Long dataid) throws IOException;
    Result<DataInfDetailVTO> dataInfDetailVTO(Long dataInfId);

    DataInfVTO getFilePath(Long dataId);

    Boolean addDownCount(int downCount,Long dataId);

    Boolean addhistory(Long dataId,Long userId);
}
