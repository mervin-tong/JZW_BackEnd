package com.piesat.school.biz.ds.datainf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.datainf.param.*;
import com.piesat.school.datainf.vto.DataInfDetailVTO;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datainf.vto.DataInfVTO;
import com.smartwork.api.support.page.TailPage;
import org.mapstruct.Context;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 * 数据信息表 服务类
 * </p>
 *
 * @author 周悦尧
 * @since 2022-01-17
 */
public interface IDatainfService extends IService<Datainf> {
    List<DataInfVTO> getAllDatainf();
    DataInfVTO saveDataInf(DataInfSaveParamData paramData);
    Boolean delDataInf(Long dataId);
    TailPage<DataInfListVTO> searchByKeyword(SearchByKeyParamData searchByKeyParamData);

    TailPage<DataInfListVTO> searchByClass(SearchByClassParamData searchByClassParamData);

    TailPage<DataInfListVTO> searchByTime(SearchByTimeParamData searchByTimeParamData);

    Boolean uploadDataInf(String file,String amount,Long dataId) throws IOException;

    DataInfDetailVTO dataInfDetail(Long dataInfId);


    DataInfVTO getFilePath(Long dataId);

    Boolean addDownCount(int downCount,Long dataId);

    Boolean addhistory(Long dataId, Long userId);

    Boolean addTopicData(Long topicId, Long dataId);

    Boolean delTopicData(Long topicId, Long dataId);

}
