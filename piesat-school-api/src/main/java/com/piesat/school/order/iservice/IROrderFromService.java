package com.piesat.school.order.iservice;
import com.piesat.school.order.param.OrderFromAttentionParamData;
import com.piesat.school.order.param.OrderFromHistoryDownLoadParamData;
import com.piesat.school.order.param.OrderFromMenuPageParamData;
import com.piesat.school.order.param.OrderFromParamData;
import com.piesat.school.order.param.*;
import com.piesat.school.order.vto.OrderFromAttentionVTO;
import com.piesat.school.order.vto.OrderFromHistoryDownLoadVTO;
import com.piesat.school.order.vto.OrderFromInfoVTO;
import com.piesat.school.order.vto.OrderFromVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

public interface IROrderFromService {
    Result<TailPage<OrderFromVTO>> orderFromMenu(OrderFromMenuPageParamData orderFromMenuPageParamData);

    Result<Boolean> orderFromCreate(OrderFromParamData orderFromParamData);

    Result<OrderFromInfoVTO> orderFromInfo(Long orderFromId);

    Result<TailPage<OrderFromAttentionVTO>> attentionList(OrderFromAttentionParamData orderFromAttentionParamData);


    //历史下载列表
    Result<TailPage<OrderFromHistoryDownLoadVTO>> historyDownload(OrderFromHistoryDownLoadParamData orderFromHistoryDownLoadParamData);

    Result<Boolean> saveAttention(OrderFromAttentionSaveParamData orderFromAttentionSaveParamData);

    Result<Boolean> delAttention(OrderFromAttentionDelParamData orderFromAttentionDelParamData);

    Result<Boolean> checkAttentionDatainf(Long checkUserId, Long checkDataId);

    Result<Boolean> orderfromDelete(String orderfromId);

    Result<OrderFromInfoVTO> auditOrder(OrderAuditParamData paramData);
    //签入签出
    Result<List<OrderFromInfoVTO>> checkInOrOut(Long userId, List<Long> idList, Integer checkStatus);
    //查询是否已关注
    Result<Integer> isAttention(OrderFromAttentionSaveParamData orderFromAttentionSaveParamData);
    //是否已加入订单
    Result<Integer> isOrder(Long dataInfoId, Long downloadUserId);

    Result<Integer> isOutOfDate(Long id);
}
