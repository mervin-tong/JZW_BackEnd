package com.piesat.school.datainf.iservice;

import com.piesat.school.base.PageQueryParamData;
import com.piesat.school.dataClass.VTO.DataClassVTO;
import com.piesat.school.dataClass.param.DataClassParam;
import com.piesat.school.datainf.param.*;
import com.piesat.school.datainf.vto.*;
import com.piesat.school.generationMode.param.GenerationModeParam;
import com.piesat.school.generationMode.vto.GenerationModeVTO;
import com.smartwork.api.Result;
import com.smartwork.api.param.ParamData;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

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

    DataInfVTO getFilePath(Long dataId, Long userId);

    Boolean addDownCount(int downCount,Long dataId);

    Boolean addhistory(Long dataId,Long userId);

    Result<TailPage<MyDataInfVTO>> dataList(DataQueryParamData paramData);
    //获取元数据列表
    Result<TailPage<DataInfListVTO>> thematicData(MetadataQueryParam paramData);
    //数据集统计
    Result<StatisticsVTO> statistics();
    //获取高热度数据列表
    Result<TailPage<DataInfListVTO>> highAttention(PageQueryParamData paramData);

    Result<Boolean> saveGeneration(GenerationModeParam param);

    Result<Boolean> deleteGenerationMode(Integer id);

    Result<List<GenerationModeVTO>> generationModeDetail();

    Result<TailPage<DataInfListVTO>> upToDateAttention(PageQueryParamData paramData);

    Result<TailPage<DataInfDetailVTO>> menuDataList(MenuDataParam param);

    Result<Boolean> saveDataClassification(DataClassParam param);

    Result<Boolean> deleteDataClassification(Integer id);

    Result<List<DataClassVTO>> dataClassList(Integer id);

    Result<Boolean> updateDataClassification(List<String> param);

    Result<Boolean> mergeFirstClass(String ids, DataClassParam param);

    Result<Boolean> mergeSecClass(String ids, DataClassParam param);

    Result<TailPage<DataInfDetailVTO>> queryClassData(Integer firstClass, Integer secClass, PageQueryParamData param);

    Result<Boolean> moveData(String ids, Integer firstClass, Integer secClass);
}
