package com.piesat.school.biz.ds.datainf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.datainf.param.DataInfListParamData;
import com.piesat.school.datainf.param.DataInfSaveParamData;
import com.piesat.school.datainf.param.SearchByKeyParamData;
import com.piesat.school.datainf.vto.DataInfListVTO;
import com.piesat.school.datainf.vto.DataInfVTO;
import com.smartwork.api.support.page.TailPage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    TailPage<DataInfVTO> listDataInf(DataInfListParamData paramData);
    TailPage<DataInfListVTO> searchByKeyword(SearchByKeyParamData searchByKeyParamData);
    DataInfVTO uploadDataInf(String file,Long dataid) throws IOException;

}
