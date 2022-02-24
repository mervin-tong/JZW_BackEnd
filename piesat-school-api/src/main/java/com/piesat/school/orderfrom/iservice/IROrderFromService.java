package com.piesat.school.orderfrom.iservice;
import com.piesat.school.orderfrom.param.OrderFromParamData;
import com.piesat.school.orderfrom.vto.OrderFromVTO;
import com.smartwork.api.Result;

import java.util.List;

public interface IROrderFromService {
    Result<List<OrderFromVTO>> orderFromMenu(Long dataType,Long downloadUserId);

    Result<OrderFromVTO> orderFromCreate(OrderFromParamData orderFromParamData);
}
