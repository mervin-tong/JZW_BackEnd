package com.piesat.school.provider.serv.weblinkserver;

import com.piesat.school.biz.ds.datainf.service.IDataShareinfService;
import com.piesat.school.biz.ds.datainf.service.IWebLinkService;
import com.piesat.school.datainf.iservice.IRDataShareInfService;
import com.piesat.school.datainf.iservice.IRWebLinkService;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.param.WebLinkParamData;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.datainf.vto.WebLinkVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService(interfaceClass = IRWebLinkService.class)
public class RWebLinkService implements IRWebLinkService {

    @Resource
    private IWebLinkService iWebLinkService;

    @Override
    public TailPage<WebLinkVTO> linkList(WebLinkParamData webLinkParamData) {
        return iWebLinkService.linkList(webLinkParamData);
    }

    @Override
    public void addLink(WebLinkParamData webLinkParamData) {
        iWebLinkService.addLink(webLinkParamData);
    }

    @Override
    public void deleteLink(WebLinkParamData webLinkParamData) {
        iWebLinkService.deleteLink(webLinkParamData);
    }

    @Override
    public void updateLink(WebLinkParamData webLinkParamData) {
        iWebLinkService.updateLink(webLinkParamData);
    }
}
