package com.piesat.school.biz.ds.datainf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.biz.ds.datainf.entity.DataShareinf;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IDataShareinfService extends IService<DataShareinf> {
    TailPage<ShareInfVTO> dataList(DataShareParamData paramData);


    Result<ShareInfVTO> applyForKey(DataShareParamData paramData);

    Result<ShareInfVTO> checkStatus(DataShareParamData paramData);

    ShareInfVTO keyToUrl(DataShareParamData dataShareParamData);


}
