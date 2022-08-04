package com.piesat.school.datainf.iservice;

import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

public interface IRDataShareInfService {


    TailPage<ShareInfVTO> datalist(DataShareParamData paramData);

}
