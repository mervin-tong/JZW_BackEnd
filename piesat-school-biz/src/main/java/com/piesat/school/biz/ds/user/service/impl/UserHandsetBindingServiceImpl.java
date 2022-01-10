package com.piesat.school.biz.ds.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.biz.ds.user.entity.UserHandsetBinding;
import com.piesat.school.biz.ds.user.mapper.UserHandsetBindingMapper;
import com.piesat.school.biz.ds.user.service.IUserHandsetBindingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author lawliet
 * @date 2021-06-09
 * <p>
 *
 */
@Slf4j
@Service
public class UserHandsetBindingServiceImpl extends ServiceImpl<UserHandsetBindingMapper, UserHandsetBinding> implements IUserHandsetBindingService {
    /**
     * 刷新用户的极光设备ID
     * @param paramData
     */
//    @Override
//    public Boolean refreshHandsetBinding(UserHandsetBindingParamData paramData){
//        UserHandsetBinding entity = new UserHandsetBinding();
//        BeanUtils.copyProperties(paramData, entity);
//        entity.setId(paramData.getUserId());
//        boolean ret = this.updateById(entity);
//        if(!ret){
//            ret = this.save(entity);
//        }
//        return ret;
//    }
}
