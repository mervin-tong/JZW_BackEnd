package com.piesat.school.biz.ds.order.service;

import com.piesat.school.biz.ds.order.entity.OrderFrom;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.order.param.*;
import com.piesat.school.order.vto.OrderFromAttentionVTO;
import com.piesat.school.order.vto.OrderFromHistoryDownLoadVTO;
import com.piesat.school.order.vto.OrderFromInfoVTO;
import com.piesat.school.order.vto.OrderFromVTO;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author suweipeng
 * @since 2022-01-17
 */
public interface IOrderFromService extends IService<OrderFrom> {

    TailPage<OrderFromVTO> orderFromMenu(OrderFromMenuPageParamData orderFromMenuPageParamData);

    Boolean orderFromCreate(OrderFromParamData orderFromParamData);

    OrderFromInfoVTO orderFromInfo(Long orderFromId);

    TailPage<OrderFromAttentionVTO> attentionList(OrderFromAttentionParamData orderFromAttentionParamData);

    TailPage<OrderFromHistoryDownLoadVTO> historyDownload(OrderFromHistoryDownLoadParamData orderFromHistoryDownLoadParamData);

    Boolean orderfromDelete(String orderfromId);

    OrderFromInfoVTO auditOrder(OrderAuditParamData paramData);

    List<OrderFromInfoVTO> checkInOrOut(Long userId, List<Long> idList, Integer checkStatus);

    Integer isAttention(OrderFromAttentionSaveParamData orderFromAttentionSaveParamData);

    Integer isOrder(Long dataInfoId, Long downloadUserId);

    Integer isOutOfDate(Long id);
}
