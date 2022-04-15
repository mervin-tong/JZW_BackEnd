package com.piesat.school.datainf.iservice;

import com.piesat.school.datainf.param.*;
import com.piesat.school.datainf.vto.DataInfDetailVTO;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datainf.vto.DataInfVTO;
import com.piesat.school.datainf.vto.MyDataInfVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public interface IRDataInfService {
    Result<TailPage<DataInfListVTO>> getAllDatainf();
//    Result<TailPage<MyDataInfVTO>> myDataMenu(Long userId);
    Result<DataInfVTO> saveDataInf(DataInfSaveParamData paramData);
    Result<Boolean> delDataInf(String dataId, Long userId);
    Result<TailPage<DataInfListVTO>> searchByKeyword(SearchByKeyParamData searchByKeyParamData);
    Result<TailPage<DataInfListVTO>> searchByClass(SearchByClassParamData searchByClassParamData);
    Result<TailPage<DataInfListVTO>> searchByTime(SearchByTimeParamData searchByTimeParamData);
    Result<TailPage<DataInfListVTO>> searchAll(SearchAllParamData searchAllParamData);
//    Result<Boolean> uploadDataInf(String file,String amount, Long dataid) throws IOException;
//    Result<Boolean> uploadPic(String pic , Long dataId);
    Result<DataInfDetailVTO> dataInfDetailVTO(DataDetailParamData paramData);

    DataInfVTO getFilePath(Long dataId);

    Boolean addDownCount(int downCount,Long dataId);

    Boolean addhistory(Long dataId,Long userId);

    Result<TailPage<MyDataInfVTO>> dataList(DataQueryParamData paramData);
}
