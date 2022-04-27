package com.piesat.school.datainf.iservice;

import com.piesat.school.base.PageQueryParamData;
import com.piesat.school.datainf.param.*;
import com.piesat.school.datainf.vto.*;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

public interface IRDataInfService {
    Result<TailPage<DataInfListVTO>> getAllDatainf();
//    Result<TailPage<MyDataInfVTO>> myDataMenu(Long userId);
    Result<DataInfVTO> saveDataInf(DataInfSaveParamData paramData);
    Result<Boolean> delDataInf(String dataIds);
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
    //获取元数据列表
    Result<TailPage<DataInfListVTO>> thematicData(MetadataQueryParam paramData);
    //数据集统计
    Result<StatisticsVTO> statistics();
    //获取高热度数据列表
    Result<TailPage<DataInfListVTO>> highAttention(PageQueryParamData paramData);
}
