package com.piesat.school.datainf.iservice;

import com.piesat.school.datainf.param.AuditApplyListParamData;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.vto.AuditApplyListVTO;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.mapstruct.Mapper;

public interface IRDataShareInfService {


    TailPage<ShareInfVTO> datalist(DataShareParamData paramData);

    Result<ShareInfVTO> applyForKey(DataShareParamData paramData);

    Result<ShareInfVTO> checkStatus(DataShareParamData paramData);

    ShareInfVTO keyToUrl(DataShareParamData dataShareParamData);

    TailPage<AuditApplyListVTO> auditApplyList(AuditApplyListParamData auditApplyListParamData);

    Result<ShareInfVTO> updateLink(DataShareParamData dataShareParamData);

    Result<String> deleteLink(DataShareParamData dataShareParamData);

    Result<ShareInfVTO> addLink(DataShareParamData paramData);

    AuditApplyListVTO detail(AuditApplyListParamData auditApplyListParamData);

    void pass(DataShareParamData dataShareParamData);
}
