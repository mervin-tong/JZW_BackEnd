package com.piesat.school.datainf.iservice;

import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper
public interface IRDataShareInfService {


    TailPage<ShareInfVTO> datalist(DataShareParamData paramData);

    Result<ShareInfVTO> applyForKey(DataShareParamData paramData);

    Result<ShareInfVTO> checkStatus(DataShareParamData paramData);
}
