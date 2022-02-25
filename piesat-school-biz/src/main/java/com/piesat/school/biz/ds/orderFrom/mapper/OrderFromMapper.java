package com.piesat.school.biz.ds.orderfrom.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.orderfrom.entity.OrderFrom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piesat.school.orderfrom.param.OrderFromMenuPageParamData;
import com.piesat.school.orderfrom.vto.OrderFromInfoVTO;
import com.piesat.school.orderfrom.vto.OrderFromVTO;
import com.smartwork.api.support.page.TailPage;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 唐子超
 * @since 2022-01-17
 */
public interface OrderFromMapper extends BaseMapper<OrderFrom> {

    List<OrderFromVTO> orderFromMenu(OrderFromMenuPageParamData orderFromMenuPageParamData, Page<OrderFrom> page);

    OrderFromInfoVTO orderFromInfo(Long orderFromId);
}
