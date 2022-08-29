package com.piesat.school.provider.serv.weblinkserver;

import com.piesat.school.biz.ds.datainf.service.IWebLinkService;
import com.piesat.school.datainf.iservice.IRWebLinkService;
import com.piesat.school.datainf.param.WebLinkParamData;
import com.piesat.school.datainf.vto.WebLinkVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * @Author: liqiteng
 * @Date: 2022/8/23
 * @Description:
 */
@DubboService(interfaceClass = IRWebLinkService.class)
public class RWebLinkService implements IRWebLinkService {

    @Resource
    private IWebLinkService iWebLinkService;

    @Override
    public TailPage<WebLinkVTO> linkList(WebLinkParamData webLinkParamData) {
        return iWebLinkService.linkList(webLinkParamData);
    }

    @Override
    public Result<WebLinkVTO> addLink(WebLinkParamData webLinkParamData) {
        return iWebLinkService.addLink(webLinkParamData);
    }

    @Override
    public Result<WebLinkVTO> deleteLink(WebLinkParamData webLinkParamData) {
        return iWebLinkService.deleteLink(webLinkParamData);
    }

    @Override
    public Result<WebLinkVTO> updateLink(WebLinkParamData webLinkParamData) {
        return iWebLinkService.updateLink(webLinkParamData);
    }
}
