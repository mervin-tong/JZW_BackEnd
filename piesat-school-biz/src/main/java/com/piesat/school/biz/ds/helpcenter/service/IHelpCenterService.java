package com.piesat.school.biz.ds.helpcenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.base.PageQueryParamData;
import com.piesat.school.biz.ds.helpcenter.entity.HelpCenter;
import com.piesat.school.helpcenter.vto.HelpCenterVTO;
import com.smartwork.api.support.page.TailPage;

import java.util.List;


public interface IHelpCenterService extends IService<HelpCenter> {

    Boolean deleteByids(String ids);

    Boolean updateInfos(HelpCenterVTO helpCenterVTO);

    Boolean saveInfos(HelpCenterVTO helpCenterVTO);

    List<HelpCenterVTO> getList();

    TailPage<HelpCenterVTO> getPage(PageQueryParamData query);

    HelpCenterVTO detail(String id);
}
