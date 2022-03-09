package com.piesat.school.biz.ds.orderfrom.service.impl;

import com.piesat.school.biz.common.helper.BizCommonValidateHelper;
import com.piesat.school.biz.ds.orderfrom.entity.Attention;
import com.piesat.school.biz.ds.orderfrom.mapper.AttentionMapper;
import com.piesat.school.biz.ds.orderfrom.service.IAttentionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.emuerlation.BizEnumType;
import com.piesat.school.orderfrom.param.OrderFromAttentionDelParamData;
import com.piesat.school.orderfrom.param.OrderFromAttentionSaveParamData;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 周悦尧
 * @since 2022-03-08
 */
@Service
public class AttentionServiceImpl extends ServiceImpl<AttentionMapper, Attention> implements IAttentionService {

    @Override
    public Boolean saveAttention(OrderFromAttentionSaveParamData orderFromAttentionSaveParamData) {
        if(orderFromAttentionSaveParamData.getDataId() == null && orderFromAttentionSaveParamData.getUserId() == null){
            return Boolean.FALSE;
        }
        Attention attention = new Attention();
        attention.setDataId(orderFromAttentionSaveParamData.getDataId());
        attention.setUserId(orderFromAttentionSaveParamData.getUserId());
        attention.setStatus(BizEnumType.CommonStatus.Valid.getKey());
        return this.save(attention);
    }

    @Override
    public Boolean delAttention(OrderFromAttentionDelParamData orderFromAttentionDelParamData) {
        if(orderFromAttentionDelParamData.getId() == null){
            return Boolean.FALSE;
        }
        Attention attention = BizCommonValidateHelper.valdiateGetById(orderFromAttentionDelParamData.getId(),this);
        attention.setStatus(BizEnumType.CommonStatus.Invalid.getKey());

        return this.updateById(attention);
    }
}
