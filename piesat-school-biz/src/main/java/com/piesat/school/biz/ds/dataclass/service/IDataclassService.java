package com.piesat.school.biz.ds.dataclass.service;

import com.piesat.school.biz.ds.dataclass.entity.Dataclass;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.dataclass.param.DataClassDelParamData;
import com.piesat.school.dataclass.param.DataClassParamData;
import com.piesat.school.dataclass.param.DataClassQueryParamData;
import com.piesat.school.dataclass.param.DataClassSaveParamData;
import com.piesat.school.dataclass.vto.DataClassVTO;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

/**
 * <p>
 * 数据分类表 服务类
 * </p>
 *
 * @author 周悦尧
 * @since 2022-01-17
 */
public interface IDataclassService extends IService<Dataclass> {
    List<DataClassVTO> getAllDataClass();
    TailPage<DataClassVTO> pageDataClass(DataClassQueryParamData paramData);
    DataClassVTO saveDataClass(DataClassSaveParamData paramData);
    Boolean delDataClass(DataClassDelParamData paramData);
    DataClassVTO updataDataClass(DataClassParamData paramData);

}
