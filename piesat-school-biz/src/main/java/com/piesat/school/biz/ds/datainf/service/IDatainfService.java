package com.piesat.school.biz.ds.datainf.service;

import com.piesat.school.base.PageQueryParamData;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.datainf.param.*;
import com.piesat.school.datainf.vto.*;
import com.smartwork.api.support.page.TailPage;

/**
 * <p>
 * 数据信息表 服务类
 * </p>
 *
 * @author 周悦尧
 * @since 2022-01-17
 */
public interface IDatainfService extends IService<Datainf> {
    TailPage<DataInfListVTO> getAllDatainf();
//    TailPage<MyDataInfVTO> myDataMenu(Long userId);

    DataInfVTO saveDataInf(DataInfSaveParamData paramData);
    Boolean delDataInf(String dataIds);
    TailPage<DataInfListVTO> searchByKeyword(SearchByKeyParamData searchByKeyParamData);

    TailPage<DataInfListVTO> searchByClass(SearchByClassParamData searchByClassParamData);

    TailPage<DataInfListVTO> searchByTime(SearchByTimeParamData searchByTimeParamData);

    TailPage<DataInfListVTO> searchAll(SearchAllParamData searchAllParamData);

    DataInfDetailVTO dataInfDetail(Long dataId);


    DataInfVTO getFilePath(Long dataId);

    Boolean addDownCount(int downCount,Long dataId);

    Boolean addhistory(Long dataId, Long userId);

    TailPage<MyDataInfVTO> dataList(DataQueryParamData paramData);

    TailPage<DataInfListVTO> thematicData(MetadataQueryParam paramData);

    StatisticsVTO statistics();

    TailPage<DataInfListVTO> highAttention(PageQueryParamData paramData);
}
