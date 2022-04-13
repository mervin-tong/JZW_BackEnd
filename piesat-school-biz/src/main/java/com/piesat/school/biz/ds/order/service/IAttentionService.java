package com.piesat.school.biz.ds.order.service;

import com.piesat.school.biz.ds.order.entity.Attention;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.order.param.OrderFromAttentionDelParamData;
import com.piesat.school.order.param.OrderFromAttentionSaveParamData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 周悦尧
 * @since 2022-03-08
 */
public interface IAttentionService extends IService<Attention> {
    Boolean saveAttention(OrderFromAttentionSaveParamData orderFromAttentionSaveParamData);
    Boolean delAttention(OrderFromAttentionDelParamData orderFromAttentionDelParamData);

    Boolean checkAttentionDatainf(Long checkUserId, Long checkDataId);
}
