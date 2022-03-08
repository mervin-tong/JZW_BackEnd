package com.piesat.school.orderfrom.iservice;
import com.piesat.school.orderfrom.param.*;
import com.piesat.school.orderfrom.vto.OrderFromAttentionVTO;
import com.piesat.school.orderfrom.vto.OrderFromInfoVTO;
import com.piesat.school.orderfrom.vto.OrderFromVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

public interface IROrderFromService {
    Result<TailPage<OrderFromVTO>> orderFromMenu(OrderFromMenuPageParamData orderFromMenuPageParamData);

    Result<OrderFromVTO> orderFromCreate(OrderFromParamData orderFromParamData);

    Result<OrderFromInfoVTO> orderFromInfo(Long orderFromId);

    Result<TailPage<OrderFromAttentionVTO>> attentionList(OrderFromAttentionParamData orderFromAttentionParamData);

    Result<Boolean> saveAttention(OrderFromAttentionSaveParamData orderFromAttentionSaveParamData);

    Result<Boolean> delAttention(OrderFromAttentionDelParamData orderFromAttentionDelParamData);
}
