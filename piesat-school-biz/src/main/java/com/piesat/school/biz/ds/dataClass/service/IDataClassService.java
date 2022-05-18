package com.piesat.school.biz.ds.dataClass.service;

import com.piesat.school.base.PageQueryParamData;
import com.piesat.school.biz.ds.dataClass.entity.DataClass;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.dataClass.VTO.DataClassVTO;
import com.piesat.school.dataClass.param.DataClassParam;
import com.piesat.school.datainf.param.MenuDataParam;
import com.piesat.school.datainf.vto.DataInfDetailVTO;
import com.piesat.school.datainf.vto.FirstPageVTO;
import com.smartwork.api.param.ParamData;
import com.smartwork.api.support.page.TailPage;

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

    Boolean mergeFirstClass(String ids, DataClassParam param);

    Boolean mergeSecClass(String ids, DataClassParam param);

    TailPage<DataInfDetailVTO> queryClassData(Integer firstClass, Integer secClass, PageQueryParamData param);

    Boolean moveData(String ids, Integer firstClass, Integer secClass);

    Boolean updateDataClassInfo(DataClassParam param);

    TailPage<FirstPageVTO> firstPage(MenuDataParam param);

}
