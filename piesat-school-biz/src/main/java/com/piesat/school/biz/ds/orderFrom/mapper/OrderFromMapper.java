package com.piesat.school.biz.ds.orderfrom.mapper;

import com.piesat.school.biz.ds.orderfrom.entity.OrderFrom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piesat.school.orderfrom.vto.OrderFromVTO;

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

    List<OrderFromVTO> orderFromMenu(Long dataType, Long downloadUserId);
}
