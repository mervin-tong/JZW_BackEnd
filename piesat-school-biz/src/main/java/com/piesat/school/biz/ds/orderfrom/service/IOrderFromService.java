package com.piesat.school.biz.ds.orderfrom.service;

import com.piesat.school.biz.ds.orderfrom.entity.OrderFrom;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.order.param.OrderFromAttentionParamData;
import com.piesat.school.order.param.OrderFromHistoryDownLoadParamData;
import com.piesat.school.order.param.OrderFromMenuPageParamData;
import com.piesat.school.order.param.OrderFromParamData;
import com.piesat.school.order.vto.OrderFromAttentionVTO;
import com.piesat.school.order.vto.OrderFromHistoryDownLoadVTO;
import com.piesat.school.order.vto.OrderFromInfoVTO;
import com.piesat.school.order.vto.OrderFromVTO;
import com.smartwork.api.support.page.TailPage;

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

    OrderFromVTO orderFromCreate(OrderFromParamData orderFromParamData);

    OrderFromInfoVTO orderFromInfo(Long orderFromId);

    TailPage<OrderFromAttentionVTO> attentionList(OrderFromAttentionParamData orderFromAttentionParamData);

    TailPage<OrderFromHistoryDownLoadVTO> historyDownload(OrderFromHistoryDownLoadParamData orderFromHistoryDownLoadParamData);

    Boolean orderfromDelete(String orderfromId);
}
