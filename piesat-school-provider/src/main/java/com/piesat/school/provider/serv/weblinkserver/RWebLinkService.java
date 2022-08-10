package com.piesat.school.provider.serv.weblinkserver;

import com.piesat.school.biz.ds.datainf.service.IDataShareinfService;
import com.piesat.school.datainf.iservice.IRDataShareInfService;
import com.piesat.school.datainf.iservice.IRWebLinkService;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService(interfaceClass = IRWebLinkService.class)
public class RWebLinkService implements IRWebLinkService {

    @Resource
    private IDataShareinfService iDataShareInfService;
    @Override
    public TailPage<ShareInfVTO> datalist(DataShareParamData paramData) {
        return iDataShareInfService.dataList(paramData);
    }

    @Override
    public Result<ShareInfVTO> applyForKey(DataShareParamData paramData) {
        return iDataShareInfService.applyForKey(paramData);
    }



    @Override
    public Result<ShareInfVTO> checkStatus(DataShareParamData paramData) {
        return iDataShareInfService.checkStatus(paramData);
    }


    @Override
    public ShareInfVTO keyToUrl(DataShareParamData dataShareParamData) {
        return iDataShareInfService.keyToUrl(dataShareParamData);
    }
}
