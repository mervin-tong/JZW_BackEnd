package com.piesat.school.biz.ds.order.bulider;

import com.piesat.school.biz.ds.order.entity.OrderFrom;
import com.piesat.school.order.vto.OrderFromVTO;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

/**
 * @author suweipeng
 * @data 2022/2/13 23:05
 */
public class OrderBuilder {
    public static OrderFromVTO toOrderFromVTO(OrderFrom orderFrom){
        OrderFromVTO orderFromVTO = new OrderFromVTO();
        Optional.ofNullable(orderFrom).ifPresent(
                _orderFrom-> {
                    BeanUtils.copyProperties(_orderFrom,orderFromVTO);
                });

        return orderFromVTO;
    }
}
