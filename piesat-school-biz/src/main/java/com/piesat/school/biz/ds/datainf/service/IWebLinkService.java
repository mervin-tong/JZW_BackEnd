package com.piesat.school.biz.ds.datainf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.biz.ds.datainf.entity.DataShareinf;
import com.piesat.school.biz.ds.datainf.entity.WebLink;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.param.WebLinkParamData;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.datainf.vto.WebLinkVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

public interface IWebLinkService extends IService<WebLink> {
    TailPage<WebLinkVTO> linkList(WebLinkParamData webLinkParamData);

    void addLink(WebLinkParamData webLinkParamData);

    void updateLink(WebLinkParamData webLinkParamData);

    void deleteLink(WebLinkParamData webLinkParamData);


}
