package com.piesat.school.biz.ds.datainf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.biz.ds.datainf.entity.DataShareinf;
import com.piesat.school.datainf.param.AuditApplyListParamData;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.param.SystemEmailParamData;
import com.piesat.school.datainf.vto.AuditApplyListVTO;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.datainf.vto.SystemEmailVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
/**
 * Created with IntelliJ IDEA.
 * @Author: liqiteng
 * @Date: 2022/8/23
 * @Description:
 */
public interface IDataShareinfService extends IService<DataShareinf> {
    TailPage<ShareInfVTO> dataList(DataShareParamData paramData);


    Result<ShareInfVTO> applyForKey(DataShareParamData paramData);


    Result<ShareInfVTO> checkStatus(DataShareParamData paramData);

    Boolean keyToUrl(DataShareParamData dataShareParamData,String URL);

    TailPage<AuditApplyListVTO> auditApplyList(AuditApplyListParamData auditApplyListParamData);

    ShareInfVTO detail(DataShareParamData dataShareParamData);

    Result<ShareInfVTO> pass(DataShareParamData dataShareParamData);

    Result<SystemEmailVTO> setEmail(SystemEmailParamData systemEmailParamData);

    SystemEmailVTO seeEmail();

    String random(String url);
}
