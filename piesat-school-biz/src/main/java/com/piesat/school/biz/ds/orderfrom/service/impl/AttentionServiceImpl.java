package com.piesat.school.biz.ds.orderfrom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.piesat.school.biz.common.helper.BizCommonValidateHelper;
import com.piesat.school.biz.ds.orderfrom.entity.Attention;
import com.piesat.school.biz.ds.orderfrom.mapper.AttentionMapper;
import com.piesat.school.biz.ds.orderfrom.mapper.OrderFromMapper;
import com.piesat.school.biz.ds.orderfrom.service.IAttentionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.emuerlation.BizEnumType;
import com.piesat.school.orderfrom.param.OrderFromAttentionDelParamData;
import com.piesat.school.orderfrom.param.OrderFromAttentionSaveParamData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private AttentionMapper attentionMapper;

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

    @Override
    public Boolean checkAttentionDatainf(Long checkUserId, Long checkDataId) {
        QueryWrapper<Attention> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",checkUserId)
                .eq("data_id",checkDataId);
        List<Attention> attentions = attentionMapper.selectList(queryWrapper);

        if (attentions.size() == 0){
            return Boolean.TRUE;

        }
        return Boolean.FALSE;
    }
}
