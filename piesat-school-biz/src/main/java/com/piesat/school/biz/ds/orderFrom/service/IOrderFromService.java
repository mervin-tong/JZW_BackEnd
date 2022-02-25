package com.piesat.school.biz.ds.orderfrom.service;

import com.piesat.school.biz.ds.orderfrom.entity.OrderFrom;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.orderfrom.param.OrderFromMenuPageParamData;
import com.piesat.school.orderfrom.param.OrderFromParamData;
import com.piesat.school.orderfrom.vto.OrderFromInfoVTO;
import com.piesat.school.orderfrom.vto.OrderFromVTO;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 唐子超
 * @since 2022-01-17
 */
public interface IOrderFromService extends IService<OrderFrom> {

    TailPage<OrderFromVTO> orderFromMenu(OrderFromMenuPageParamData orderFromMenuPageParamData);

    OrderFromVTO orderFromCreate(OrderFromParamData orderFromParamData);

    OrderFromInfoVTO orderFromInfo(Long orderFromId);
}
