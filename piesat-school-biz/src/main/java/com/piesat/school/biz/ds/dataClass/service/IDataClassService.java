package com.piesat.school.biz.ds.dataClass.service;

import com.piesat.school.biz.ds.dataClass.entity.DataClass;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.dataClass.VTO.DataClassVTO;
import com.piesat.school.dataClass.param.DataClassParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lapaus
 * @since 2022-04-13
 */
public interface IDataClassService extends IService<DataClass> {

    Boolean saveDataClassification(DataClassParam param);

    Boolean deleteDataClassification(Integer id);

    List<DataClassVTO> dataClassList(Integer id);

    Boolean updateDataClassification(List<String> param);
}
