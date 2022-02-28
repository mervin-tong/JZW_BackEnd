package com.piesat.school.biz.ds.orderfrom.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.orderfrom.entity.OrderFrom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piesat.school.orderfrom.param.OrderFromAttentionParamData;
import com.piesat.school.orderfrom.param.OrderFromMenuPageParamData;
import com.piesat.school.orderfrom.vto.OrderFromAttentionVTO;
import com.piesat.school.orderfrom.vto.OrderFromInfoVTO;
import com.piesat.school.orderfrom.vto.OrderFromVTO;
import com.smartwork.api.support.page.TailPage;
import org.apache.ibatis.annotations.Param;

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

    List<OrderFromVTO> orderFromMenu(@Param("orderFromMenuPageParamData") OrderFromMenuPageParamData orderFromMenuPageParamData, Page<OrderFromVTO> page);

    OrderFromInfoVTO orderFromInfo(Long orderFromId);

    List<OrderFromAttentionVTO> attentionList(@Param("orderFromAttentionParamData") OrderFromAttentionParamData orderFromAttentionParamData, Page<OrderFromAttentionVTO> page);
}
