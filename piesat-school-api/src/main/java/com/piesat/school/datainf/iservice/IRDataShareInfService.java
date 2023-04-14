package com.piesat.school.datainf.iservice;

import com.piesat.school.datainf.param.AuditApplyListParamData;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.param.SystemEmailParamData;
import com.piesat.school.datainf.vto.AuditApplyListVTO;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.datainf.vto.SystemEmailVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

public interface IRDataShareInfService {


    TailPage<ShareInfVTO> datalist(DataShareParamData paramData);

    Result<ShareInfVTO> applyForKey(DataShareParamData paramData);

    Result<ShareInfVTO> checkStatus(DataShareParamData paramData);

    Result<String> keyToUrl(DataShareParamData dataShareParamData);

    TailPage<AuditApplyListVTO> auditApplyList(AuditApplyListParamData auditApplyListParamData);


    ShareInfVTO detail(DataShareParamData dataShareParamData);

    Result<SystemEmailVTO> setEmail(SystemEmailParamData systemEmailParamData);


    Result<ShareInfVTO> pass(DataShareParamData dataShareParamData);

    SystemEmailVTO seeEmail();

    Result<List<AuditApplyListVTO>> checkinOrOut(List<Long> dataList, Long userId, Integer checkStatus);
}
