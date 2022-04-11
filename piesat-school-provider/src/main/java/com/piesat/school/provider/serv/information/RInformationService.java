package com.piesat.school.provider.serv.information;

import com.piesat.school.biz.ds.information.service.IInformationService;
import com.piesat.school.information.iservice.IRInformationService;
import com.piesat.school.information.param.InformationAddParamData;
import com.piesat.school.information.param.InformationPageParam;
import com.piesat.school.information.vto.InformationVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService(interfaceClass = IRInformationService.class)
public class RInformationService implements IRInformationService {
    @Resource
    private IInformationService informationService;
    @Override
    public Result<InformationVTO> addOrUpdateInformation(InformationAddParamData paramData) {
        return Result.ofSuccess(informationService.addOrUpdateInformation(paramData));
    }

    @Override
    public Result<Boolean> delInformation(Long id) {
        return Result.ofSuccess(informationService.delInformation(id));
    }

    @Override
    public Result<InformationVTO> detail(Long id) {
        return Result.ofSuccess(informationService.detail(id));
    }

    @Override
    public Result<TailPage<InformationVTO>> informationPage(InformationPageParam paramData) {
        return Result.ofSuccess(informationService.informationPage(paramData));
    }
}
