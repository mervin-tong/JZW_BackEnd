package com.piesat.school.biz.ds.information.service;

import com.piesat.school.biz.ds.information.entity.Information;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.information.param.InformationAddParamData;
import com.piesat.school.information.param.InformationPageParam;
import com.piesat.school.information.vto.InformationVTO;
import com.smartwork.api.support.page.TailPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author suweipeng
 * @since 2022-04-11
 */
public interface IInformationService extends IService<Information> {

    InformationVTO addOrUpdateInformation(InformationAddParamData paramData);

    Boolean delInformation(Long id);

    InformationVTO detail(Long id);

    TailPage<InformationVTO> informationPage(InformationPageParam paramData);
}
