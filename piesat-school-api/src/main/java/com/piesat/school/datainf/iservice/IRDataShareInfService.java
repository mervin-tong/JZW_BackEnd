package com.piesat.school.datainf.iservice;

import com.piesat.school.datainf.param.AuditApplyListParamData;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.param.SystemEmailParamData;
import com.piesat.school.datainf.vto.AuditApplyListVTO;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.datainf.vto.SystemEmailVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.mapstruct.Mapper;

public interface IRDataShareInfService {


    TailPage<ShareInfVTO> datalist(DataShareParamData paramData);

    Result<ShareInfVTO> applyForKey(DataShareParamData paramData);

    Result<ShareInfVTO> checkStatus(DataShareParamData paramData);

    ShareInfVTO keyToUrl(DataShareParamData dataShareParamData);

    TailPage<AuditApplyListVTO> auditApplyList(AuditApplyListParamData auditApplyListParamData);


    ShareInfVTO detail(DataShareParamData dataShareParamData);

    Result<SystemEmailVTO> setEmail(SystemEmailParamData systemEmailParamData);


    Result<ShareInfVTO> pass(DataShareParamData dataShareParamData);

    String random(String url);

    SystemEmailVTO seeEmail();
}
