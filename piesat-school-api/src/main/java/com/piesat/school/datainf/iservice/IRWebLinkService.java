package com.piesat.school.datainf.iservice;

import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.param.WebLinkParamData;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.datainf.vto.WebLinkVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

public interface IRWebLinkService {


    TailPage<WebLinkVTO> linkList(WebLinkParamData webLinkParamData);

    void addLink(WebLinkParamData webLinkParamData);

    void deleteLink(WebLinkParamData webLinkParamData);

    void updateLink(WebLinkParamData webLinkParamData);

}
