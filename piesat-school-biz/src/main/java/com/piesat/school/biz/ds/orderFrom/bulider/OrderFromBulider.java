package com.piesat.school.biz.ds.orderfrom.bulider;

import com.piesat.school.biz.ds.orderfrom.entity.OrderFrom;
import com.piesat.school.biz.ds.user.entity.Role;
import com.piesat.school.orderfrom.vto.OrderFromVTO;
import com.piesat.school.user.vto.RoleVTO;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

/**
 * @author suweipeng
 * @data 2022/2/13 23:05
 */
public class OrderFromBulider {
    public static OrderFromVTO toOrderFromVTO(OrderFrom orderFrom){
        OrderFromVTO orderFromVTO = new OrderFromVTO();
        Optional.ofNullable(orderFrom).ifPresent(
                _orderFrom-> {
                    BeanUtils.copyProperties(_orderFrom,orderFromVTO);
                });

        return orderFromVTO;
    }
}
