package com.piesat.school.datainf.iservice;

import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.param.WebLinkParamData;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.datainf.vto.WebLinkVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

public interface IRWebLinkService {


    TailPage<WebLinkVTO> linkList(WebLinkParamData webLinkParamData);

    Result<WebLinkVTO> addLink(WebLinkParamData webLinkParamData);

    Result<WebLinkVTO> deleteLink(WebLinkParamData webLinkParamData);

    Result<WebLinkVTO> updateLink(WebLinkParamData webLinkParamData);

}
