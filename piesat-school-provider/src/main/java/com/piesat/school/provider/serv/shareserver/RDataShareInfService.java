package com.piesat.school.provider.serv.shareserver;

import com.piesat.school.biz.ds.datainf.service.IDataShareinfService;
import com.piesat.school.datainf.iservice.IRDataShareInfService;
import com.piesat.school.datainf.param.AuditApplyListParamData;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.vto.AuditApplyListVTO;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.order.iservice.IROrderFromService;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@DubboService(interfaceClass = IRDataShareInfService.class)
public class RDataShareInfService implements IRDataShareInfService {

    @Resource
    private IDataShareinfService iDataShareInfService;
    @Override
    public TailPage<ShareInfVTO> datalist(DataShareParamData paramData) {
        return iDataShareInfService.dataList(paramData);
    }

    @Override
    public Result<ShareInfVTO> applyForKey(DataShareParamData paramData) {
        return iDataShareInfService.applyForKey(paramData);
    }



    @Override
    public Result<ShareInfVTO> checkStatus(DataShareParamData paramData) {
        return iDataShareInfService.checkStatus(paramData);
    }


    @Override
    public ShareInfVTO keyToUrl(DataShareParamData dataShareParamData) {
        return iDataShareInfService.keyToUrl(dataShareParamData);
    }

    @Override
    public TailPage<AuditApplyListVTO> auditApplyList(AuditApplyListParamData auditApplyListParamData) {
        return iDataShareInfService.auditApplyList(auditApplyListParamData);
    }

    @Override
    public Result<ShareInfVTO> updateLink(DataShareParamData dataShareParamData) {
        return null;
    }

    @Override
    public Result<String> deleteLink(DataShareParamData dataShareParamData) {
        return null;
    }

    @Override
    public Result<ShareInfVTO> addLink(DataShareParamData paramData) {
        return null;
    }

    @Override
    public AuditApplyListVTO detail(AuditApplyListParamData auditApplyListParamData) {
        return iDataShareInfService.detail(auditApplyListParamData);
    }

    @Override
    public void pass(DataShareParamData dataShareParamData) {
        iDataShareInfService.pass(dataShareParamData);
    }
}
