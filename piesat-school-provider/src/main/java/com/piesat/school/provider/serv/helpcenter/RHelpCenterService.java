package com.piesat.school.provider.serv.helpcenter;

import com.piesat.school.base.PageQueryParamData;
import com.piesat.school.biz.ds.helpcenter.service.IHelpCenterService;
import com.piesat.school.helpcenter.iservice.IRHelpCenterService;
import com.piesat.school.helpcenter.vto.HelpCenterVTO;
import com.smartwork.api.support.page.TailPage;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

@DubboService(interfaceClass = IRHelpCenterService.class)
public class RHelpCenterService implements IRHelpCenterService {
    @Resource
    private IHelpCenterService iHelpCenterService;

    @Override
    public TailPage<HelpCenterVTO> page(PageQueryParamData query) {
        return iHelpCenterService.getPage(query);
    }

    @Override
    public List<HelpCenterVTO> list() {
        return iHelpCenterService.getList();
    }

    @Override
    public Boolean save(HelpCenterVTO helpCenterVTO) {
        return iHelpCenterService.saveInfos(helpCenterVTO);
    }

    @Override
    public Boolean update(HelpCenterVTO helpCenterVTO) {
        return iHelpCenterService.updateInfos(helpCenterVTO);
    }

    @Override
    public Boolean delete(String ids) {
        return iHelpCenterService.deleteByids(ids);
    }

    @Override
    public HelpCenterVTO detail(String id) {
        return iHelpCenterService.detail(id);
    }
}
