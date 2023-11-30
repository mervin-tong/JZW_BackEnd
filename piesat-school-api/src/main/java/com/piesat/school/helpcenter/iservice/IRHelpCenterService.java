package com.piesat.school.helpcenter.iservice;

import com.piesat.school.base.PageQueryParamData;
import com.piesat.school.helpcenter.vto.HelpCenterVTO;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

public interface IRHelpCenterService {

    TailPage<HelpCenterVTO> page(PageQueryParamData query);

    List<HelpCenterVTO> list();

    Boolean save(HelpCenterVTO helpCenterVTO);

    Boolean update(HelpCenterVTO helpCenterVTO);

    Boolean delete(String ids);

    HelpCenterVTO detail(String id);
}
