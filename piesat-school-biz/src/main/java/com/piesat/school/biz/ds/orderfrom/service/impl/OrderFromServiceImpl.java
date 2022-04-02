package com.piesat.school.biz.ds.orderfrom.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.orderfrom.bulider.OrderFromBulider;
import com.piesat.school.biz.ds.orderfrom.entity.OrderFrom;
import com.piesat.school.biz.ds.orderfrom.mapper.OrderFromMapper;
import com.piesat.school.biz.ds.orderfrom.service.IOrderFromService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.i18n.ResponseErrorCode;
import com.piesat.school.order.param.OrderFromAttentionParamData;
import com.piesat.school.order.param.OrderFromHistoryDownLoadParamData;
import com.piesat.school.order.param.OrderFromMenuPageParamData;
import com.piesat.school.order.param.OrderFromParamData;
import com.piesat.school.order.vto.OrderFromAttentionVTO;
import com.piesat.school.order.vto.OrderFromHistoryDownLoadVTO;
import com.piesat.school.order.vto.OrderFromInfoVTO;
import com.piesat.school.order.vto.OrderFromVTO;
import com.smartwork.api.exception.SmartworkI18nException;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

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

    @Resource
    private OrderFromMapper orderFromMapper;
    //获取订单列表
    @Override
    public TailPage<OrderFromVTO> orderFromMenu(OrderFromMenuPageParamData orderFromMenuPageParamData) {
        Page<OrderFromVTO> page = new Page<>(orderFromMenuPageParamData.getPn(),orderFromMenuPageParamData.getPs());
        page.setOptimizeCountSql(false);
        List<OrderFromVTO> list = orderFromMapper.orderFromMenu(orderFromMenuPageParamData, page);
        list.forEach(e->{
            if(e.getUpdatedAt()!=null&&(new Date()).getTime()>(e.getUpdatedAt().getTime()+3*24*60*60*1000)&&e.getDataType()==2){
                e.setDataType(4l);
            }
        });
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
        orderFrom.setMark(orderFromParamData.getDeclare());
        orderFrom.setDataType(1L); //默认状态为审核中
        //如果数据是公开的则为已审核(通过)
        if(orderFromParamData.getStatus() == 1L){
            orderFrom.setDataType(2L);
        }
        return OrderFromBulider.toOrderFromVTO(orderFrom);
    }

    @Override
    public OrderFromInfoVTO orderFromInfo(Long orderFromId) {
        OrderFromInfoVTO orderFromInfoVTO=new OrderFromInfoVTO();
        OrderFrom orderFrom=orderFromMapper.selectById(orderFromId);
        BeanUtils.copyProperties(orderFrom,orderFromInfoVTO);
        if(orderFrom.getUpdatedAt()!=null&&((new Date()).getTime()>(orderFrom.getUpdatedAt().getTime()+3*24*60*60*1000))&&orderFrom.getDataType()==2){
            orderFromInfoVTO.setDataType(4l);
        }
        return orderFromInfoVTO;
    }

    @Override
    public TailPage<OrderFromAttentionVTO> attentionList(OrderFromAttentionParamData orderFromAttentionParamData) {
        Page<OrderFromAttentionVTO> page = new Page<>(orderFromAttentionParamData.getPn(),orderFromAttentionParamData.getPs());
        page.setOptimizeCountSql(false);//关闭mybatis自动优化
        List<OrderFromAttentionVTO> list = orderFromMapper.attentionList(orderFromAttentionParamData, page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }

    @Override
    public TailPage<OrderFromHistoryDownLoadVTO> historyDownload(OrderFromHistoryDownLoadParamData orderFromHistoryDownLoadParamData) {
        Page<OrderFromHistoryDownLoadVTO> page = new Page<>(orderFromHistoryDownLoadParamData.getPn(),orderFromHistoryDownLoadParamData.getPs());
//        page.setOptimizeCountSql(false);//关闭mybatis自动优化
        List<OrderFromHistoryDownLoadVTO> list = orderFromMapper.historyDownload(orderFromHistoryDownLoadParamData, page);
        list.forEach(e->{
            if((e.getCreatedAt().getTime()+7*24*60*60*1000)>=(new Date()).getTime()){
                e.setIsDownloadAble(true);
            }else {
                e.setIsDownloadAble(false);
            }

        });
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }

    @Override
    public Boolean orderfromDelete(String orderfromId) {
        List<Long> longs = new ArrayList<>();
        String[] split = orderfromId.split(",");
        for (String s : split) {
            longs.add(Long.valueOf(s));
        }
        return orderFromMapper.orderfromDelete(longs);




    }
}
