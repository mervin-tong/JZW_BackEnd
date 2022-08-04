package com.piesat.school.provider.serv.shareserver;

import com.piesat.school.biz.ds.datainf.service.IDataShareinfService;
import com.piesat.school.datainf.iservice.IRDataShareInfService;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.order.iservice.IROrderFromService;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService(interfaceClass = IRDataShareInfService.class)
public class RDataShareInfService implements IRDataShareInfService {

    @Resource
    private IDataShareinfService iDataShareInfService;
    @Override
    public TailPage<ShareInfVTO> datalist(DataShareParamData paramData) {
        return iDataShareInfService.dataList(paramData);
    }
}
