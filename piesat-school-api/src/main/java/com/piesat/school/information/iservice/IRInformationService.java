package com.piesat.school.information.iservice;

import com.piesat.school.information.param.InformationAddParamData;
import com.piesat.school.information.param.InformationPageParam;
import com.piesat.school.information.vto.InformationVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

public interface IRInformationService {
    Result<InformationVTO> addOrUpdateInformation(InformationAddParamData paramData);

    Result<Boolean> delInformation(Long id);

    Result<InformationVTO> detail(Long id);

    Result<TailPage<InformationVTO>> informationPage(InformationPageParam paramData);


}
