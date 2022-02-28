package com.piesat.school.biz.ds.orderfrom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.piesat.school.biz.ds.datainf.mapper.DatainfMapper;
import com.piesat.school.biz.ds.orderfrom.bulider.OrderFromBulider;
import com.piesat.school.biz.ds.orderfrom.entity.OrderFrom;
import com.piesat.school.biz.ds.orderfrom.mapper.OrderFromMapper;
import com.piesat.school.biz.ds.orderfrom.service.IOrderFromService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.i18n.ResponseErrorCode;
import com.piesat.school.orderfrom.param.OrderFromAttentionParamData;
import com.piesat.school.orderfrom.param.OrderFromMenuPageParamData;
import com.piesat.school.orderfrom.param.OrderFromParamData;
import com.piesat.school.orderfrom.vto.OrderFromAttentionVTO;
import com.piesat.school.orderfrom.vto.OrderFromInfoVTO;
import com.piesat.school.orderfrom.vto.OrderFromVTO;
import com.smartwork.api.exception.SmartworkI18nException;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 唐子超
 * @since 2022-01-17
 */
@Service
public class OrderFromServiceImpl extends ServiceImpl<OrderFromMapper, OrderFrom> implements IOrderFromService {

    @Autowired
    private OrderFromMapper orderFromMapper;
    @Autowired
    private DatainfMapper datainfMapper;
    //获取订单列表
    @Override
    public TailPage<OrderFromVTO> orderFromMenu(OrderFromMenuPageParamData orderFromMenuPageParamData) {
        Page<OrderFromVTO> page = new Page<>(orderFromMenuPageParamData.getPn(),orderFromMenuPageParamData.getPs());
        page.setOptimizeCountSql(false);
        List<OrderFromVTO> list = orderFromMapper.orderFromMenu(orderFromMenuPageParamData, page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);

    }
    //创建订单
    @Override
    public OrderFromVTO orderFromCreate(OrderFromParamData orderFromParamData) {
        if (orderFromParamData == null ||
                orderFromParamData.getDownloadUserId() == null ||
                orderFromParamData.getDataInfoId() == null){
            throw new SmartworkI18nException(ResponseErrorCode.MANAGEMENT_DATE_DENIED);
        }
        OrderFrom orderFrom = new OrderFrom();
        orderFrom.setCreatedAt(new Date());
        orderFrom.setDataInfoId(orderFromParamData.getDataInfoId());
        orderFrom.setAuditorUserId(orderFromParamData.getAuditorUserId());
        orderFrom.setDownloadUserId(orderFromParamData.getDownloadUserId());
        orderFrom.setDataType(1L); //默认状态为审核中
        //如果数据是公开的则为已审核(通过)
        if(orderFromParamData.getStatus() == 1L){
            orderFrom.setDataType(2L);
        }
        this.save(orderFrom);
        return OrderFromBulider.toOrderFromVTO(orderFrom);
    }

    @Override
    public OrderFromInfoVTO orderFromInfo(Long orderFromId) {
        return orderFromMapper.orderFromInfo(orderFromId);
    }

    @Override
    public TailPage<OrderFromAttentionVTO> attentionList(OrderFromAttentionParamData orderFromAttentionParamData) {
        Page<OrderFromAttentionVTO> page = new Page<>(orderFromAttentionParamData.getPn(),orderFromAttentionParamData.getPs());
        page.setOptimizeCountSql(false);//关闭mybatis自动优化
        List<OrderFromAttentionVTO> list = orderFromMapper.attentionList(orderFromAttentionParamData, page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }
}
