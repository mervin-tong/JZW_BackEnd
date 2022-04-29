package com.piesat.school.biz.ds.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.piesat.school.biz.ds.order.entity.Attention;
import com.piesat.school.biz.ds.order.mapper.AttentionMapper;
import com.piesat.school.biz.ds.order.service.IAttentionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.emuerlation.BizEnumType;
import com.piesat.school.order.param.OrderFromAttentionDelParamData;
import com.piesat.school.order.param.OrderFromAttentionSaveParamData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    private AttentionMapper attentionMapper;

    @Override
    public Boolean saveAttention(OrderFromAttentionSaveParamData orderFromAttentionSaveParamData) {
        if(orderFromAttentionSaveParamData.getDataId() == null && orderFromAttentionSaveParamData.getUserId() == null){
            return Boolean.FALSE;
        }
        Attention test=attentionMapper.selectOne(new QueryWrapper<Attention>().eq("user_id",orderFromAttentionSaveParamData.getUserId()).eq("data_id", orderFromAttentionSaveParamData.getDataId()));
        if (test !=null){
            return Boolean.FALSE;
        }
        Attention attention =new Attention();
        attention.setDataId(orderFromAttentionSaveParamData.getDataId());
        attention.setUserId(orderFromAttentionSaveParamData.getUserId());
        attention.setStatus(BizEnumType.CommonStatus.Valid.getKey());
        return this.save(attention);
    }

    @Override
    public Boolean delAttention(OrderFromAttentionDelParamData orderFromAttentionDelParamData) {
        if(orderFromAttentionDelParamData.getIds() == null){
            return Boolean.FALSE;
        }
        ArrayList<Long> longs = new ArrayList<>();
        String[] split = orderFromAttentionDelParamData.getIds().split(",");
        for (String s : split) {
            longs.add(Long.valueOf(s));
        }
//        UpdateWrapper<Attention> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.set("status",BizEnumType.CommonStatus.Invalid.getKey());//逻辑删除 0为删除
//        updateWrapper.in("id",longs);
        return this.removeByIds(longs);
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
