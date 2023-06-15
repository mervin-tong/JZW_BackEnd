package com.piesat.school.provider.serv.shareserver;

import com.piesat.school.biz.ds.datainf.service.IDataShareinfService;
import com.piesat.school.datainf.iservice.IRDataShareInfService;
import com.piesat.school.datainf.param.AuditApplyListParamData;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.param.SystemEmailParamData;
import com.piesat.school.datainf.vto.AuditApplyListVTO;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.datainf.vto.SystemEmailVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @Author: liqiteng
 * @Date: 2022/8/23
 * @Description:
 */
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
    public Result<String> keyToUrl(DataShareParamData dataShareParamData) {
        return iDataShareInfService.keyToUrl(dataShareParamData);
    }

    @Override
    public TailPage<AuditApplyListVTO> auditApplyList(AuditApplyListParamData auditApplyListParamData) {
        return iDataShareInfService.auditApplyList(auditApplyListParamData);
    }



    @Override
    public ShareInfVTO detail(DataShareParamData dataShareParamData) {
        return iDataShareInfService.detail(dataShareParamData);
    }

    @Override
    public Result<SystemEmailVTO> setEmail(SystemEmailParamData systemEmailParamData) {
        return iDataShareInfService.setEmail(systemEmailParamData);
    }
    @Override
    public Result<ShareInfVTO> pass(DataShareParamData dataShareParamData) {
        return iDataShareInfService.pass(dataShareParamData);
    }


    @Override
    public Result<List<AuditApplyListVTO>> checkinOrOut(List<Long> dataList, Long userId, Integer checkStatus) {
        return Result.ofSuccess(iDataShareInfService.checkinOrOut(dataList,userId,checkStatus));
    }

    @Override
    public SystemEmailVTO seeEmail() {
        return iDataShareInfService.seeEmail();
    }

}
