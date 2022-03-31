package com.piesat.school.biz.ds.orderfrom.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.orderfrom.entity.OrderFrom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piesat.school.order.param.OrderFromAttentionParamData;
import com.piesat.school.order.param.OrderFromHistoryDownLoadParamData;
import com.piesat.school.order.param.OrderFromMenuPageParamData;
import com.piesat.school.order.vto.OrderFromAttentionVTO;
import com.piesat.school.order.vto.OrderFromHistoryDownLoadVTO;
import com.piesat.school.order.vto.OrderFromInfoVTO;
import com.piesat.school.order.vto.OrderFromVTO;
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

    List<OrderFromHistoryDownLoadVTO> historyDownload(@Param("orderFromAttentionParamData") OrderFromHistoryDownLoadParamData orderFromHistoryDownLoadParamData, Page<OrderFromHistoryDownLoadVTO> page);

    Boolean orderfromDelete(List<Long> longs);
}
