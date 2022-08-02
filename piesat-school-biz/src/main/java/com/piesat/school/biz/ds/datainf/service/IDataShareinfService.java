package com.piesat.school.biz.ds.datainf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.biz.ds.datainf.entity.DataShareinf;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.smartwork.api.support.page.TailPage;

public interface IDataShareinfService extends IService<DataShareinf> {
    TailPage<ShareInfVTO> dataList(DataShareParamData paramData);
}
